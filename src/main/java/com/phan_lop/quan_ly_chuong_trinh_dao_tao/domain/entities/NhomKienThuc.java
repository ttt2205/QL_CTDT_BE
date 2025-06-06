package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
@Table(name = "nhom_kien_thuc")
public class NhomKienThuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_nhom", nullable = false, unique = true)
    private String maNhom;

    @Column(name = "ten_nhom", nullable = false)
    private String tenNhom;

    @Column(name = "tich_luy", nullable = false)
    private boolean tichLuy;

    @OneToMany(mappedBy = "nhomKienThuc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<KeHoachDayHoc> listKeHoachDayHoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khoi_kien_thuc_id", nullable = false)
    private KhoiKienThuc khoiKienThuc;

    @Column(name = "so_tin_chi_tu_chon_toi_thieu", nullable = true)
    private int soTinChiTuChonToiThieu;

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

    public int getSoTinChiTuChon() {
        //tinh tong so tin chi tu chon
        NhomKienThuc nhomKienThuc = this;
        int soTinChiTuChon = nhomKienThuc.getListKeHoachDayHoc().stream()
                .filter(keHoachDayHoc -> !keHoachDayHoc.isBatBuoc()) // lọc môn tự chọn
                .map(keHoachDayHoc -> keHoachDayHoc.getHocPhan().getSoTinChi()) // lấy số tín chỉ
                .reduce(0, Integer::sum); // tính tổng
        return soTinChiTuChon;
    }
}
