package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhanCongGiangDayReqDto {
    @NotBlank(message = "Ma giang vien khong duoc de trong")
    private Long giangVienId;

    @NotBlank(message = "Ma ke hoach mo nhom khong duoc de trong")
    private Long keHoachMoNhomId;

    @Min(value = 1, message = "Số nhóm phải lớn hơn 0")
    private int soNhom;

    @Min(value = 1, message = "Hoc ki phải tu 1 den 3")
    @Max(value = 3, message = "Hoc ki phải tu 1 den 3")
    private int hocKyDay;

    @Pattern(regexp = "^(LyThuyet|ThucHanh|BaiTap|TatCa)$", message = "Loai must have value LyThuyet|ThucHanh|BaiTap|TatCa")
    private String loai;

    @Min(value = 1, message = "So tiet thuc hien phai lon hon 0")
    private int soTietThucHien;
}
