package com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.TongHopKeHoachMoNhom.TongHopGiangVienDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.GiangVienReqDto;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GiangVienMapper {

    // Entity → DTO (response)
    GiangVienResDto entityToDto(GiangVien entity);

    // DTO → Entity (request - dùng khi tạo)
    GiangVien dtoToEntity(GiangVienReqDto dto);
    
    TongHopGiangVienDto toTongHopGiangVienDto(GiangVien giangVien);

    default List<GiangVienResDto> toListResDto(List<GiangVien> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
