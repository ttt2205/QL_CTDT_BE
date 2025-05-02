package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.ThongTinChung;

import jakarta.persistence.criteria.Predicate;

public class NganhSpecification {
    public static Specification<Nganh> byFilters(MultiValueMap<String, String> params) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc mặc định: chỉ lấy sản phẩm chưa bị xóa
            predicates.add(cb.isFalse(root.get("isDeleted")));

            // Nếu có query name -> thêm điều kiện LIKE (phân biệt chữ thường/chữ hoa)
            // if (params.containsKey("name")) {
            // predicates.add(cb.like(
            // cb.lower(root.get("roleName")),
            // "%" + params.getFirst("name").toLowerCase() + "%"));
            // }

            // Trả về mảng các điều kiện kết hợp với AND
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
