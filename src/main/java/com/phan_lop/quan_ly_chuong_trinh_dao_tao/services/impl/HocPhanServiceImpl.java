package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.HocPhanDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception.BadRequestException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception.CustomException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.DeCuongChiTietRepo;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.HocPhanRepo;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.HocPhanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HocPhanServiceImpl implements HocPhanService {

    private HocPhanRepo hocPhanRepo;
    private DeCuongChiTietRepo deCuongChiTietRepo;
    @Override
    public List<HocPhan> getAll() {
        return hocPhanRepo.findAll();
    }


    public List<HocPhan> getHocPhanWithDeCuongChiTiet() {
        return hocPhanRepo.findHocPhanWithDeCuongChiTiet();
    }
    @Override
    public HocPhan findByMaHocPhan(String maHocPhan) {
        return hocPhanRepo.findByMaHocPhan(maHocPhan).orElseThrow(() -> new BadRequestException("Ma hoc phan not existed"));
    }

    @Override
    public HocPhan findById(Long id) {
        return hocPhanRepo.findById(id).orElseThrow(() -> new BadRequestException("Ma hoc phan not existed"));
    }

    @Override
    public boolean deleteById(Long id) {
        deCuongChiTietRepo.deleteByHocPhanId(id);
        hocPhanRepo.deleteById(id);
        return true;
    }

    @Override
    public HocPhan create(HocPhanDto hpDto) {
        try {
            Optional<HocPhan> isExistSubject = hocPhanRepo.findByMaHocPhan(hpDto.getMaHocPhan());


            if (isExistSubject.isPresent()) {
                throw CustomException.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Mã học phần đã tồn tại!")
                        .build();
            }
            if (hpDto.getMaHocPhanTruoc() != null) {
                Optional<HocPhan> isExistRequiredSubject = hocPhanRepo.findByMaHocPhan(hpDto.getMaHocPhanTruoc());
                if (isExistRequiredSubject.isEmpty()) {
                    throw CustomException.builder()
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message("Mã học phần trước không tồn tại!")
                            .build();
                }
            }

            HocPhan hpEntity = HocPhan.builder()
                    .maHocPhan(hpDto.getMaHocPhan())
                    .tenHocPhan(hpDto.getTenHocPhan())
                    .soTietBaiTap(hpDto.getSoTietBaiTap())
                    .soTietLyThuyet(hpDto.getSoTietLyThuyet())
                    .soTietThucHanh(hpDto.getSoTietThucHanh())
                    .maHocPhanTruoc(hpDto.getMaHocPhanTruoc())
                    .status(true).build();
            return hocPhanRepo.save(hpEntity);

        } catch (Exception e) {
            System.out.println("createNew Subject error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public HocPhan update(Long id, HocPhanDto hpDto) {
        try {
            HocPhan isExistUpdateSubject = hocPhanRepo.findById(id).orElseThrow(() -> {
                throw CustomException.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Ngành not found!")
                        .build();
            });

            Optional<HocPhan> isExistSubjectWithSameCode = hocPhanRepo.findByMaHocPhan(hpDto.getMaHocPhan());
            if (isExistSubjectWithSameCode.isPresent() && !isExistSubjectWithSameCode.get().getId().equals(id)) {
                throw CustomException.builder()
                        .statusCode(HttpStatus.CONFLICT.value()) // Use CONFLICT for duplicate
                        .message("Mã học phần đã tồn tại!")
                        .build();
            }

            if (hpDto.getMaHocPhanTruoc() != null) {
                Optional<HocPhan> isExistRequiredSubject = hocPhanRepo.findByMaHocPhan(hpDto.getMaHocPhanTruoc());
                if (isExistRequiredSubject.isEmpty()) {
                    throw CustomException.builder()
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message("Mã học phần trước không tồn tại!")
                            .build();
                }
                isExistUpdateSubject.setMaHocPhanTruoc(hpDto.getMaHocPhanTruoc());
            } else {
                isExistUpdateSubject.setMaHocPhanTruoc(null); // Allow clearing the prerequisite
            }

            isExistUpdateSubject.setMaHocPhan(hpDto.getMaHocPhan());
            isExistUpdateSubject.setTenHocPhan(hpDto.getTenHocPhan());
            isExistUpdateSubject.setSoTietBaiTap(hpDto.getSoTietBaiTap());
            isExistUpdateSubject.setSoTietLyThuyet(hpDto.getSoTietLyThuyet());
            isExistUpdateSubject.setSoTietThucHanh(hpDto.getSoTietThucHanh());
            isExistUpdateSubject.setStatus(hpDto.isStatus());

            return hocPhanRepo.save(isExistUpdateSubject);

        } catch (Exception e) {
            System.out.println("update Subject error: " + e.getMessage()); // Correct log message
            throw e;
        }
    }
}
