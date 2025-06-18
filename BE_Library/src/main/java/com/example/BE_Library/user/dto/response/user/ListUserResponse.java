package com.example.BE_Library.user.dto.response.user;

import com.example.BE_Library.common.dto.response.ResponseData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class ListUserResponse extends ResponseData {
    List<UserResponse> users;
    private Long total;

    public ListUserResponse(List<UserResponse> users) {this.users = users;}
}
