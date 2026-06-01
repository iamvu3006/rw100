package com.vti.backend.service.impl;

import com.vti.backend.repository.IAccountRepository;
import com.vti.backend.repository.IDepartmentRepository;
import com.vti.backend.repository.IPositionRepository;
import com.vti.backend.repository.impl.AccountRepositoryImpl;
import com.vti.backend.repository.impl.DepartmentRepositoryImpl;
import com.vti.backend.repository.impl.PositionRepositoryImpl;
import com.vti.backend.service.IAccountService;
import com.vti.dto.ImportError;
import com.vti.dto.context.AccountContext;
import com.vti.dto.csv.AccountCsv;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.utils.ScannerUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AccountServiceImpl implements IAccountService {
    private static final String BASE_CSV_PATH = "C:\\Users\\Admin\\OneDrive\\Documents\\rw100\\csv";
    // khoi tao accountRepository
    private final IAccountRepository accountRepository = new AccountRepositoryImpl();
    private final IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
    private final IPositionRepository positionRepository = new PositionRepositoryImpl();


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

        AccountContext context = new AccountContext(
                accountRepository.mapByUsername(),
                accountRepository.mapAccountByEmail(),
                departmentRepository.findAll(),
                positionRepository.findAll()
        );
        String pathError = BASE_CSV_PATH + "\\account_error.csv";
        return this.importFileCSV(pathName, context, pathError);
    }

    @Override
    public void saveAll(List<Account> entities) {
        accountRepository.createAccounts(entities);
    }

    @Override
    public void validation(String line, AccountContext context, List<Account> accountSuccess,
                           List<ImportError<AccountCsv>> importErrors) {
        String[] fields = line.split(",", -1);
        List<String> errors = new ArrayList<>();
        String username = fields.length > 0 ? fields[0] : "";
        String email = fields.length > 1 ? fields[1] : "";
        String fullName = fields.length > 2 ? fields[2] : "";
        String departmentId = fields.length > 3 ? fields[3] : "";//"1"  "2"
        String positionId = fields.length > 4 ? fields[4] : "";
        AccountCsv accountCsv = new AccountCsv(email, fullName, username, departmentId, positionId);

        //validation
        if (Objects.isNull(username) || username.trim().isEmpty()) {
            errors.add("Username không dc để trống");
        } else if (context.getMapByUsername().get(username) != null) {// check xem username da ton tai chua
            errors.add("Username đã tồn tại");
        }

        if (Objects.isNull(email) || email.trim().isEmpty()) {
            errors.add("Email không dc để trống");
        } else if (!email.matches(ScannerUtils.EMAIL_REGEX)) {// kiểm tra  định dạng email "a@b"
            errors.add("Email không đúng định dạng");
        } else if (context.getMapByEmail().get(email) != null) {// check xem username da ton tai chua
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
            for (Department de : context.getDepartments()) {
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
            for (Position po : context.getPositions()) {
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

            context.getMapByEmail().put(email, account);
            context.getMapByUsername().put(username, account);
        } else {
            // them line lỗi + ds lỗi liên quan vao list de ti nữa xuat file loi
            ImportError<AccountCsv> error = new ImportError<>(accountCsv, errors);
            importErrors.add(error);
        }
    }

    @Override
    public void exportFileError(List<ImportError<AccountCsv>> importErrors, String pathError) {
        if (!importErrors.isEmpty()) {
            try {
                File parent = new File(pathError).getParentFile();
                if (parent != null && !parent.exists() && !parent.mkdirs()) {
                    throw new IOException("Không thể tạo thư mục chứa file lỗi CSV");
                }
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathError))) {
                    bw.write("username,email,full_name,department_id,position_id,error_message");
                    bw.newLine();
                    for (ImportError<AccountCsv> error : importErrors) {
                        String ln = error.getCsv().toString() + "," + String.join("|", error.getMessage());
                        bw.write(ln);
                        bw.newLine();
                    }
                    bw.flush();
                }
            } catch (Exception e) {
                throw new RuntimeException("Không thể ghi file lỗi CSV", e);
            }
        }
    }

}
