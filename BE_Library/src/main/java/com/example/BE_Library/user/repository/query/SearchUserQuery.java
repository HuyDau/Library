package com.example.BE_Library.user.repository.query;

import com.example.BE_Library.common.constant.SortOrder;
import lombok.Data;

import java.util.Map;

@Data
public class SearchUserQuery {
    private String searchTerm;

    private Map<String, SortOrder> sort;
    private Integer page;
    private Integer pageSize;

    public Integer getOffset() {
        if (page == null || pageSize  == null) {
            return null;
        }
        return (page - 1) * pageSize;
    }

    public Integer getLimit() {
        return pageSize;
    }
}
