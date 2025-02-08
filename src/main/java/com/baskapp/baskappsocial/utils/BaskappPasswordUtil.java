package com.baskapp.baskappsocial.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class BaskappPasswordUtil {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encryptPassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia");
        }
        return encoder.encode(password);
    }

    public static Boolean validatePassword(String tried, String password) {
        if (tried == null || tried.isBlank()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia");
        }
        return encoder.matches(tried, password);
    }
}
