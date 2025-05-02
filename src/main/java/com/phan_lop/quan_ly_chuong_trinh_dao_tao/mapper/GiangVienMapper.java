package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mapper;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GiangVienMapper {

    GiangVienResDto entityToDto(GiangVien entity);
}
