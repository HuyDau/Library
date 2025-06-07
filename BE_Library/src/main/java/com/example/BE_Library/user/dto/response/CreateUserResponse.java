package com.example.BE_Library.user.dto.response;

import com.example.BE_Library.common.dto.response.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreateUserResponse extends ResponseData {

    private String id;

}
