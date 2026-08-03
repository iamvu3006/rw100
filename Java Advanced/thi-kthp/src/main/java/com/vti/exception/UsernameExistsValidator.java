package com.vti.exception;

import com.vti.repository.IAccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsernameExistsValidator implements ConstraintValidator<UsernameExist, String> {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(username)) {
            return true;
        }
        return !accountRepository.existsByUsername(username);
    }
}