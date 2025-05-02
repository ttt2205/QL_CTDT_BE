package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhanCongGiangDayResDto {
    private Long id;
    private String nhom;
    private int hocKyDay;
    private String loai;
    private int soTietThucHien;
    private int soTietThucTe;
    private boolean status;

    private GiangVienResDto giangVien;
}
