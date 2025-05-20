package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienExportProjection;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.GiangVienReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.GiangVienMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.GiangVienService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/giang-vien")
@RequiredArgsConstructor
public class GiangVienController {

    private final GiangVienService giangVienService;
    private final GiangVienMapper giangVienMapper;

    @GetMapping
    public ResponseEntity<List<GiangVienResDto>> getAll() {
        return ResponseEntity.ok(giangVienService.getAllActiveGiangViens());
    }

    @GetMapping("/by-khoa/{khoa}")
    public ResponseEntity<List<GiangVienResDto>> getByKhoa(@PathVariable String khoa) {
        return ResponseEntity.ok(giangVienService.findByKhoa(khoa));
    }

    @GetMapping("/export/{boMon}")
public ResponseEntity<List<GiangVienExportProjection>> getByBoMon(@PathVariable String boMon) {
        return ResponseEntity.ok(giangVienService.getExportByBoMon(boMon));
    }
    
    @GetMapping("/search/{ten}")
    public ResponseEntity<List<GiangVienResDto>> search(@PathVariable String ten) {
        return ResponseEntity.ok(giangVienService.searchByTen(ten));
    }


    @PostMapping("/them")
    public ResponseEntity<?> addGiangVien(@Valid @RequestBody GiangVienReqDto dto) {
        try {
            giangVienService.addGiangVien(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Thêm giảng viên thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("statusCode", 500, "message", e.getMessage()));
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> softDelete(@PathVariable Long id) {
        giangVienService.deleteSoft(id);
        return ResponseEntity.ok("Đã xoá mềm giảng viên");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateGiangVien(@PathVariable Long id, @RequestBody GiangVien req) {
        giangVienService.updateGiangVien(id, req);
        return ResponseEntity.ok(Map.of("message", "Cập nhật thành công"));
    }

    // ⚠️ Đặt CUỐI CÙNG để tránh xung đột với các route như "/them", "/search/{ten}", ...
@GetMapping("/api/giangvien/{id:\\d+}")
    public ResponseEntity<GiangVien> getById(@PathVariable Long id) {
        return ResponseEntity.ok(giangVienService.findById(id));
    }
}

