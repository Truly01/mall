package com.truly.mall.controller;

import com.truly.mall.common.CommonResult;
import com.truly.mall.mbg.model.PmsBrand;
import com.truly.mall.service.PmsBrandService;
import com.truly.mall.service.impl.PmsBrandServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author truly
 * @date 2022/10/21 21:38
 * 品牌管理Controller
 */
@Api(tags = "PmsController",description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    private static final Logger LOGGER= LoggerFactory.getLogger(PmsBrandController.class);
    @Autowired
    private PmsBrandService pmsBrandService;

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("获取所有品牌列表")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public CommonResult getBrandList(){
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult create(@RequestBody PmsBrand pmsBrand){
        CommonResult commonResult;
       int result = pmsBrandService.createBrand(pmsBrand);
       if (result==1){
           commonResult=CommonResult.success(pmsBrand);
           LOGGER.debug("createBrand success:{}",pmsBrand);
       }else {
           commonResult=CommonResult.failed("操作失败");
           LOGGER.debug("createBrand failed:{}",pmsBrand);
       }
       return commonResult;
   }

   @ApiOperation("根据ID更新品牌")
   @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
   public CommonResult update(@PathVariable("id") Long id,@RequestBody PmsBrand pmsBrand){
        CommonResult commonResult;
       int result = pmsBrandService.updateBrand(id, pmsBrand);
       if (result>0){
           commonResult=CommonResult.success(pmsBrand);
           LOGGER.debug("updateBrand sucess:{}",pmsBrand);
       }else {
           commonResult=CommonResult.failed("操作失败");
           LOGGER.debug("updateBrand failed:{}",pmsBrand);
       }
       return commonResult;
   }

   @ApiOperation("根据ID删除品牌")
   @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
   public CommonResult delete(@PathVariable("id") Long id){
        CommonResult commonResult;
       int result = pmsBrandService.deleteBrand(id);
       if (result>0){
           commonResult=CommonResult.success(null);
           LOGGER.debug("deleteBrand sucess:id={}",id);
       }else {
           commonResult=CommonResult.failed("操作失败");
           LOGGER.debug("deleteBrand failed:id={}",id);
       }
       return commonResult;
   }

   @ApiOperation("分页查询品牌列表")
   @RequestMapping(value = "/list",method = RequestMethod.GET)
   public CommonResult list(
                            @RequestParam(value = "pageNum", defaultValue = "1")
                            @ApiParam("页码") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "3")
                            @ApiParam("每页数量") Integer pageSize){
       List<PmsBrand> pmsBrands = pmsBrandService.listBrand(pageNum, pageSize);
       return CommonResult.success(pmsBrands);
   }

   @ApiOperation("根据ID获取品牌详情")
   @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CommonResult brand(@PathVariable("id") Long id){
       PmsBrand brand = pmsBrandService.getBrand(id);
       return CommonResult.success(brand);
   }
}
