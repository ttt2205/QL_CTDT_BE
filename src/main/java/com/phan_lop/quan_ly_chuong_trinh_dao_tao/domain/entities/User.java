package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.utils.JsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "ho_ten", nullable = false)
    private String hoTen;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "sdt", nullable = true)
    private String sdt;

    @Column(name = "vai_tro", nullable = false)
    private String vaiTro;

    @Column(name = "nam_sinh", nullable = true)
    private int namSinh;

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
