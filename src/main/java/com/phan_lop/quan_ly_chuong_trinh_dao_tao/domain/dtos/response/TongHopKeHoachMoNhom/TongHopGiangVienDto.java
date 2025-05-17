package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos.response.TongHopKeHoachMoNhom;

import lombok.Data;

@Data
public class TongHopGiangVienDto {
//    {
//        "maCB": "11381",
//            "hoVaTen": "Phạm Thế Bảo",
//            "namSinh": 1972,
//            "chucDanh": "PGS. TS, GVCC"
//    }
    public int maGiangVien; //lay tam id
    public String hoVaTen;
    public int namSinh;
    public String chucDanh;
}
