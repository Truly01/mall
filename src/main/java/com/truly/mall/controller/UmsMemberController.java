package com.truly.mall.controller;

import com.truly.mall.common.CommonResult;
import com.truly.mall.service.UmsMeberServcie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author truly
 * @date 2022/10/23 0:09\
 * 会员登录注册管理
 */
@RestController
@RequestMapping("/sso")         //如果用RestController("/sso")会报错nested exception is java.lang.NumberFormatException: For input string: “swagger-ui”
@Api(tags = "UmsMemberController",description = "会员登录注册管理")
public class UmsMemberController {
    @Autowired
    private UmsMeberServcie umsMeberServcie;

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode",method = RequestMethod.GET)
    public CommonResult getAuthCode(@RequestParam String tel){
       return umsMeberServcie.generateAuthCode(tel);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode",method = RequestMethod.POST)
    public CommonResult verifyAuthCode(@RequestParam String code,@RequestParam String tel){
        return umsMeberServcie.verifyAuthCode(code,tel);
    }
}
