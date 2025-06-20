package com.example.BE_Library.user.dto.request.user;

import com.example.BE_Library.common.constant.TextMaxLength;
import com.example.BE_Library.common.dto.request.RequestData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChangePasswordRequest extends RequestData {
    private String userId;

    @Size(max = TextMaxLength.TEXT_BOX,  message = "Password length must be shorter than or equal " + TextMaxLength.TEXT_BOX)
    @NotBlank(message = "Password is required")
    private String password;
}
