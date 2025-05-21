package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

import jakarta.persistence.criteria.Predicate;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.ThongTinChung;

public class ThongTinChungSpecification {
    public static Specification<ThongTinChung> byFilters(MultiValueMap<String, String> params) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc mặc định: chỉ lấy sản phẩm chưa bị xóa
            predicates.add(cb.isFalse(root.get("isDeleted")));

            // Nếu có query name -> thêm điều kiện LIKE (phân biệt chữ thường/chữ hoa)
            if (params.containsKey("search") && !params.getFirst("search").isEmpty()) {
                String search = params.getFirst("search").toLowerCase();
                predicates.add(cb.like(
                        cb.lower(root.get("khoaQuanLy")),
                        "%" + search + "%"));
                predicates.add(cb.like(
                        cb.lower(root.get("loaiBang")),
                        "%" + search + "%"));
                predicates.add(cb.like(
                        cb.lower(root.get("loaiHinhDaoTao")),
                        "%" + search + "%"));
                predicates.add(cb.like(
                        cb.lower(root.get("ngonNgu")),
                        "%" + search + "%"));
            }

            // Trả về mảng các điều kiện kết hợp với AND
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
