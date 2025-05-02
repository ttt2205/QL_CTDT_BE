package com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;

@Repository
public interface NganhRepository extends JpaRepository<Nganh, Long>, JpaSpecificationExecutor<Nganh> {
    @Query("SELECT p FROM Nganh p WHERE p.id = :id AND p.isDeleted = false")
    public Optional<Nganh> findNganhById(@Param("id") Long id);

    public Nganh findNganhByMaNganh(String maNganh);

    public boolean existsById(Long id);
}
