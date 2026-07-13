package com.vti.service.impl;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;

import com.vti.form.AccountCreateOrUpdateForm;
import com.vti.form.AccountSearchForm;

import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPositionRepository;

import com.vti.service.IAccountService;

import com.vti.specification.AccountCustomSpecification;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

    @Override
    public Page<AccountDTO> findAll(AccountSearchForm form,  Pageable pageable) {
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
        // chuyển page<account> thành Page<DTO>
        Page<AccountDTO> accountDTOPage = accountPage.map(account -> modelMapper.map(account, AccountDTO.class));
        return accountDTOPage;
    }

    @Override
    public AccountDTO findById(Integer id) {
        Account account = accountRepository.findById(id).orElse(null);
        AccountDTO dto = null;
        if (Objects.nonNull(account)) {
            dto = modelMapper.map(account, AccountDTO.class);
        }
        return dto;
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void create(AccountCreateOrUpdateForm form) {
//        // tìm department theo depId
////        Department department = departmentRepository.findById(account.getDepartment().getId()).orElse(null);
////        if (Objects.isNull(department)) {
////            throw new RuntimeException("Department not found");
////        }
//        Department department = departmentRepository.findById(account.getDepartment().getId())
//                .orElseThrow(() -> new RuntimeException("Department not found"));
//
//        // tìm position theo poID
////        Position position = positionRepository.findById(account.getPosition().getId()).orElse(null);
////        if (Objects.isNull(position)) {
////            throw new RuntimeException("Position not found");
////        }
//        Position position = positionRepository.findById(account.getPosition().getId())
//                .orElseThrow(() -> new RuntimeException("Position not found"));
//
//        if (accountRepository.existsByUsernameAndIdNot(account.getUsername(), null)) {
//            throw new RuntimeException("Username exists");
//        }
//        if (accountRepository.existsByEmailAndIdNot(account.getEmail(), null)) {
//            throw new RuntimeException("Email exists");
//        }
//        accountRepository.save(account);
        // chuyển form -> account
        // validation dữ liệu
        if (accountRepository.existsByUsernameAndIdNot(form.getUsername(), null)) {
            throw new RuntimeException("Username exists");
        }
        if (accountRepository.existsByEmailAndIdNot(form.getEmail(), null)) {
            throw new RuntimeException("Email exists");
        }
        Department department = departmentRepository.findById(form.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Position position = positionRepository.findById(form.getPositionId())
                .orElseThrow(() -> new RuntimeException("Position not found"));
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
                .orElseThrow(() -> new RuntimeException("Account not found"));

        /// validation dữ liệu
        if (accountRepository.existsByUsernameAndIdNot(form.getUsername(), id)) {
            throw new RuntimeException("Username exists");
        }
        if (accountRepository.existsByEmailAndIdNot(form.getEmail(), id)) {
            throw new RuntimeException("Email exists");
        }
        Department department = departmentRepository.findById(form.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Position position = positionRepository.findById(form.getPositionId())
                .orElseThrow(() -> new RuntimeException("Position not found"));

        // luu lại
        accountUpdate.setUsername(form.getUsername());
        accountUpdate.setEmail(form.getEmail());
        accountUpdate.setFullName(form.getFullName());
        accountUpdate.setDepartment(department);
        accountUpdate.setPosition(position);

        accountRepository.save(accountUpdate);
    }
}