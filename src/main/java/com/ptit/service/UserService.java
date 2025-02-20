package com.ptit.service;

import com.ptit.model.Users;

public interface UserService {
    Users register(Users user);
    String verify(String username, String password);
}