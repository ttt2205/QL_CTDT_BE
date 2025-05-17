package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.GiangVienMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.GiangVienRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.GiangVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class GiangVienServiceImpl implements GiangVienService {

    private final GiangVienRepository giangVienRepository;
    private final GiangVienMapper giangVienMapper;

    @Override
    public List<GiangVienResDto> getAllActiveGiangViens() {
        return giangVienMapper.toListResDto(giangVienRepository.findByStatusTrue());
    }

    @Override
    public List<GiangVienResDto> searchByTen(String ten) {
        return giangVienMapper.toListResDto(giangVienRepository.findByStatusTrueAndTenContainingIgnoreCase(ten));
    }

    @Override
    public List<GiangVienResDto> findByKhoa(String khoa) {
        return giangVienMapper.toListResDto(giangVienRepository.findByStatusTrueAndKhoa(khoa));
    }
    
    @Override
    public GiangVien findById(Long id) {
        return giangVienRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy giảng viên ID = " + id));
    }
    @Override
    public void deleteSoft(Long id) {
        GiangVien gv = giangVienRepository.findById(id).orElseThrow();
        gv.setStatus(false);
        giangVienRepository.save(gv);
    }

    @Override
public void updateGiangVien(Long id, GiangVien updated) {
    GiangVien gv = giangVienRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy giảng viên"));

    gv.setTen(updated.getTen());
    gv.setNamSinh(updated.getNamSinh());
    gv.setTrinhDo(updated.getTrinhDo());
    gv.setChucDanh(updated.getChucDanh());

    giangVienRepository.save(gv);
}

    @Override
    public void addGiangVien(GiangVien giangVien) {
        giangVien.setStatus(true);
        giangVienRepository.save(giangVien);
    }


}
