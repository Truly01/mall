package com.truly.mall.service;

import com.truly.mall.common.CommonResult;
import org.springframework.stereotype.Service;

/**
 * @author truly
 * @date 2022/10/23 0:13
 * 会员登录注册服务
 */
@Service
public interface UmsMeberServcie {

  /**
     * 生成验证码
     * @param tel
     * @return
     */
    CommonResult generateAuthCode(String tel);

    /**
     * 判断验证码是否和手机号匹配
     * @param tel
     * @param code
     * @return
     */
    CommonResult verifyAuthCode(String code,String tel);

}
