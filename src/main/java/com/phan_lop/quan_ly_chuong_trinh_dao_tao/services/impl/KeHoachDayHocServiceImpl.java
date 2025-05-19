package com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.impl;

import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KeHoachDayHocReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.KhoiKienThucReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.request.NhomKienThucReqDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.KhoiKienThucResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.NhomKienThucResDto;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.entities.*;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.exception.BadRequestException;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.KeHoachDayHocMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.KhoiKienThucMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.mappers.NhomKienThucMapper;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.repositories.*;
import com.phan_lop.quan_ly_chuong_trinh_dao_tao.services.KeHoachDayHocService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KeHoachDayHocServiceImpl implements KeHoachDayHocService {

    private KeHoachDayHocRepository keHoachDayHocRepository;
    private KhoiKienThucRepository khoiKienThucRepository;
    private NhomKienThucRepository nhomKienThucRepository;
    private ThongTinChungRepository thongTinChungRepository;
    private HocPhanRepository hocPhanRepository;
    private KhoiKienThucMapper khoiKienThucMapper;
    private NhomKienThucMapper nhomKienThucMapper;
    private KeHoachDayHocMapper keHoachDayHocMapper;

    @Override
    public KeHoachDayHoc createKeHoachDayHoc(KeHoachDayHocReqDto reqDto) {
        KeHoachDayHoc keHoachDayHoc = keHoachDayHocMapper.toEntity(reqDto);
        HocPhan hocPhan = hocPhanRepository.findById((long)reqDto.getHocPhanId()).orElseThrow(()->new BadRequestException("Hoc phan khong ton tai"));
        NhomKienThuc nhomKienThuc = nhomKienThucRepository.findById((long)reqDto.getNhomKienThucId()).orElseThrow(()->new BadRequestException("Nhom kien thuc khong ton tai"));

        keHoachDayHoc.setHocPhan(hocPhan);
        keHoachDayHoc.setNhomKienThuc(nhomKienThuc);

        keHoachDayHocRepository.save(keHoachDayHoc);
        return keHoachDayHoc;
    }

    @Override
    @Transactional
    public boolean updateKeHoachDayHoc(Long id, KeHoachDayHocReqDto reqDto) {
        KeHoachDayHoc keHoachDayHoc = keHoachDayHocRepository.findById(id)
                    .orElseThrow(()->new BadRequestException("Ke hoach day hoc khong ton tai"));
        NhomKienThuc oldNhomKienThuc = keHoachDayHoc.getNhomKienThuc();
        HocPhan oldHocPhan = keHoachDayHoc.getHocPhan();
        //update cac truong tu dto
        keHoachDayHocMapper.updateEntityFromDto(reqDto, keHoachDayHoc);

        HocPhan hocPhan = hocPhanRepository.findById((long)reqDto.getHocPhanId()).orElseThrow(()->new BadRequestException("Hoc phan khong ton tai"));
        NhomKienThuc nhomKienThuc = nhomKienThucRepository.findById((long)reqDto.getNhomKienThucId())
                    .orElseThrow(()->new BadRequestException("Nhom kien thuc khong ton tai"));

        keHoachDayHoc.setHocPhan(hocPhan);
        keHoachDayHoc.setNhomKienThuc(nhomKienThuc);

        keHoachDayHoc.setHocKy(keHoachDayHocMapper.getStringHocKiFromReqDto(reqDto));

        keHoachDayHocRepository.save(keHoachDayHoc);

        //sau khi save ke hoach day hoc update so tin chi tu chon toi thieu cua nhom kien thuc
        if(oldNhomKienThuc != nhomKienThuc) {
            oldNhomKienThuc.getListKeHoachDayHoc().remove(keHoachDayHoc);
        }
        int soTinChiTuChonToiThieu = oldNhomKienThuc.getSoTinChiTuChonToiThieu();
        int soTinChiTuChon = oldNhomKienThuc.getSoTinChiTuChon();
        if(soTinChiTuChonToiThieu > soTinChiTuChon){
            oldNhomKienThuc.setSoTinChiTuChonToiThieu(soTinChiTuChon);
        }
        nhomKienThucRepository.save(nhomKienThuc);

        return true;
    }


    @Override
    @Transactional
    public boolean deleteKeHoachDayHoc(Long id) {
        KeHoachDayHoc keHoachDayHoc = keHoachDayHocRepository.findById(id).orElseThrow(
                ()->new BadRequestException("Ke hoach day hoc khong ton tai"));
        //update so tin chi tu chon toi thieu
        NhomKienThuc nhomKienThuc = keHoachDayHoc.getNhomKienThuc();
        nhomKienThuc.getListKeHoachDayHoc().remove(keHoachDayHoc);
        int soTinChiTuChonToiThieu = nhomKienThuc.getSoTinChiTuChonToiThieu();
        int soTinChiTuChon = nhomKienThuc.getSoTinChiTuChon();
        if(soTinChiTuChonToiThieu > soTinChiTuChon){
            nhomKienThuc.setSoTinChiTuChonToiThieu(soTinChiTuChon);
        }
        nhomKienThucRepository.save(nhomKienThuc);

        keHoachDayHocRepository.deleteById(id);
        return true;
    }

    @Override
    public KhoiKienThuc createKhoiKienThuc(KhoiKienThucReqDto khoiKienThucDto) {
        KhoiKienThuc khoiKienThuc = khoiKienThucMapper.toCreateEntity(khoiKienThucDto);
        ThongTinChung thongTinChung = thongTinChungRepository.findThongTinChungById(khoiKienThucDto.getThongTinChungId())
                .orElseThrow(()->new BadRequestException("Thong tin chung khong ton tai"));

        khoiKienThuc.setThongTinChung(thongTinChung);
        khoiKienThuc.setListNhomKienThuc(List.of());
        try {
            khoiKienThucRepository.save(khoiKienThuc);
            return khoiKienThuc;
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new BadRequestException("Lỗi unique key");
        }
    }

    @Override
    public boolean updateKhoiKienThuc(Long id, KhoiKienThucReqDto khoiKienThucDto) {
        KhoiKienThuc khoiKienThuc = khoiKienThucRepository.findById(id).orElseThrow(()-> new BadRequestException("Khoi kien thuc khong ton tai"));
        khoiKienThucMapper.updateEntityFromDto(khoiKienThucDto, khoiKienThuc);
        ThongTinChung thongTinChung = thongTinChungRepository.findThongTinChungById(khoiKienThucDto.getThongTinChungId())
                .orElseThrow(()-> new BadRequestException("Thong tin chung khong ton tai"));
        khoiKienThuc.setThongTinChung(thongTinChung);
        try {
            khoiKienThucRepository.save(khoiKienThuc);
            return true;
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new BadRequestException("Lỗi unique key");
        }
    }

    @Override
    public boolean deleteKhoiKienThuc(Long khoiKienThucId) {
        khoiKienThucRepository.findById(khoiKienThucId).orElseThrow(()->new BadRequestException("Khoi kien thuc khong ton tai"));
        khoiKienThucRepository.deleteById(khoiKienThucId);
        return true;
    }

    @Override
    public NhomKienThuc createNhomKienThuc(NhomKienThucReqDto nhomKienThucDto) {
        NhomKienThuc nhomKienThuc = nhomKienThucMapper.toCreateEntity(nhomKienThucDto);
        KhoiKienThuc khoiKienThuc = khoiKienThucRepository.findById((long)nhomKienThucDto.getKhoiKienThucId()).orElseThrow(()-> new BadRequestException("Khoi kien thuc khong ton tai"));
        nhomKienThuc.setKhoiKienThuc(khoiKienThuc);
        try {
            nhomKienThucRepository.save(nhomKienThuc);
            return nhomKienThuc;
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Lỗi unique key");
        }
    }

    @Override
    public boolean updateNhomKienThuc(Long id, NhomKienThucReqDto nhomKienThucDto) {
        NhomKienThuc nhomKienThuc = nhomKienThucRepository.findById(id).orElseThrow(()-> new BadRequestException("Nhom kien thuc khong ton tai"));
        nhomKienThucMapper.updateEntityFromDto(nhomKienThucDto, nhomKienThuc);

        int soTinChiTuChon = nhomKienThuc.getSoTinChiTuChon();
        if(nhomKienThucDto.getSoTinChiTuChonToiThieu() > soTinChiTuChon) {
            throw new BadRequestException("Số tín chỉ tự chọn tối thiểu vượt quá số tín chỉ tự chọn hiện có!");
        }

        KhoiKienThuc khoiKienThuc = khoiKienThucRepository.findById((long)nhomKienThucDto.getKhoiKienThucId()).orElseThrow(()-> new BadRequestException("Khoi kien thuc khong ton tai"));
        nhomKienThuc.setKhoiKienThuc(khoiKienThuc);

        try {
            nhomKienThucRepository.save(nhomKienThuc);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Lỗi unique key");
        }
    }


    @Override
    public boolean deleteNhomKienThuc(Long nhomKienThucId) {
        nhomKienThucRepository.findById(nhomKienThucId).orElseThrow(()->new BadRequestException("Nhom kien thuc khong ton tai"));
        nhomKienThucRepository.deleteById(nhomKienThucId);
        return true;
    }

    @Override
    public List<KhoiKienThucResDto> getAllKhoiKienThuc() {
        return khoiKienThucMapper.toListDto(khoiKienThucRepository.findAll());
    }

    @Override
    public List<NhomKienThucResDto> getAllNhomKienThuc() {
        return nhomKienThucMapper.toListDto(nhomKienThucRepository.findAll());
    }
}
