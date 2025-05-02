package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.ThongTinChung;

@Repository
public interface ThongTinChungRepository
        extends JpaRepository<ThongTinChung, Long>, JpaSpecificationExecutor<ThongTinChung> {
    @Query("SELECT p FROM ThongTinChung p WHERE p.id = :id AND p.isDeleted = false")
    public Optional<ThongTinChung> findThongTinChungById(@Param("id") Long id);

    public boolean existsById(Long id);
}
