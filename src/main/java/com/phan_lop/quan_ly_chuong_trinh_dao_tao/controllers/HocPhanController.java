package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.HocPhanResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.HocPhanMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.HocPhanService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hoc-phan")
@AllArgsConstructor
public class HocPhanController {

    @Qualifier("hocPhanMapperImpl")
    private HocPhanMapper mapper;
    private HocPhanService hocPhanService;

    @GetMapping
    public ResponseEntity<List<HocPhanResDto>> getAll() {
        return ResponseEntity.ok(mapper.toListDto(hocPhanService.getAll()));
    }

    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestBody String entity) {
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getByIdMethodName(@PathVariable Long id) {
        return null;
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putMethodName(@PathVariable Long id, @RequestBody String entity) {
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMethodName(@PathVariable Long id) {
        return null;
    }
}
