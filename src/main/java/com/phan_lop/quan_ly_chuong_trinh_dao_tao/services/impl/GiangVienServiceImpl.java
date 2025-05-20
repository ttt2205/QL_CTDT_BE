package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienExportProjection;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.User;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.GiangVienReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.GiangVienMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.GiangVienExportRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.GiangVienRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.UserRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.GiangVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class GiangVienServiceImpl implements GiangVienService {

    private final GiangVienRepository giangVienRepository;
    private final GiangVienMapper giangVienMapper;
    private final UserRepository userRepository;
    private final GiangVienExportRepository giangVienExportRepository;



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
    public List<GiangVienResDto> findByBoMon(String boMon) {
        return giangVienMapper.toListResDto(giangVienRepository.findByStatusTrueAndBoMon(boMon));
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
    public List<GiangVienExportProjection> getExportByBoMon(String boMon) {
        System.out.println("📥 Đang lấy danh sách export theo bộ môn: " + boMon);
        return giangVienExportRepository.getExportByBoMon(boMon);
    }
    
    @Override
    public void addGiangVien(GiangVienReqDto dto) {
        if (giangVienRepository.existsByUserId(dto.getUserId())) {
            throw new RuntimeException("User đã là giảng viên");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        GiangVien gv = new GiangVien();
        gv.setUser(user);
        gv.setTen(dto.getTen());
        gv.setNamSinh(dto.getNamSinh());
        gv.setChucDanh(dto.getChucDanh());
        gv.setBoMon(dto.getBoMon());
        gv.setKhoa(dto.getKhoa());
        gv.setChuyenMon(dto.getChuyenMon());
        gv.setTrinhDo(dto.getTrinhDo());
        gv.setStatus(true);

        giangVienRepository.save(gv);
    }




}
