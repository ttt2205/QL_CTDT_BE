package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.GiangVien;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.GiangVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/giangvien")
@RequiredArgsConstructor
public class GiangVienController {

    private final GiangVienService giangVienService;

    @GetMapping
    public ResponseEntity<List<GiangVienResDto>> getAll() {
        return ResponseEntity.ok(giangVienService.getAllActiveGiangViens());
    }

    @GetMapping("/by-khoa/{khoa}")
    public ResponseEntity<List<GiangVienResDto>> getByKhoa(@PathVariable String khoa) {
        return ResponseEntity.ok(giangVienService.findByKhoa(khoa));
    }

    @GetMapping("/search/{ten}")
    public ResponseEntity<List<GiangVienResDto>> search(@PathVariable String ten) {
        return ResponseEntity.ok(giangVienService.searchByTen(ten));
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

    @PostMapping("/them")
    public ResponseEntity<String> addGiangVien(@RequestBody GiangVien req) {
        giangVienService.addGiangVien(req);
        return ResponseEntity.ok("Thêm giảng viên thành công");
    }
    
    
        @GetMapping("/{id}")
        public ResponseEntity<GiangVien> getById(@PathVariable Long id) {
            return ResponseEntity.ok(giangVienService.findById(id));
        }
}
