package com.truly.mall.controller;

import com.truly.mall.common.CommonResult;
import com.truly.mall.es.document.EsProduct;
import com.truly.mall.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author truly
 * @date 2022/10/28 21:29
 * 搜索商品管理Controller
 */
@RestController
@RequestMapping("/esProduct")
@Api(tags = "EsProductController",description = "搜索商品管理")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    @ApiOperation("导入所有数据库中商品到es")
    @GetMapping("/importAll")
    public CommonResult importAll(){
        int count=esProductService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation("根据id删除商品信息")
    @PostMapping("/delete/{id}")
    CommonResult delete(@PathVariable Long id){
        esProductService.delete(id);
        return CommonResult.success(null);
    }


    @ApiOperation("根据id批量删除商品信息")
    @PostMapping("/delete/batch")
    CommonResult deleteBatch(@RequestParam("ids") List<Long> ids){
        esProductService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation("根据id创建商品")
    @PostMapping("/create/{id}")
    public CommonResult create(@PathVariable Long id){
        EsProduct esProduct = esProductService.create(id);
        if (esProduct!=null){
            return CommonResult.success(esProduct);
        }
        return CommonResult.failed("创建失败");
    }

    @ApiOperation("简单搜索")
    @GetMapping("/search/simple")
    public CommonResult search(@RequestParam(required = false) String keyword,
                               @RequestParam(required = false,defaultValue = "0") Integer pageNum,
                               @RequestParam(required = false,defaultValue = "5")Integer pageSize){
        Page<EsProduct> search = esProductService.search(keyword, pageNum, pageSize);
        return CommonResult.success(search);
    }
}
