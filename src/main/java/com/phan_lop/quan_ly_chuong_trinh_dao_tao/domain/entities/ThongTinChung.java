package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities;

import java.time.LocalDateTime;
import java.util.Map;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.utils.JsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "thong_tin_chung")
public class ThongTinChung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nganh_id", nullable = false)
    private Nganh nganh;

    @Column(name = "khoa_quan_ly", nullable = true)
    private String khoaQuanLy;

    @Column(name = "he_dao_tao", nullable = true)
    private String heDaoTao;

    @Column(name = "trinh_do", nullable = true)
    private String trinhDo;

    @Column(name = "tin_chi", nullable = true)
    private int tinChi;

    @Column(name = "tong_tin_chi", nullable = true)
    private int tongTinChi;

    @Column(name = "thoi_gian_dao_tao", nullable = true)
    private int thoiGianDaoTao;

    @Column(name = "nam_ban_hanh", nullable = true)
    private String namBanHanh;

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

    @Column(name = "created_by", columnDefinition = "JSON", nullable = true)
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> createdBy;

    @Column(name = "updated_by", columnDefinition = "JSON", nullable = true)
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> updatedBy;

    @Column(name = "deleted_by", columnDefinition = "JSON", nullable = true)
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> deletedBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = true;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void softDelete(Map<String, Object> updatedByUser) {
        this.deletedAt = LocalDateTime.now();
        this.isDeleted = true;
        this.deletedBy = updatedByUser;
    }
}
