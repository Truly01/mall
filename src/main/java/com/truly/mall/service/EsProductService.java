package com.truly.mall.service;


import com.truly.mall.es.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author truly
 * @date 2022/10/27 22:54
 */
public interface EsProductService {
    /**
     * 从数据库中导入所以商品到ES
     * @return
     */
    int importAll();

    /**
     * 根据id删除商品
     * @param id
     * @return
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     * @param id
     * @return
     */
    EsProduct create(Long id);

    /**
     * 批量删除商品
     * @param ids
     * @return
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或副标题
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
