package com.example.service;

import jakarta.ejb.Stateless;

@Stateless
public class AuthService {

    public boolean validateUser(String username, String password) {
        return "admin".equals(username) && "1234".equals(password);
    }
}
