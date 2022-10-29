package com.truly.mall.controller;

import com.truly.mall.common.CommonResult;
import com.truly.mall.dto.UmsAdminLoginParam;
import com.truly.mall.mbg.model.UmsAdmin;
import com.truly.mall.mbg.model.UmsPermission;
import com.truly.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author truly
 * @date 2022/10/24 22:48
 * 后台用户管理
 */
@RestController
@Api(tags = "UmsAdminController",description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public CommonResult register(@RequestBody UmsAdmin umsAdminParam){
       UmsAdmin umsAdmin = adminService.register(umsAdminParam);
       if (umsAdmin!=null){
           CommonResult.success(umsAdmin);
       }
       return CommonResult.failed();
    }

    @ApiOperation("登录后返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result){
      String token= adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
      if (token==null){
          return CommonResult.validateFailed("用户名或密码错误！");
      }
        HashMap<String, String> tokenMap = new HashMap<>();
      tokenMap.put("token",token);
      tokenMap.put("tokenHead",tokenHead);
      return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取用户的所有权限")
    @GetMapping("/permission/{adminId}")
    public CommonResult getPermissionList(@PathVariable("adminId")Long adminId){
        List<UmsPermission> permissionList=adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
