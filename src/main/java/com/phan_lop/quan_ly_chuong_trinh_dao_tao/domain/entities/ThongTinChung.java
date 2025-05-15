package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
@Table(name = "thong_tin_chung")
public class ThongTinChung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nganh_id", nullable = false)
    private Nganh nganh;

    @OneToMany(mappedBy = "thongTinChung", fetch = FetchType.LAZY)
    private List<KhoiKienThuc> listKhoiKienThuc;

    @Column(name = "gioi_thieu", nullable = true)
    private String gioiThieu = "";

    @Column(name = "khoa_quan_ly", nullable = true)
    private String khoaQuanLy;

    @Column(name = "loai_bang", nullable = true)
    private String loaiBang;

    @Column(name = "loai_hinh_dao_tao", nullable = true)
    private String loaiHinhDaoTao;

    @Column(name = "tong_tin_chi", nullable = true)
    private int tongTinChi;

    @Column(name = "ngon_ngu", nullable = true)
    private String ngonNgu;

    @Column(name = "website", nullable = true)
    private String website;

    @Column(name = "thoi_gian_dao_tao", nullable = true)
    private Double thoiGianDaoTao;

    @Column(name = "ban_hanh", nullable = true)
    private String banHanh;

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
