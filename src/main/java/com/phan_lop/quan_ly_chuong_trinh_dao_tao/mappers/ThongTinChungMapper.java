package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.NganhDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ThongTinChungDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.ThongTinChung;

@Mapper(componentModel = "spring", uses = { NganhMapper.class }, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ThongTinChungMapper {
    ThongTinChung toEntity(ThongTinChungDto thongTinChungDto);

    ThongTinChungDto toDto(ThongTinChung thongTinChungEntity);
}
