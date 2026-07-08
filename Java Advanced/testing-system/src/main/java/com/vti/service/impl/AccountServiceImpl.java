package com.vti.service.impl;

import com.vti.entity.Account;
import com.vti.entity.Position;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IPositionRepository;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IPositionRepository positionRepository;// dùng để xử lý khóa ngoại position_id

    @Override
    public List<Account> findAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    @Override
    public Account findById(Integer id) {
        Account account = accountRepository.findById(id).orElse(null);
        return account;
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