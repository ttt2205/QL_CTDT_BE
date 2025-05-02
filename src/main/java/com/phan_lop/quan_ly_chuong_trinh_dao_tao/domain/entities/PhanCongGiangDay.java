package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TimeZone;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.utils.JsonConverter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "phan_cong_giang_day", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"giang_vien_id", "ke_hoach_mo_nhom_id", "hoc_ky_day"})
})
public class PhanCongGiangDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "giang_vien_id", nullable = false)
    private GiangVien giangVien;

    @ManyToOne
    @JoinColumn(name = "ke_hoach_mo_nhom_id", nullable = false)
    private KeHoachMoNhom keHoachMoNhom;

    @Column(name = "so_nhom", nullable = false)
    private int soNhom;

    @Column(name = "hoc_ky_day", nullable = false)
    private int hocKyDay;

    @Column(name = "loai", nullable = false)
    private String loai;

    @Column(name = "so_tiet_thuc_hien", nullable = false)
    private int soTietThucHien;

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
}
