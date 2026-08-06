package com.vti.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.config.JWTUtils;
import com.vti.dto.AccountDTO;
import com.vti.dto.AccountLoginDTO;
import com.vti.entity.Account;
import com.vti.enums.Role;
import com.vti.exception.BusinessException;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountSearchForm;
import com.vti.form.AccountUpdateForm;
import com.vti.form.LoginForm;
import com.vti.form.RegisterForm;
import com.vti.repository.IAccountRepository;
import com.vti.service.IAccountService;
import com.vti.specification.AccountSpecification;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    /**
     * Lấy tài khoản đang đăng nhập từ SecurityContext (null nếu không có / request công khai).
     */
    private Account getCurrentAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return accountRepository.findByUsername(authentication.getName());
    }

    @Override
    public Page<AccountDTO> findAll(AccountSearchForm form, Pageable pageable) {
        Page<Account> accountPage = accountRepository.findAll(AccountSpecification.buildWhere(form), pageable);
        return accountPage.map(account -> modelMapper.map(account, AccountDTO.class));
    }

    @Override
    public AccountDTO findById(Integer id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Tài khoản không tồn tại with ID: " + id).build());
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public AccountDTO findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw BusinessException.builder().message("Tài khoản không tồn tại with username: " + username).build();
        }
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    @Transactional
    public void create(AccountCreateForm form) {
        if (accountRepository.existsByUsername(form.getUsername())) {
            throw BusinessException.builder().message("Username đã tồn tại trên hệ thống").build();
        }
        if (accountRepository.existsByEmail(form.getEmail())) {
            throw BusinessException.builder().message("Email đã tồn tại trên hệ thống").build();
        }

        Account account = Account.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .fullName(form.getFullName())
                .email(form.getEmail())
                .role(form.getRole() != null ? form.getRole() : Role.STAFF)
                .avatarUrl(form.getAvatarUrl())
                .build();

        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void update(AccountUpdateForm form, Integer id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Tài khoản không tồn tại with ID: " + id).build());

        if (!account.getEmail().equals(form.getEmail()) && accountRepository.existsByEmail(form.getEmail())) {
            throw BusinessException.builder().message("Email đã tồn tại trên hệ thống").build();
        }

        Account currentAccount = getCurrentAccount();
        boolean isSelfUpdate = currentAccount != null && currentAccount.getId().equals(id);

        // Không cho ADMIN tự đổi role của chính mình (kể cả đang là ADMIN hay STAFF)
        if (isSelfUpdate && form.getRole() != null && form.getRole() != account.getRole()) {
            throw BusinessException.builder().message("Không thể tự thay đổi vai trò của chính mình").build();
        }

        // Không cho hạ quyền admin cuối cùng của hệ thống
        if (account.getRole() == Role.ADMIN && form.getRole() == Role.STAFF) {
            long adminCount = accountRepository.countByRole(Role.ADMIN);
            if (adminCount <= 1) {
                throw BusinessException.builder().message("Không thể hạ quyền admin cuối cùng của hệ thống").build();
            }
        }

        account.setFullName(form.getFullName());
        account.setEmail(form.getEmail());
        if (form.getRole() != null) {
            account.setRole(form.getRole());
        }
        if (form.getAvatarUrl() != null) {
            account.setAvatarUrl(form.getAvatarUrl());
        }

        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Tài khoản không tồn tại with ID: " + id).build());

        Account currentAccount = getCurrentAccount();
        if (currentAccount != null && currentAccount.getId().equals(id)) {
            throw BusinessException.builder().message("Không thể tự xóa tài khoản của chính mình").build();
        }

        if (account.getRole() == Role.ADMIN) {
            long adminCount = accountRepository.countByRole(Role.ADMIN);
            if (adminCount <= 1) {
                throw BusinessException.builder().message("Không thể xóa admin cuối cùng của hệ thống").build();
            }
        }

        accountRepository.deleteById(id);
    }

    @Override
    public AccountLoginDTO login(LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword())
        );

        String token = jwtUtils.generateToken(loginForm.getUsername());
        Account account = accountRepository.findByUsername(loginForm.getUsername());

        return AccountLoginDTO.builder()
                .token(token)
                .id(account.getId())
                .username(account.getUsername())
                .fullName(account.getFullName())
                .role(account.getRole())
                .build();
    }

    @Override
    @Transactional
    public void register(RegisterForm form) {
        AccountCreateForm createForm = new AccountCreateForm();
        createForm.setUsername(form.getUsername());
        createForm.setPassword(form.getPassword());
        createForm.setFullName(form.getFullName());
        createForm.setEmail(form.getEmail());
        // Cố ý bỏ qua form.getRole() — endpoint /auth/register là permitAll(),
        // không được phép để client tự chọn role (chặn leo thang đặc quyền ADMIN qua đăng ký công khai)
        createForm.setRole(Role.STAFF);
        create(createForm);
    }
}