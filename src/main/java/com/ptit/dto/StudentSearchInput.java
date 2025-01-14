package com.ptit.dto;

import lombok.Data;

@Data
public class StudentSearchInput {
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String phone;
}
