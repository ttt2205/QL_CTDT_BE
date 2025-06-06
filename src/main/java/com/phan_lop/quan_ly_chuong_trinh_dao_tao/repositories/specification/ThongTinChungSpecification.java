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
                Predicate searchPredicate = cb.or(
                        cb.like(cb.lower(root.get("khoaQuanLy")), "%" + search + "%"),
                        cb.like(cb.lower(root.get("loaiBang")), "%" + search + "%"),
                        cb.like(cb.lower(root.get("loaiHinhDaoTao")), "%" + search + "%"),
                        cb.like(cb.lower(root.get("ngonNgu")), "%" + search + "%"));

                predicates.add(searchPredicate);
            }

            // Trả về các điều kiện kết hợp với AND
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}