package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GiangVienResDto {
    private Long id;
    private String ten;
    private String chucDanh;
    private String boMon;
    private String khoa;
    private String trinhDo;
    private String chuyenMon;
    private int namSinh;
    private boolean status;
}
