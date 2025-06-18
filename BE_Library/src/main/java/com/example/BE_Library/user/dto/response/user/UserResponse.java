package com.example.BE_Library.user.dto.response.user;

import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String role;
}
