package com.ptit.controller;

import com.ptit.dto.BaseResponse;
import com.ptit.dto.LoginRequest;
import com.ptit.model.Users;
import com.ptit.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl service;


    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return service.register(user);

    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody LoginRequest loginRequest) {
        String jwt = service.verify(loginRequest.getUsername(), loginRequest.getPassword());
        BaseResponse response;
        if (jwt != null) {
            response = BaseResponse.builder()
                    .code(200)
                    .message("Login successful")
                    .data(jwt)
                    .build();
        } else {
            response = BaseResponse.builder()
                    .code(401)
                    .message("Login failed")
                    .data(null)
                    .build();
        }
        return ResponseEntity.ok(response);
    }
}
