package com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.GiangVienDetailsResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.GiangVienDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phanconggiangday")
public class GiangVienDetailsController {

    @Autowired
    private GiangVienDetailsService service;

    @GetMapping("/{idGV}")
    public List<GiangVienDetailsResDto> getPhanCongByGiangVien(@PathVariable Long idGV) {
        return service.getPhanCongByGiangVienId(idGV);
    }
}
