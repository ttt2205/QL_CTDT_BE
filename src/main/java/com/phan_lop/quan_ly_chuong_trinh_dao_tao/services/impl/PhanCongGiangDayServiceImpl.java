package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.PhanCongGiangDayReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachMoNhom;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.PhanCongGiangDay;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception.BadRequestException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.PhanCongGiangDayMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.PhanCongGiangDayRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.GiangVienService;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.KeHoachMoNhomService;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.PhanCongGiangDayService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PhanCongGiangDayServiceImpl implements PhanCongGiangDayService {

    private PhanCongGiangDayRepository repository;
    private GiangVienService giangVienService;
    private KeHoachMoNhomService keHoachMoNhomService;
    @Qualifier("phanCongGiangDayMapper")
    private PhanCongGiangDayMapper mapper;

    @Override
    public PhanCongGiangDay findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BadRequestException("Phan Cong Giang Day Not Found"));
    }

    @Override
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

    @Override
    public void softDeleteById(Long id) {
        PhanCongGiangDay pc = findById(id);
        pc.softDelete(null);
        repository.save(pc);
    }

    @Transactional
    @Override
    public void hardDeleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public PhanCongGiangDay updateById(Long id, PhanCongGiangDayReqDto reqDto) {
        GiangVien gv = giangVienService.findById(reqDto.getGiangVienId());
        KeHoachMoNhom kh = keHoachMoNhomService.findById(reqDto.getKeHoachMoNhomId());

        repository.findByGiangVienAndKeHoachMoNhomAndHocKyDay(gv, kh, reqDto.getHocKyDay())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new BadRequestException("Duplicated entry for unique constraint (giangVienId, keHoachMoNhomId, hocKyDay)");
                    }
                });

        PhanCongGiangDay pc = findById(id);
        mapper.updateToEntity(reqDto, pc);
        pc.setGiangVien(gv);
        pc.setKeHoachMoNhom(kh);

        return repository.save(pc);
    }


}
