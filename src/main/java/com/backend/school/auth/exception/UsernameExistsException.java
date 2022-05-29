package com.backend.school.auth.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsernameExistsException extends UsernameNotFoundException {
    public UsernameExistsException(String error) {
        super(error);
    }
}
