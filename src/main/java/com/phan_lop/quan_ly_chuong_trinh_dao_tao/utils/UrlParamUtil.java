package com.phan_lop.quan_ly_chuong_trinh_dao_tao.utils;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UrlParamUtil {
    public Sort getSort(List<String> sortParams) {
        Sort sorting = Sort.unsorted(); // Mặc định không có sắp xếp

        // Lọc tất cả tham số sort
        if (sortParams != null) {
            for (String param : sortParams) {
                String[] sortInfo = param.split(",");
                if (sortInfo.length == 2) {
                    String field = sortInfo[0].trim();
                    Sort.Direction direction = Sort.Direction
                            .fromString(sortInfo[1].trim());
                    sorting = sorting.and(Sort.by(new Sort.Order(direction, field)));
                }
            }
            return sorting;
        } else {
            sorting = Sort.by(Sort.Direction.ASC, "id"); // Mặc định fallback nếu direction không hợp lệ
            return sorting;
        }
    }
}
