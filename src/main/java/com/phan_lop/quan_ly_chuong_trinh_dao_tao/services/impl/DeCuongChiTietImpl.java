package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.DeCuongChiTietDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.DeCuongChiTiet;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.DeCuongChiTietRepo;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.HocPhanRepo;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.DeCuongChiTietService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeCuongChiTietImpl implements DeCuongChiTietService {
    private DeCuongChiTietRepo deCuongChiTietRepo;
    private HocPhanRepo hocPhanRepo;
    @Override
    public DeCuongChiTiet findById(Long id) {
        return null;
    }

    @Override
    public List<DeCuongChiTiet> findByHocPhanId(Long id) {
        return deCuongChiTietRepo.findByHocPhanId(id);
    }

    @Override
    public boolean deleteByHocPhanId(Long id) {
        deCuongChiTietRepo.deleteByHocPhanId(id);
        return false;
    }

    @Override
    public boolean create(List<DeCuongChiTietDto> deCuongChiTieDtotList) {
        try {
            String maHocPhan = deCuongChiTieDtotList.get(0).getHocPhan().getMaHocPhan();
            Optional<HocPhan> hocPhanOptional = hocPhanRepo.findByMaHocPhan(maHocPhan);
            if (hocPhanOptional.isEmpty()) {
                throw new IllegalArgumentException("Không tìm thấy mã học phần để thêm de cuong chi tiet : " + maHocPhan);
            }
            if ( findByHocPhanId(hocPhanOptional.get().getId()).size() >= 1) {
                throw new IllegalArgumentException("Mã học phần đã có de cuong chi tiet : " + maHocPhan);
            }
            HocPhan hocPhan = hocPhanOptional.get();
            for (DeCuongChiTietDto item : deCuongChiTieDtotList) {
                DeCuongChiTiet hpEntity = DeCuongChiTiet.builder()
                        .hocPhan(hocPhan)
                        .tenCotDiem(item.getTenCotDiem())
                        .hinhThuc(item.getHinhThuc())
                        .trongSo(item.getTrongSo())
                        .status(true).build();
                DeCuongChiTiet save = deCuongChiTietRepo.save(hpEntity);
            }
            return true;

        } catch (Exception e) {
            System.out.println("createNew DC chi tiet error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean update(Long hocPhanId, List<DeCuongChiTietDto> deCuongChiTietList) {
        try {
            String maHocPhan = deCuongChiTietList.get(0).getHocPhan().getMaHocPhan();
            Optional<HocPhan> hocPhanOptional = hocPhanRepo.findByMaHocPhan(maHocPhan);
            if (hocPhanOptional.isEmpty()) {
                throw new IllegalArgumentException("Không tìm thấy để thêm de cuong chi tiet với mã học phần: " + maHocPhan);
            }
            HocPhan hocPhan = hocPhanOptional.get();
            if ( deCuongChiTietList.isEmpty()) {
                throw new IllegalArgumentException("Nội dung của đề cương chi tiết rỗng: ");
            }
            //delete all de cuong chi tiet old then creat new
            deCuongChiTietRepo.deleteByHocPhanId(hocPhanId);
            for (DeCuongChiTietDto item : deCuongChiTietList) {
                DeCuongChiTiet hpEntity = DeCuongChiTiet.builder()
                        .hocPhan(hocPhan)
                        .tenCotDiem(item.getTenCotDiem())
                        .hinhThuc(item.getHinhThuc())
                        .trongSo(item.getTrongSo())
                        .status(true).build();
                DeCuongChiTiet save = deCuongChiTietRepo.save(hpEntity);
            }
            return true;

        } catch (Exception e) {
            System.out.println("update de cuong chi tiet ERROR : " + e.getMessage());
            throw e;
        }
    }
}
