package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.PhanCongGiangDayReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.PhanCongGiangDayResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.PhanCongGiangDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.jmx.export.annotation.ManagedOperation;

import java.util.List;

@Mapper(componentModel = "spring", uses = { GiangVienMapper.class })
public interface PhanCongGiangDayMapper {

    @Mapping(target = "soTietThucTe", source = ".", qualifiedByName = "tinhSoTietThucTe")
    @Mapping(target = "nhom", source = ".", qualifiedByName = "xacDinhNhom")
    @Mapping(target = "soNhom", ignore = false)
    PhanCongGiangDayResDto entityToDto(PhanCongGiangDay entity);

    PhanCongGiangDay updateToEntity(PhanCongGiangDayReqDto reqDto, @MappingTarget PhanCongGiangDay entity);

    default List<PhanCongGiangDayResDto> toListResDto(List<PhanCongGiangDay> entities) {
        return entities.stream().map(this::entityToDto).toList();
    }

    @Named("tinhSoTietThucTe")
    default int tinhSoTietThucTe(PhanCongGiangDay entity) {
        return (int) Math.round(entity.getSoTietThucHien() * entity.getKeHoachMoNhom().getHocPhan().getHeSo());
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
