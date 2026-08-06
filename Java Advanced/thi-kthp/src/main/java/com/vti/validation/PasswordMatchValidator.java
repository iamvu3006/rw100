package com.vti.validation;

import com.vti.form.RegisterForm;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterForm> {
    @Override
    public boolean isValid(RegisterForm form, ConstraintValidatorContext context) {
        if (form.getPassword() == null || form.getConfirmPassword() == null) {
            return true;
        }
        boolean valid = form.getPassword().equals(form.getConfirmPassword());
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Mật khẩu xác nhận không khớp")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }
        return valid;
    }
}