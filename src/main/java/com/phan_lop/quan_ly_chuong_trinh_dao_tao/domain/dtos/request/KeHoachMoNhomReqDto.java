package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeHoachMoNhomReqDto {

    @NotBlank(message = "Mã học phần không được để trống")
    private String maHocPhan;

    @NotBlank(message = "Khoa không được để trống")
    private String khoa;

    @NotBlank(message = "Năm học không được để trống")
    @Pattern(regexp = "2\\d{3}-2\\d{3}", message = "Năm học phải có định dạng 'yyyy-yyyy'")
    private String namHoc;

    @Min(value = 1, message = "Tổng số nhóm phải lớn hơn 0")
    private int tongSoNhom;

    @Min(value = 1, message = "Tổng số sinh viên phải lớn hơn 0")
    private int tongSoSinhVien;
//
//    @DecimalMin(value = "0.1", message = "Hệ số phải lớn hơn 0")
//    private double heSo;

}

