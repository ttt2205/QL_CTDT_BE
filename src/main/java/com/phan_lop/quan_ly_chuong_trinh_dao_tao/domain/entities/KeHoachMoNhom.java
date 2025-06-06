package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
@Table(name = "ke_hoach_mo_nhom", uniqueConstraints = {
        @UniqueConstraint(name="UK_HocPhan_NamHoc", columnNames = {"hoc_phan_id", "nam_hoc"})
})
public class KeHoachMoNhom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoc_phan_id", nullable = false)
    private HocPhan hocPhan;

    @OneToMany(mappedBy = "keHoachMoNhom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PhanCongGiangDay> phanCongGiangDay;

//    @Column(name = "he_so", nullable = false)
//    private double heSo;

    @Column(name = "khoa", nullable = false)
    private String khoa;

    @Column(name = "tong_so_nhom", nullable = false)
    private int tongSoNhom;

    @Column(name = "tong_so_sinh_vien", nullable = false)
    private int tongSoSinhVien;

    @Column(name = "namHoc", nullable = false, length = 50)
    private String namHoc;

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
