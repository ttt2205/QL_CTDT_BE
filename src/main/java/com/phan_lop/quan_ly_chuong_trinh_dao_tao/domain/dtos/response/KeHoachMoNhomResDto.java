package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeHoachMoNhomResDto {
    private Long id;
//    private double heSo;
    private String khoa;
    private int tongSoNhom;
    private int tongSoSinhVien;
    private int soSinhVien1Nhom;
    private String namHoc;
    private boolean status;
    private HocPhanResDto hocPhan;

    @JsonManagedReference
    private List<PhanCongGiangDayResDto> phanCongGiangDay;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime updatedAt;
}