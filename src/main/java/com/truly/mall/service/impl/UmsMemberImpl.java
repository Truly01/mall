package com.truly.mall.service.impl;

import com.truly.mall.common.CommonResult;
import com.truly.mall.service.RedisService;
import com.truly.mall.service.UmsMeberServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @author truly
 * @date 2022/10/23 0:20
 * 会员管理实现类
 */
@Service
public class UmsMemberImpl implements UmsMeberServcie {

    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long REDIS_KEY_EXPIRE_SECONDS;
    @Override
    public CommonResult generateAuthCode(String tel) {
        StringBuilder sb = new StringBuilder();
        //随机生成验证码
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+tel,sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+tel,REDIS_KEY_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(),"获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String code, String tel) {
        if (StringUtils.isEmpty(code)){
            return CommonResult.failed("请输入验证码");
        }else {
           String realAuthCode= redisService.get(REDIS_KEY_PREFIX_AUTH_CODE+tel);
           if (realAuthCode.equals(code)){
               return CommonResult.success(null,"验证码校验成功");
           }else {
               return CommonResult.failed("验证码不正确");
           }
        }
    }
}
