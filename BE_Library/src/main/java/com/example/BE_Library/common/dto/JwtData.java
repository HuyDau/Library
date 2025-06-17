package com.example.BE_Library.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtData {
    private String userId;
    private String email;
    private String role;
}
