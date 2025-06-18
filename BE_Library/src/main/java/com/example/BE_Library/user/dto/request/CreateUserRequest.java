package com.example.BE_Library.user.dto.request;

import com.example.BE_Library.common.constant.TextMaxLength;
import com.example.BE_Library.common.dto.request.RequestData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateUserRequest extends RequestData {

    @Size(max = TextMaxLength.TEXT_BOX, message = "Email length must be shorter than or equal " + TextMaxLength.TEXT_BOX)
    @NotBlank(message = "Email is required")
    private String email;

    @Size(max = TextMaxLength.TEXT_BOX,  message = "Password length must be shorter than or equal " + TextMaxLength.TEXT_BOX)
    @NotBlank(message = "Password is required")
    private String password;

    @Size(max = TextMaxLength.TEXT_BOX, message = "Name length must be shorter than or equal " + TextMaxLength.TEXT_BOX)
    private String fullName;

    @Size(max = TextMaxLength.TEXT_BOX, message = "Phone number must be shorter than or equal " + TextMaxLength.TEXT_BOX)
    private String phoneNumber;
}
