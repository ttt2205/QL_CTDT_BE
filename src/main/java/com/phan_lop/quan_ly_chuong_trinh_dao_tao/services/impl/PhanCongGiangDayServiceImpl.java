package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.PhanCongGiangDayReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachMoNhom;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.PhanCongGiangDay;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception.BadRequestException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.PhanCongGiangDayRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.GiangVienService;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.KeHoachMoNhomService;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.PhanCongGiangDayService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhanCongGiangDayServiceImpl implements PhanCongGiangDayService {

    private PhanCongGiangDayRepository repository;
    private GiangVienService giangVienService;
    private KeHoachMoNhomService keHoachMoNhomService;

    public List<PhanCongGiangDay> getByKeHoachMoNhomId(Long keHoachMoNhomId) {
        return repository.findByKeHoachMoNhomId(keHoachMoNhomId).orElseThrow(() -> new BadRequestException("Ke hoach not found"));
    }

    @Override
    public PhanCongGiangDay addPhanCongGiangDay(PhanCongGiangDayReqDto reqDto) {
        GiangVien gv = giangVienService.findById(reqDto.getGiangVienId());
        KeHoachMoNhom khmn = keHoachMoNhomService.findById(reqDto.getKeHoachMoNhomId());
        if (repository.existsByGiangVienAndKeHoachMoNhomAndHocKyDay(gv, khmn, reqDto.getHocKyDay())) {
            throw new BadRequestException("Duplicated entry for unique constrain(giangVienId, keHoachMoNhomId, hocKyDay)");
        }
        PhanCongGiangDay pc = PhanCongGiangDay.builder()
                .giangVien(gv)
                .keHoachMoNhom(khmn)
                .soNhom(reqDto.getSoNhom())
                .hocKyDay(reqDto.getHocKyDay())
                .loai(reqDto.getLoai())
                .soTietThucHien(reqDto.getSoTietThucHien())
                .build();
        repository.save(pc);
        return pc;
    }
}
