package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mapper;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.PhanCongGiangDayResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.PhanCongGiangDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = { GiangVienMapper.class })
public interface PhanCongGiangDayMapper {

    // @Mapping(target = "createdAt", ignore = true)
    // @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "soTietThucTe", source = ".", qualifiedByName = "tinhSoTietThucTe")
    @Mapping(target = "nhom", source = ".", qualifiedByName = "xacDinhNhom")
    PhanCongGiangDayResDto entityToDto(PhanCongGiangDay entity);

    @Named("tinhSoTietThucTe")
    default int tinhSoTietThucTe(PhanCongGiangDay entity) {
        return (int) Math.round(entity.getSoTietThucHien() * entity.getKeHoachMoNhom().getHeSo());
    }

    @Named("xacDinhNhom")
    default String xacDinhNhom(PhanCongGiangDay entity) {
        if (entity.getLoai().equals("Ly thuyet")) {
            return entity.getSoNhom() + " LT";
        } else if (entity.getLoai().equals("Thuc hanh")) {
            return entity.getSoNhom() + " TH";
        } else if (entity.getLoai().equals("Bai tap")) {
            return entity.getSoNhom() + " BT";
        }
        return entity.getSoNhom() + "";
    }

}
