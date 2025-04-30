package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.NganhDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.NganhService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/nganh")
@AllArgsConstructor
public class NganhController {

    private NganhService nganhService;

    @GetMapping
    public ResponseEntity<?> getMethodName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam MultiValueMap<String, String> params) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestBody String entity) {
        return null;
    }

    @GetMapping("{maNganh}")
    public ResponseEntity<?> getByMaNganh(@PathVariable String maNganh) {
        return new ResponseEntity<>(nganhService.getByMaNganh(maNganh), HttpStatusCode.valueOf(200));
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
