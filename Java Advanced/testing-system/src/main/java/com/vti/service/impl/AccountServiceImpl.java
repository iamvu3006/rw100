package com.vti.service.impl;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IPositionRepository;
import com.vti.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IPositionRepository positionRepository;// dùng để xử lý khóa ngoại position_id

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountServiceImpl departmentRepository;

    @Autowired
    private AccountServiceImpl positionRepository;

    @Override
    public List<AccountDTO> findAll() {
        List<Account> accounts = accountRepository.findAll();
        // chuyển list account thành list accountDTO
        return accounts.stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO findById(Integer id) {
        Account account = accountRepository.findById(id).orElse(null);
        AccountDTO dto = null;
        if (Objects.nonNull(account)) {
            // chuyển lẻn từng account -> accountDTO
            dto = modelMapper.map(account, AccountDTO.class);
        }
        return dto;
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void create(Account account) {
        // xử lý khóa ngoại: client gửi lên position chỉ có id -> phải tìm lại
        // position đã tồn tại trong DB trước khi lưu, ko thì Hibernate sẽ
        // hiểu nhầm là tạo mới 1 position (transient object)
        if (Objects.nonNull(account.getPosition())) {
            Position position = positionRepository.findById(account.getPosition().getId())
                    .orElseThrow(() -> new RuntimeException("Position ID not found!"));
            account.setPosition(position);
        }
        accountRepository.save(account);
    }

    @Override
    public void update(Account account, Integer id) {
        // tìm account cần update theo id
        Account accountUpdate = accountRepository.findById(id).orElse(null);
        Department department = departmentRepository.findById(account.getDepartment().getId()).orElse(null);
        if (Objects.isNull(accountUpdate)) {
            throw new RuntimeException("ID not found!");
        } else {
            // lưu lại thông tin update
            accountUpdate.setUsername(account.getUsername());
            accountUpdate.setPassword(account.getPassword());
            accountUpdate.setEmail(account.getEmail());
            accountUpdate.setFullName(account.getFullName());

            // xử lý khóa ngoại khi update
            if (Objects.nonNull(account.getPosition())) {
                Position position = positionRepository.findById(account.getPosition().getId())
                        .orElseThrow(() -> new RuntimeException("Position ID not found!"));
                accountUpdate.setPosition(position);
            }
            accountRepository.save(accountUpdate);
        }
    }
}