package com.example.BE_Library.common.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OneSearchTermPagingRequest extends PagingAndSortingRequest{
    private String searchTerm;

    public OneSearchTermPagingRequest(String searchTerm, String sort, Integer page, Integer pageSize, boolean includeTotal) {
        super(sort, page, pageSize, includeTotal);
        this.searchTerm = searchTerm;
    }
}
