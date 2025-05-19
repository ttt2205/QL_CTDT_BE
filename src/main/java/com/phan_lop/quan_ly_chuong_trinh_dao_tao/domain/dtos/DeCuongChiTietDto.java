package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeCuongChiTietDto {
    private Long id;
    private HocPhanDto hocPhan;
    private String tenCotDiem;
    private Double trongSo;
    private String hinhThuc;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private boolean isDeleted;
}