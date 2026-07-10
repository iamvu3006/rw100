package com.vti.service.impl;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.form.AccountCreateOrUpdateForm;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPositionRepository;
import com.vti.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<AccountDTO> findAll() {
        List<Account> accounts = accountRepository.findAll();
//        // chuyển list account thành list accountDTO
//        List<AccountDTO> accountDTOS = new ArrayList<>();
//        for (Account account : accounts) {
//            // chuyển lần lượt account -> accountDTO
//            AccountDTO dto = modelMapper.map(account, AccountDTO.class);// chuyển A  -> A'
//            accountDTOS.add(dto);
//        }
        return accounts.stream().map(acc -> modelMapper.map(acc, AccountDTO.class)).toList();
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