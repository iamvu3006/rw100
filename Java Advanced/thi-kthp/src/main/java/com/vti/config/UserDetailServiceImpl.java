package com.vti.config;

import com.vti.entity.Account;
import com.vti.exception.BusinessException;
import com.vti.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (Objects.isNull(account)) {
            throw BusinessException.builder().message("Username not found").build();
        }
        return new User(username, account.getPassword(),
                AuthorityUtils.createAuthorityList(account.getRole().name()));
    }
}