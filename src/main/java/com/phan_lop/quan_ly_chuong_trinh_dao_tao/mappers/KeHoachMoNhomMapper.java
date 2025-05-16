package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KeHoachMoNhomReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.PhanCongGiangDayReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KeHoachMoNhomResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachMoNhom;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PhanCongGiangDayMapper.class, HocPhanMapper.class})
public interface KeHoachMoNhomMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "soSinhVien1Nhom", source = ".", qualifiedByName = "tinhSoSinhVien1Nhom")
    KeHoachMoNhomResDto entityToResDto(KeHoachMoNhom entity);

    // Map từ ReqDto vào Entity đang có sẵn
    void updateEntityFromDto(KeHoachMoNhomReqDto dto, @MappingTarget KeHoachMoNhom entity);

    @Named("tinhSoSinhVien1Nhom")
    default int tinhSoSinhVien1Nhom(KeHoachMoNhom entity) {
        return Math.round((float) entity.getTongSoSinhVien() / entity.getTongSoNhom());
    }

    @Mapping(target = "hocPhan", ignore = true)
    @Mapping(target = "phanCongGiangDay", ignore = true)
    KeHoachMoNhom reqDtoToEntity(KeHoachMoNhomReqDto reqDto);

    default List<KeHoachMoNhomResDto> toListDto(List<KeHoachMoNhom> keHoachMoNhomList) {
        return keHoachMoNhomList.stream().map(this::entityToResDto).collect(Collectors.toList());
    }
}
