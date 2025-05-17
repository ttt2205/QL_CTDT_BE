package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KeHoachMoNhomReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KeHoachMoNhomResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.TongHopKeHoachMoNhom.TongHopKeHoachMoNhomDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.TongHopKeHoachMoNhom.TongHopPhanCongDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachMoNhom;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.PhanCongGiangDay;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception.BadRequestException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.GiangVienMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.KeHoachMoNhomMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.KeHoachMoNhomRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.HocPhanService;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.KeHoachMoNhomService;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KeHoachMoNhomServiceImpl implements KeHoachMoNhomService {

    private final GiangVienMapper giangVienMapper;
    private KeHoachMoNhomRepository repository;
    @Qualifier("keHoachMoNhomMapper")
    private KeHoachMoNhomMapper mapper;
    private HocPhanService hocPhanService;

    public List<KeHoachMoNhom> findByHocPhanId(Long hocPhanId) {
        return repository.findByHocPhanId(hocPhanId);
    }

    public KeHoachMoNhom findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BadRequestException("Ke hoach mo nhom not found"));
    }

    @Override
    public List<KeHoachMoNhom> findAll() {
        return repository.findAll();
    }

    @Override
    public List<KeHoachMoNhom> search(String keyword, String namHoc) {
        return repository.searchKeHoachMoNhom(keyword, namHoc);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public KeHoachMoNhom update(Long id, KeHoachMoNhomReqDto reqDto) {
        KeHoachMoNhom keHoachMoNhom = findById(id);
        mapper.updateEntityFromDto(reqDto, keHoachMoNhom);

        HocPhan hocPhan = hocPhanService.findByMaHocPhan(reqDto.getMaHocPhan());
        keHoachMoNhom.setHocPhan(hocPhan);

        try {
            return repository.save(keHoachMoNhom);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                org.hibernate.exception.ConstraintViolationException constraintEx =
                        (org.hibernate.exception.ConstraintViolationException) ex.getCause();

                String constraintName = constraintEx.getConstraintName();
                if ("ke_hoach_mo_nhom.UK_HocPhan_NamHoc".equals(constraintName)) {
                    throw new BadRequestException("Cặp học phần và năm học đã tồn tại");
                }
            }
            throw ex;
        }
    }

    @Override
    public KeHoachMoNhomResDto add(KeHoachMoNhomReqDto reqDto) {
        KeHoachMoNhom keHoachMoNhom = mapper.reqDtoToEntity(reqDto);
        HocPhan hp = hocPhanService.findByMaHocPhan(reqDto.getMaHocPhan());
        keHoachMoNhom.setHocPhan(hp);
        keHoachMoNhom.setPhanCongGiangDay(List.of());

        try {
            KeHoachMoNhom result = repository.save(keHoachMoNhom);
            return mapper.entityToResDto(result);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                org.hibernate.exception.ConstraintViolationException constraintEx =
                        (org.hibernate.exception.ConstraintViolationException) ex.getCause();

                String constraintName = constraintEx.getConstraintName();
                System.out.println(constraintName);
                if ("ke_hoach_mo_nhom.UK_HocPhan_NamHoc".equals(constraintName)) {
                    throw new BadRequestException("Cặp học phần và năm học đã tồn tại");
                }
            }
            throw ex;
        }

    }

    public List<TongHopKeHoachMoNhomDto> tongHopKeHoachMoNhom(String namHoc) {
        List<KeHoachMoNhom> listKeHoachMoNhom = repository.findByNamHoc(namHoc);
        List<PhanCongGiangDay> listPhanCong = new ArrayList<>();
        listKeHoachMoNhom.forEach(keHoach -> {
            listPhanCong.addAll(keHoach.getPhanCongGiangDay());
        });

        Map<GiangVien, Map<KeHoachMoNhom, List<PhanCongGiangDay>>> map = listPhanCong.stream()
                .collect(Collectors.groupingBy(
                        PhanCongGiangDay::getGiangVien,
                        Collectors.groupingBy(PhanCongGiangDay::getKeHoachMoNhom)
                ));

        map.forEach((giangVien, mapKHN) -> {
            System.out.println("Giảng viên: " + giangVien.getId() + " (" + giangVien.getTen() + ")");
            mapKHN.forEach((kh, listPC)->{
                System.out.println(" - Kế hoạch: " + kh.getId() + " (" + kh.getNamHoc() + ")");
                listPC.forEach(pc->{
                    System.out.println("    + Phan cong: " + pc.getId() + " (" + pc.getSoNhom() + ")");
                });
            });
        });


        List<TongHopKeHoachMoNhomDto> ketQua = map.entrySet().stream().map(gvEntry -> {
            GiangVien gv = gvEntry.getKey();
            Map<KeHoachMoNhom, List<PhanCongGiangDay>> phanCongTheoKeHoach = gvEntry.getValue();
            List<TongHopPhanCongDto> listPhanCongDto = phanCongTheoKeHoach.entrySet().stream().map(entry-> {
                List<PhanCongGiangDay> phanCongs = entry.getValue();

                //tong hop phan cong de tao TongHopPhanCong
                KeHoachMoNhom keHoachMoNhom = phanCongs.get(0).getKeHoachMoNhom();
                HocPhan hocPhan = phanCongs.get(0).getKeHoachMoNhom().getHocPhan();
                int soLuongNhom = phanCongs.stream().mapToInt(PhanCongGiangDay::getSoNhom).sum();
                List<Integer> hocKyDay = phanCongs.stream().map(PhanCongGiangDay::getHocKyDay).collect(Collectors.toList());
                int tongSoTiet = phanCongs.stream().mapToInt(PhanCongGiangDay::getSoTietThucHien).sum();

                TongHopPhanCongDto phanCongDto = TongHopPhanCongDto.builder()
                        .maHocPhan(hocPhan.getMaHocPhan())
                        .tenHocPhan(hocPhan.getTenHocPhan())
                        .soTietHocPhan(hocPhan.getSoTietHocPhan())
                        .soTinChi(hocPhan.getSoTinChi())
                        .tongSoTietDay(tongSoTiet)
                        .hocKyDay(hocKyDay)
                        .soLuongNhomLop(soLuongNhom)
                        .build();

                return phanCongDto;
            }).collect(Collectors.toList());

            TongHopKeHoachMoNhomDto keHoachDto = TongHopKeHoachMoNhomDto.builder()
                    .giangVien(giangVienMapper.toTongHopGiangVienDto(gv))
                    .phanCongGiangDay(listPhanCongDto)
                    .build();

            return keHoachDto;
        }).collect(Collectors.toList());

        return ketQua;
    }
}
