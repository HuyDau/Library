package com.example.BE_Library.user.dto.request.user;

import com.example.BE_Library.common.dto.request.OneSearchTermPagingRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ListUserRequest extends OneSearchTermPagingRequest {
    public ListUserRequest(String searchTerm, String sort, Integer page, Integer pageSize, boolean includeTotal) {
        super(searchTerm, sort, page, pageSize, includeTotal);
    }
}
