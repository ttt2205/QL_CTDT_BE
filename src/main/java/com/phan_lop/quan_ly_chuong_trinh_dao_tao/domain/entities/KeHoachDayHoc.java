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
@Table(name = "ke_hoach_day_hoc")
public class KeHoachDayHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thong_tin_chung_id", nullable = false)
    private ThongTinChung thongTinChung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoc_phan_id", nullable = false)
    private HocPhan hocPhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhom_kien_thuc_id", nullable = false)
    private NhomKienThuc nhomKienThuc;

    @Column(name = "hoc_ky", nullable = false)
    private int hocKy;

    @Column(name = "nam_hoc", nullable = false)
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
