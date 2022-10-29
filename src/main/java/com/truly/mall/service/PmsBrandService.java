package com.truly.mall.service;

import com.truly.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * @author truly
 * @date 2022/10/22 12:45
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
