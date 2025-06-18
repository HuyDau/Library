package com.example.BE_Library.common.dto.request;

import com.example.BE_Library.common.constant.SortOrder;
import com.example.BE_Library.common.exception.InvalidRequestParamException;
import com.example.BE_Library.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PagingAndSortingRequest extends PagingRequest{
    private Map<String, SortOrder> sort;

    public PagingAndSortingRequest(String sort, Integer page, Integer pageSize, boolean includeTotal) {
        super(page, pageSize, includeTotal);
        setSort(sort);
    }

    public void setSort(String sortParam) {
        if (StringUtils.isEmpty(sortParam)) {
            return;
        }
        String[] sortElements = sortParam.split(",");
        if (sortElements.length % 2 != 0) {
            throw new InvalidRequestParamException("Invalid sort parameter");
        }
        Map<String, SortOrder> sort = new LinkedHashMap<>();
        for (int i = 0; i < sortElements.length; i = i + 2) {
            SortOrder sortOrder = SortOrder.valueOf(sortElements[i + 1]);
            sort.put(sortElements[i], sortOrder);
        }
        this.sort = sort;
    }
}
