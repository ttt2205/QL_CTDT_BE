package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities;

import java.time.LocalDateTime;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hoc_phan")
public class HocPhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_hoc_phan", nullable = false, unique = true)
    private String maHocPhan;

    @Column(name = "ten_hoc_phan", nullable = false)
    private String tenHocPhan;

    @Column(name = "so_tin_chi", nullable = false)
    private int soTinChi = 0;

    @Column(name = "so_tiet_ly_thuyet", nullable = false)
    private int soTietLyThuyet;

    @Column(name = "so_tiet_bai_tap", nullable = false)
    private int soTietBaiTap;

    @Column(name = "so_tiet_thuc_hanh", nullable = false)
    private int soTietThucHanh;

    @Column(name = "ma_hoc_phan_truoc", nullable = true)
    private String maHocPhanTruoc;

     @Column(name = "he_so_hoc_phan", nullable = false)
     private double heSo;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "created_at", updatable = false, columnDefinition = "DATETIME(6)")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME(6)")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = true;
        this.isDeleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void softDelete(Map<String, Object> updatedByUser) {
        this.deletedAt = LocalDateTime.now();
        this.isDeleted = true;
    }

    public int getSoTietHocPhan() {
        return soTietBaiTap + soTietThucHanh + soTietLyThuyet;
    }

}
