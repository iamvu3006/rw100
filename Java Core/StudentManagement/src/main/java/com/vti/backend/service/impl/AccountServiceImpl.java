package com.vti.backend.service.impl;

import com.vti.backend.repository.IAccountRepository;
import com.vti.backend.repository.impl.AccountRepositoryImpl;
import com.vti.backend.service.IAccountService;
import com.vti.entity.Account;

/**
 * Triển khai nghiệp vụ tài khoản, gọi xuống tầng Repository.
 */
public class AccountServiceImpl implements IAccountService {

    // Khởi tạo repository xử lý dữ liệu tài khoản
    private final IAccountRepository accountRepository = new AccountRepositoryImpl();

    /**
     * Câu 1: Xác thực thông tin đăng nhập của người dùng.
     *
     * @param email    Email đăng nhập
     * @param password Mật khẩu
     * @return Account nếu hợp lệ, null nếu sai thông tin
     */
    @Override
    public Account login(String email, String password) {
        // Kiểm tra đầu vào không được rỗng
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        return accountRepository.login(email, password);
    }
}