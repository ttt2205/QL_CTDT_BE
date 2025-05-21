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

            if (params.containsKey("search") && !params.getFirst("search").isEmpty()) {
                String search = params.getFirst("search").toLowerCase();
                // Tạo danh sách các điều kiện OR
                List<Predicate> orPredicates = new ArrayList<>();
                orPredicates.add(cb.like(cb.lower(root.get("khoaQuanLy")), "%" + search + "%"));
                orPredicates.add(cb.like(cb.lower(root.get("loaiBang")), "%" + search + "%"));
                orPredicates.add(cb.like(cb.lower(root.get("loaiHinhDaoTao")), "%" + search + "%"));
                orPredicates.add(cb.like(cb.lower(root.get("ngonNgu")), "%" + search + "%"));

                // Kết hợp các điều kiện OR vào một predicate duy nhất
                predicates.add(cb.or(orPredicates.toArray(new Predicate[0])));
            }

            // Trả về các điều kiện kết hợp với AND
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}