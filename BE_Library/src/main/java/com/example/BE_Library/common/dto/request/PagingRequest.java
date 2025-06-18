package com.example.BE_Library.common.dto.request;

import com.example.BE_Library.common.constant.PagingConstant;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class PagingRequest extends RequestData {
    @Min(value = 1, message = "Page must be greater than or equals 1")
    private Integer page = PagingConstant.DEFAULT_PAGE;

    @Min(value = 0, message = "Page size must be greater than or equals 0")
    private Integer pageSize = PagingConstant.DEFAULT_PAGE_SIZE;

    private boolean includeTotal;
}
