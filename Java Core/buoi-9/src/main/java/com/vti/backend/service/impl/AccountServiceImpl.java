package com.vti.backend.service.impl;

import com.vti.backend.repository.IAccountRepository;
import com.vti.backend.repository.IDepartmentRepository;
import com.vti.backend.repository.IPositionRepository;
import com.vti.backend.repository.impl.AccountRepositoryImpl;
import com.vti.backend.repository.impl.DepartmentRepositoryImpl;
import com.vti.backend.repository.impl.PositionRepositoryImpl;
import com.vti.backend.service.IAccountService;
import com.vti.dto.ImportError;
import com.vti.dto.csv.AccountCsv;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.utils.ScannerUtils;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AccountServiceImpl implements IAccountService {
    private static final String BASE_CSV_PATH = "C:\\Users\\Admin\\OneDrive\\Documents\\rw100\\csv";
    // khoi tao accountRepository
    private IAccountRepository accountRepository = new AccountRepositoryImpl();
    private IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
    private IPositionRepository positionRepository = new PositionRepositoryImpl();


    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public boolean create(String email, String username, String fullName, int departmentID, int positionID) {
        return accountRepository.create(email, username, fullName, departmentID, positionID);
    }

    @Override
    public boolean update(int id, String updateName, String email, String username, int departmentId, int positionId) {
        return accountRepository.update(id, updateName, email, username, departmentId, positionId);
    }

    @Override
    public boolean delete(int id) {
        return accountRepository.delete(id);
    }

    @Override
    public Map<String, Account> mapAccountByUsername() {
        return accountRepository.mapAccountByUsername();
    }

    @Override
    public boolean checkUsernameExist(String username, Integer id) {
        return accountRepository.checkUsernameExist(username, id);
    }

    @Override
    public boolean checkEmailExist(String email) {
        return accountRepository.checkEmailExist(email);
    }

    @Override
    public boolean checkIdExist(Integer id) {
        return accountRepository.checkIdExist(id);
    }

    @Override
    public boolean update(int id, String updateName) {
        return accountRepository.update(id, updateName);
    }

    @Override
    public String importAccountFromCSV(String pathName) {
        File file = new File(pathName);
        if (!file.exists()) {
            return "File không tồn tại";
        }
        if (!pathName.endsWith(".csv")) {
            return "File ko đúng định dạng!";
        }
        List<Account> accounts = new ArrayList<>();
        boolean firstLine = true;
        boolean checkImport = false;
        String header = "";
        int accountID = 0;
        List<ImportError<AccountCsv>> importErrors = new ArrayList<>();
        Map<String, Account> mapAccountByUsername = accountRepository.mapByUsername();
        Map<String, Account> mapAccountByEmail = accountRepository.mapAccountByEmail();
        List<Department> departments = departmentRepository.findAll();// kiem tra xem departmentID import vao co ton tai hay ko
        List<Position> positions = positionRepository.findAll();
        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
            header = br.readLine();// bo di dong header
            String line;
            while ((line = br.readLine()) != null) {
                this.validation(line, mapAccountByUsername, mapAccountByEmail, departments, positions, accounts, importErrors);
            }
            if (!accounts.isEmpty()) {
                checkImport = accountRepository.createAccounts(accounts);
            }

            // xuat file
            String pathError = BASE_CSV_PATH + "\\account_error.csv";// nơi mà sẽ lưu file lỗi
            this.exportCSV(header, pathError, importErrors);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String message = "";
        if (importErrors.isEmpty()) {
            message = "Import thành công";
        }
        if (accounts.isEmpty()) {
            message = "Import ko thành công, đã xuất file lỗi csv\\account_error.csv";
        }
        if (!importErrors.isEmpty() && !accounts.isEmpty()) {
            message = "Import thành công " + accounts.size() + " nhân viên, " +
                    "đã xuất lỗi ra file csv\\account_error.csv";
        }
        return message;
    }

    public void validation(String line, Map<String, Account> mapByUsername, Map<String, Account> mapByEmail,
                           List<Department> departments, List<Position> positions, List<Account> accountSuccess,
                           List<ImportError<AccountCsv>> importErrors) {
        String[] fields = line.split(",", -1);
        List<String> errors = new ArrayList<>();
        String username = fields[0];
        String email = fields[1];
        String fullName = fields[2];
        String departmentId = fields[3];//"1"  "2"
        String positionId = fields[4];
        AccountCsv accountCsv = new AccountCsv(email, fullName, username, departmentId, positionId);

        //validation
        if (Objects.isNull(username) || username.trim().isEmpty()) {
            errors.add("Username không dc để trống");
        } else if (mapByUsername.get(username) != null) {// check xem username da ton tai chua
            errors.add("Username đã tồn tại");
        }

        if (Objects.isNull(email) || email.trim().isEmpty()) {
            errors.add("Email không dc để trống");
        } else if (!email.matches(ScannerUtils.EMAIL_REGEX)) {// kiểm tra  định dạng email "a@b"
            errors.add("Email không đúng định dạng");
        } else if (mapByEmail.get(email) != null) {// check xem username da ton tai chua
            errors.add("Email đã tồn tại");
        }

        if (Objects.isNull(fullName) || fullName.trim().isEmpty()) {
            errors.add("FullName không dc để trống");
        }

        // kiêm tra xem day phai la so hay ko
        Department department = null;
        if (!departmentId.matches(ScannerUtils.NUMBER_REGEX)) {
            errors.add("DepartmentId phải là số");
        } else {
            // kiểm tra xem departmentId có tồn tại ko
            boolean checkDepartment = false;
            for (Department de : departments) {
                if (de.getId() == Integer.parseInt(departmentId)) {
                    department = de;
                    checkDepartment = true;
                    break;
                }
            }
            if (!checkDepartment) {
                errors.add("DepartmentId này không tồn tại");
            }
        }

        // kiêm tra xem day phai la so hay ko
        Position position = null;
        if (!positionId.matches(ScannerUtils.NUMBER_REGEX)) {
            errors.add("PositionId phải là số");
        } else {
            // kiểm tra xem positionId có tồn tại ko
            boolean checkPosition = false;
            for (Position po : positions) {
                if (po.getId() == Integer.parseInt(positionId)) {
                    position = po;
                    checkPosition = true;
                    break;
                }
            }
            if (!checkPosition) {
                errors.add("PositionId này không tồn tại");
            }
        }
        if (errors.isEmpty()) {// nếu ko có 1 loi nao
            Account account = new Account(username, fullName, email, department, position);
            accountSuccess.add(account);

            mapByEmail.put(email, account);
            mapByUsername.put(username, account);
        } else {
            // them line lỗi + ds lỗi liên quan vao list de ti nữa xuat file loi
            ImportError<AccountCsv> error = new ImportError(accountCsv, errors);
            importErrors.add(error);
        }
    }

    public void exportCSV(String header, String pathError, List<ImportError<AccountCsv>> importErrors) {
        if (!importErrors.isEmpty()) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(pathError));
                bw.write(header + ",error_message");
                bw.newLine();
                for (ImportError<AccountCsv> error : importErrors) {
                    String ln = error.getCsv().toString() + "," + String.join("|", error.getMessage());
                    bw.write(ln);
                    bw.newLine();
                }
                bw.flush();
            } catch (Exception e) {
            }
        }
    }

}
