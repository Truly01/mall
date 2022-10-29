package com.truly.mall.service.impl;

import com.truly.mall.dao.EsProductDao;
import com.truly.mall.es.document.EsProduct;
import com.truly.mall.es.repository.EsProductRepository;
import com.truly.mall.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author truly
 * @date 2022/10/27 23:34
 * 商品搜索管理实现类
 */
@Service
public class EsProductServiceImpl implements EsProductService {
    @Autowired
    private EsProductDao productDao;
    @Autowired
    private EsProductRepository esProductRepository;

    @Override
    public int importAll() {
        List<EsProduct> esProducts = productDao.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = esProductRepository.saveAll(esProducts);
        Iterator<EsProduct> it = esProductIterable.iterator();
        int result = 0;
        while (it.hasNext()) {
            result++;
            it.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        esProductRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct result = null;
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            result = esProductRepository.save(esProduct);
        }
        return result;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            ArrayList<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            esProductRepository.deleteAll(esProductList);
        }
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        PageRequest pageable = PageRequest.of(pageNum, pageSize);
        return esProductRepository.findByNameOrSubTitleOrKeywords(keyword,keyword,keyword,pageable);
    }
}