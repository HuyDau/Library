package com.example.BE_Library.user.dto.response.user;

import com.example.BE_Library.common.dto.response.ResponseData;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class GetUserInfoResponse extends ResponseData {
    private String id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String role;
}
