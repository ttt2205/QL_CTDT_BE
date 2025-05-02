package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import java.lang.StackWalker.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers.DeCuongChiTietController;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.controllers.ThongTinChungController;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ChuongTrinhKhungDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.ChuongTrinhKhungDto.NhomKienThucChuongTrinhKhung;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.HocPhan;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KeHoachDayHoc;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.KhoiKienThuc;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.Nganh;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.NhomKienThuc;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.ThongTinChung;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.requests.ThongTinChungRequest;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exceptions.CustomException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.HocPhanRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.KeHoachDayHocRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.KhoiKienThucRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.NganhRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.NhomKienThucRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.ThongTinChungRepository;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.specification.ThongTinChungSpecification;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.ThongTinChungService;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.utils.UrlParamUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ThongTinChungServiceImpl implements ThongTinChungService {

    private final UrlParamUtil urlParamUtil;

    private final ThongTinChungRepository thongTinChungRepository;

    private final NganhRepository nganhRepository;

    private final KhoiKienThucRepository khoiKienThucRepository;

    private final NhomKienThucRepository nhomKienThucRepository;

    private final HocPhanRepository hocPhanRepository;

    private final KeHoachDayHocRepository keHoachDayHocRepository;

    @Override
    public List<ThongTinChung> findAll() {
        return thongTinChungRepository.findAll();
    }

    @Override
    public Page<ThongTinChung> findAll(int page, int size, MultiValueMap<String, String> params) {
        if (params.get("page") != null)
            params.remove("page");
        if (params.get("size") != null)
            params.remove("size");

        // Lấy danh sách tham số `sort` co dang [[id,asc],[name,desc]]
        List<String> sortParams = params.get("sort");
        Sort sorting = urlParamUtil.getSort(sortParams);
        Pageable pageable = PageRequest.of(page, size, sorting);
        Specification<ThongTinChung> spec = ThongTinChungSpecification.byFilters(params);
        return thongTinChungRepository.findAll(spec, pageable);
    }

    @Override
    public ThongTinChung findThongTinChungyById(Long id) {
        return thongTinChungRepository.findThongTinChungById(id).orElseThrow(() -> {
            throw CustomException.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Thong tin chung not found!")
                    .build();
        });
    }

    @Override
    public ThongTinChung createNewThongTinChung(ThongTinChungRequest thongTinChungRequest) {
        try {
            // Find nganh with id
            Nganh nganh = nganhRepository.findNganhById(thongTinChungRequest.getNganhId()).orElseThrow(() -> {
                throw CustomException.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Nganh not found!")
                        .build();
            });

            ThongTinChung thongTinChungEntity = ThongTinChung.builder()
                    .banHanh(thongTinChungRequest.getBanHanh())
                    .khoaQuanLy(thongTinChungRequest.getKhoaQuanLy())
                    .loaiBang(thongTinChungRequest.getLoaiBang())
                    .loaiHinhDaoTao(thongTinChungRequest.getLoaiHinhDaoTao())
                    .ngonNgu(thongTinChungRequest.getNgonNgu())
                    .thoiGianDaoTao(thongTinChungRequest.getThoiGianDaoTao())
                    .website(thongTinChungRequest.getWebsite())
                    .nganh(nganh)
                    .tongTinChi(thongTinChungRequest.getTongTinChi())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .status(thongTinChungRequest.isStatus())
                    .build();
            return thongTinChungRepository.save(thongTinChungEntity);
        } catch (Exception e) {
            System.out.println("createNewThongTinChung error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ThongTinChung updateThongTinChung(Long id, ThongTinChungRequest thongTinChungRequest) {
        try {
            // Check thong tin chung exist
            ThongTinChung thongTinChungIsExist = thongTinChungRepository.findThongTinChungById(id).orElseThrow(() -> {
                throw CustomException.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Thong tin chung not found!")
                        .build();
            });

            // Find nganh with id
            Nganh nganh = nganhRepository.findNganhById(thongTinChungRequest.getNganhId()).orElseThrow(() -> {
                throw CustomException.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Nganh not found!")
                        .build();
            });

            thongTinChungIsExist.setBanHanh(thongTinChungRequest.getBanHanh());
            thongTinChungIsExist.setKhoaQuanLy(thongTinChungRequest.getKhoaQuanLy());
            thongTinChungIsExist.setLoaiBang(thongTinChungRequest.getLoaiBang());
            thongTinChungIsExist.setLoaiHinhDaoTao(thongTinChungRequest.getLoaiHinhDaoTao());
            thongTinChungIsExist.setNgonNgu(thongTinChungRequest.getNgonNgu());
            thongTinChungIsExist.setThoiGianDaoTao(thongTinChungRequest.getThoiGianDaoTao());
            thongTinChungIsExist.setWebsite(thongTinChungRequest.getWebsite());
            thongTinChungIsExist.setNganh(nganh);
            thongTinChungIsExist.setTongTinChi(thongTinChungRequest.getTongTinChi());
            thongTinChungIsExist.setCreatedAt(LocalDateTime.now());
            thongTinChungIsExist.setUpdatedAt(LocalDateTime.now());
            thongTinChungIsExist.setStatus(thongTinChungRequest.isStatus());

            return thongTinChungRepository.save(thongTinChungIsExist);
        } catch (Exception e) {
            System.out.println("updateThongTinChung error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ThongTinChung deleteThongTinChung(Long id) {
        try {
            // Check thong tin chung exist
            ThongTinChung thongTinChungIsExist = thongTinChungRepository.findThongTinChungById(id).orElseThrow(() -> {
                throw CustomException.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Thong tin chung not found!")
                        .build();
            });

            thongTinChungIsExist.setDeleted(true);
            return thongTinChungRepository.save(thongTinChungIsExist);
        } catch (Exception e) {
            System.out.println("deleteThongTinChung error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<ChuongTrinhKhungDto> getChuongTrinhKhungByThongTinChungId(Long id) {
        try {
            List<ChuongTrinhKhungDto> response = new ArrayList<>();

            List<KhoiKienThuc> listKhoiKienThuc = khoiKienThucRepository.findAllByThongTinChungId(id);

            if (listKhoiKienThuc.isEmpty()) {
                return response;
            }

            // Lặp qua khối kiến thức để tổng hợp chương trình khung
            listKhoiKienThuc.forEach(khoiKienThuc -> {
                List<NhomKienThuc> listNhomKienThucByKhoiKienThuc = khoiKienThuc.getListNhomKienThuc();

                if (listNhomKienThucByKhoiKienThuc.isEmpty()) {
                    response.add(ChuongTrinhKhungDto.builder()
                            .tenKhoiKienThuc(khoiKienThuc.getName())
                            .nhomKienThucChuongOfKhoiKienThuc(List.of())
                            .build());
                } else {
                    List<NhomKienThucChuongTrinhKhung> listNhomKienThucChuongTrinhKhungs = new ArrayList<>();

                    for (NhomKienThuc nhomKienThuc : listNhomKienThucByKhoiKienThuc) {
                        List<KeHoachDayHoc> listKeHoachDayHocByNhomKienThuc = nhomKienThuc.getListKeHoachDayHoc();

                        if (listKeHoachDayHocByNhomKienThuc.isEmpty()) {
                            listNhomKienThucChuongTrinhKhungs.add(NhomKienThucChuongTrinhKhung.builder()
                                    .tenNhom(nhomKienThuc.getTenNhom())
                                    .tichLuy(nhomKienThuc.isTichLuy())
                                    .soTinChiBatBuoc(0)
                                    .soTinChiTuChon(0)
                                    .build());
                        } else {
                            int soTinChiBatBuoc = 0;
                            int soTinChiTuChon = 0;
                            for (KeHoachDayHoc keHoachDayHoc : listKeHoachDayHocByNhomKienThuc) {
                                HocPhan hocPhanByIdOfKeHoachDayHoc = keHoachDayHoc.getHocPhan();
                                if (hocPhanByIdOfKeHoachDayHoc != null) {
                                    if (!keHoachDayHoc.isBatBuoc()) {
                                        soTinChiTuChon += hocPhanByIdOfKeHoachDayHoc.getSoTinChi();
                                    }
                                    soTinChiBatBuoc += hocPhanByIdOfKeHoachDayHoc.getSoTinChi();
                                }
                            }

                            listNhomKienThucChuongTrinhKhungs.add(NhomKienThucChuongTrinhKhung.builder()
                                    .tenNhom(nhomKienThuc.getTenNhom())
                                    .tichLuy(nhomKienThuc.isTichLuy())
                                    .soTinChiBatBuoc(
                                            soTinChiBatBuoc)
                                    .soTinChiTuChon(
                                            soTinChiTuChon)
                                    .build());
                        }

                    }

                    response.add(ChuongTrinhKhungDto.builder()
                            .tenKhoiKienThuc(khoiKienThuc.getName())
                            .nhomKienThucChuongOfKhoiKienThuc(
                                    listNhomKienThucChuongTrinhKhungs)
                            .build());
                }
            });

            return response;
        } catch (Exception e) {
            System.out.println("getChuongTrinhKhungByThongTinChungId error: " + e.getMessage());
            throw e;
        }
    }

}
