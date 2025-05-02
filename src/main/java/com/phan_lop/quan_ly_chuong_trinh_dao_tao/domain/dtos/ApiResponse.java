package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private int statusCode; // "success" hoặc "error"
    private String message;
    private T data; // Có thể là List<T> hoặc một object
}
