package com.phan_lop.quan_ly_chuong_trinh_dao_tao.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NganhDto {
    private Long id;

    private String maNganh;

    private String tenNganh;

    private boolean status;
}
