package com.vti.service.impl;

import com.vti.config.JWTUtils;
import com.vti.dto.AccountDTO;
import com.vti.dto.AccountLoginDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.enums.Role;
import com.vti.exception.BusinessException;
import com.vti.form.AccountCreateOrUpdateForm;
import com.vti.form.AccountSearchForm;
import com.vti.form.ChangPasswordForm;
import com.vti.form.ForgotPasswordForm;
import com.vti.form.LoginForm;
import com.vti.form.RegisterForm;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPositionRepository;
import com.vti.service.IAccountService;
import com.vti.specification.AccountCustomSpecification;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private IPositionRepository positionRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Page<AccountDTO> findAll(AccountSearchForm form, Pageable pageable) {
        Specification<Account> where = Specification.unrestricted();// where 1=1
        if (StringUtils.isNotEmpty(form.getEmail())) {// form.getEmail() != null && !form.getEmail().isEmpty()
            AccountCustomSpecification searchEmail = new AccountCustomSpecification("email", form.getEmail());
            where = where.and(searchEmail);// where email like ?
        }

        if (StringUtils.isNotEmpty(form.getUsername())) {
            AccountCustomSpecification searchUsername = new AccountCustomSpecification("username", form.getUsername());
            where = where.and(searchUsername);// where username like ?
        }

        if (StringUtils.isNotEmpty(form.getFullName())) {
            AccountCustomSpecification searchFullName = new AccountCustomSpecification("fullName", form.getFullName());
            where = where.and(searchFullName);// where fullName like ?
        }

        if (Objects.nonNull(form.getDepartmentId())) {
            AccountCustomSpecification searchDepartment = new AccountCustomSpecification("departmentId", form.getDepartmentId());
            where = where.and(searchDepartment);// where departmentId = ?
        }

        if (Objects.nonNull(form.getPositionId())) {
            AccountCustomSpecification searchPosition = new AccountCustomSpecification("positionId", form.getPositionId());
            where = where.and(searchPosition);// where positionId = ?
        }
        Page<Account> accountPage = accountRepository.findAll(where, pageable);//select * from account
        // chuyewern page<account> thành Page<DTO>
        Page<AccountDTO> accountDTOPage = accountPage.map(account -> modelMapper.map(account, AccountDTO.class));
        return accountDTOPage;
    }

    @Override
    public AccountDTO findById(Integer id) {
        Account account = accountRepository.findById(id).orElse(null);
        AccountDTO dto = null;
//        if (Objects.nonNull(account)) {
//            dto = modelMapper.map(account, AccountDTO.class);
//        }
        dto = modelMapper.map(account, AccountDTO.class);
        return dto;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void create(AccountCreateOrUpdateForm form) {// dành cho admin
        // validation dữ liệu
        if (accountRepository.existsByUsernameAndIdNot(form.getUsername(), null)) {
            throw BusinessException.builder().message("Username đã tồn tại!").build();
        }
        if (accountRepository.existsByEmailAndIdNot(form.getEmail(), null)) {
            throw BusinessException.builder().message("Email đã tồn tại!").build();
        }
        Department department = departmentRepository.findById(form.getDepartmentId())
                .orElseThrow(() -> BusinessException.builder().message("Department không tồn tại").build());
        Position position = positionRepository.findById(form.getPositionId())
                .orElseThrow(() -> BusinessException.builder().message("Position not found").build());
        // lưu
        Account account = new Account();
        account.setUsername(form.getUsername());
        account.setFullName(form.getFullName());
        account.setEmail(form.getEmail());
        account.setDepartment(department);
        account.setPosition(position);

        //set mac dinh password la 123456, role = USER
        account.setPassword(passwordEncoder.encode("123456"));// encode: mã hóa mk
        account.setRole(Role.USER);

        try {
            // xử lý lưu ảnh
//        form.getAvatar().getInputStream();// đây là bức ảnh
            //
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
            String newAvatarName = sdf.format(new Date());
            Path path = Paths.get("C:\\Users\\Admin\\Desktop\\rw100\\Frontend Basic\\testing-system-FE\\img\\" + newAvatarName + ".png");
            Files.copy(form.getAvatar().getInputStream(), path);// lưu ảnh vào thư mục trên
            account.setAvatarUrl(newAvatarName + ".png");// giữ nguyên tên ảnh
        } catch (IOException e) {
            throw new RuntimeException();
        }
        accountRepository.save(account);
    }

    @Override
    public void update(AccountCreateOrUpdateForm form, Integer id) {
        Account accountUpdate = accountRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Account không tồn tại").build());

        /// validation dữ liệu
        if (accountRepository.existsByUsernameAndIdNot(form.getUsername(), id)) {
            throw BusinessException.builder().message("Username đã tồn tại!").build();
        }
        if (accountRepository.existsByEmailAndIdNot(form.getEmail(), id)) {
            throw BusinessException.builder().message("Email đã tồn tại!").build();
        }
        Department department = departmentRepository.findById(form.getDepartmentId())
                .orElseThrow(() -> BusinessException.builder().message("Department không tồn tại").build());
        Position position = positionRepository.findById(form.getPositionId())
                .orElseThrow(() -> BusinessException.builder().message("Position not found").build());

        // luu lại
        accountUpdate.setUsername(form.getUsername());
        accountUpdate.setEmail(form.getEmail());
        accountUpdate.setFullName(form.getFullName());
        accountUpdate.setDepartment(department);
        accountUpdate.setPosition(position);

        try {
            // xử lý lưu ảnh
//        form.getAvatar().getInputStream();// đây là bức ảnh
            //
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
            String newAvatarName = sdf.format(new Date());
            Path path = Paths.get("C:\\Users\\Admin\\Desktop\\rw100\\Frontend Basic\\testing-system-FE\\img\\"
                    + newAvatarName + ".png");
            Files.copy(form.getAvatar().getInputStream(), path);// lưu ảnh vào thư mục trên
            accountUpdate.setAvatarUrl(newAvatarName + ".png");// giữ nguyên tên ảnh
        } catch (IOException e) {
            throw new RuntimeException();
        }
        accountRepository.save(accountUpdate);
    }

    @Override
    public AccountDTO findByUsername(String username) {
        Account account = accountRepository.seByUsername(username);
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public AccountLoginDTO login(LoginForm loginForm) {
        // check xem username va password co dung ko
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        // gen token
        String token = jwtUtils.generateToken(loginForm.getUsername());
        return new AccountLoginDTO(loginForm.getUsername(), token);
    }

    @Override
    public void register(RegisterForm form) {
        // validation dữ liệu
        if (accountRepository.existsByUsernameAndIdNot(form.getUsername(), null)) {
            throw BusinessException.builder().message("Username đã tồn tại!").build();
        }
        if (accountRepository.existsByEmailAndIdNot(form.getEmail(), null)) {
            throw BusinessException.builder().message("Email đã tồn tại!").build();
        }
        // phòng ban chờ việc và chức vụ DEV mặc định
        Department department = departmentRepository.findById(10)
                .orElseThrow(() -> BusinessException.builder().message("Department không tồn tại").build());
        Position position = positionRepository.findById(1)
                .orElseThrow(() -> BusinessException.builder().message("Position not found").build());
        // lưu
        Account account = new Account();
        account.setUsername(form.getUsername());
        account.setFullName(form.getFullName());
        account.setEmail(form.getEmail());
        account.setDepartment(department);
        account.setPosition(position);
        account.setPassword(passwordEncoder.encode(form.getPassword()));// encode: mã hóa mk
        //set role = USER
        account.setRole(Role.USER);
        accountRepository.save(account);
    }

    @Override
    public void sendEmailForgotPassword(ForgotPasswordForm form) {
        // tim account theo email
        Account account = accountRepository.findByEmail(form.getEmail());
        if (account == null) {
            return;
        }

        //sinh ra token và lưu vào account
        UUID token = UUID.randomUUID();//
        account.setToken(token.toString());
        accountRepository.save(account);

        // link trang web chứa token
        String url = "http://127.0.0.1:5500/changePassword.html?id=" + account.getId() + "&token=" + token;
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(form.getEmail());// gửi den email nao
        mailMessage.setSubject("Forgot Password");
        mailMessage.setText("Bạn click vào đường dẫn sau để đổi mật khẩu: " + url);

        javaMailSender.send(mailMessage);// gửi email
    }

    @Override
    public void changePassword(ChangPasswordForm form) {
        // check xem id + token có  hợp lệ ko?
        Account account = accountRepository.findById(form.getId()).orElseThrow(() -> BusinessException.builder().message("Account not found").build());
        if (!account.getToken().equals(form.getToken())) {
            throw BusinessException.builder().message("Token ko hợp lệ!").build();
        }

        //set lại password
        account.setPassword(passwordEncoder.encode(form.getNewPassword()));
        //clear token đi
        account.setToken(null);
        accountRepository.save(account);
    }
}