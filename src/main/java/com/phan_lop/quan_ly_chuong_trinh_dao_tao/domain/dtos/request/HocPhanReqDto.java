package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HocPhanReqDto {

    @NotBlank(message = "Mã học phần không được để trống")
    private String maHocPhan;

    @NotBlank(message = "Tên học phần không được để trống")
    private String tenHocPhan;

    @NotNull(message = "Số tín chỉ không được để trống")
    @Positive(message = "Số tín chỉ phải là số dương")
    private int soTinChi;

    @NotNull(message = "Số tiết lý thuyết không được để trống")
    @Positive(message = "Số tiết lý thuyết phải là số dương")
    private int soTietLyThuyet;

    @NotNull(message = "Số tiết bài tập không được để trống")
    @Positive(message = "Số tiết bài tập phải là số dương")
    private int soTietBaiTap;

    @NotNull(message = "Số tiết thực hành không được để trống")
    @Positive(message = "Số tiết thực hành phải là số dương")
    private int soTietThucHanh;

    private String maHocPhanTruoc;


}