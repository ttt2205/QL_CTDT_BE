package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.ChiTietThongTinChungResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.ThongTinChung;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {KhoiKienThucMapper.class})
public interface ChiTietThongTinChungMapper {

    @Mapping(target = "maNganh", source = ".", qualifiedByName = "getMaNganh")
    @Mapping(target = "tenNganh", source = ".", qualifiedByName = "getTenNganh")
    ChiTietThongTinChungResDto toDto(ThongTinChung thongTinChung);

    @Named("getMaNganh")
    default String getMaNganh(ThongTinChung thongTinChung) {
        return thongTinChung.getNganh().getMaNganh();
    }

    @Named("getTenNganh")
    default String getTenNganh(ThongTinChung thongTinChung) {
        return thongTinChung.getNganh().getTenNganh();
    }
}
