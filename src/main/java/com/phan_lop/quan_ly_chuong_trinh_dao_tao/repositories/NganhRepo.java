package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NganhRepo extends JpaRepository<Nganh, Long> {
    Optional<Nganh> getByMaNganh(String maNganh);
}
