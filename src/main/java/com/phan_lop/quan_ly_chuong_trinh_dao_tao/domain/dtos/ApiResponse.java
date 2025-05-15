package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //exclude null
public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private T data;
}
