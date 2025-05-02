package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseListData<T> {
    private int statusCode;
    private String message;
    private Map<String, Object> metadata;
    private List<T> data; // Có thể là List<T> hoặc một object
}
