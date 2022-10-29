package com.truly.mall.service.impl;

import com.truly.mall.mbg.model.PmsBrand;
import com.truly.mall.service.PmsBrandService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author truly
 * @date 2022/10/22 23:05
 */
@SpringBootTest
public class PmsServiceImplTest {
    @Autowired
    private PmsBrandService pmsBrandService;
    @Test
    public void pmsTest(){
        List<PmsBrand> pmsBrands = pmsBrandService.listAllBrand();
        System.out.println(pmsBrands);
    }
}
