package com.vti.service.impl;

import com.vti.config.JWTUtils;
import com.vti.dto.AccountDTO;
import com.vti.dto.AccountLoginDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.exception.BusinessException;
import com.vti.form.AccountCreateOrUpdateForm;
import com.vti.form.AccountSearchForm;
import com.vti.form.LoginForm;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

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
    public void create(AccountCreateOrUpdateForm form) {
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
}