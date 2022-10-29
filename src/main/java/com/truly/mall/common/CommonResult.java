package com.truly.mall.common;

import lombok.Data;

/**
 * @author truly
 * @date 2022/10/22 12:52
 * 返回的通用类
 */
@Data
public class  CommonResult {
    private long code;
    private String message;
    private Object data;
    protected CommonResult(long code,String message,Object data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    /***
     * 成功返回结果
     * @param data
     * @return
     */
    public static CommonResult success(Object data){
        return new CommonResult(ResultCode.SUCCESS.getCode(),null,data);
    }
    /***
     * 成功返回结果
     * @param data
     * @param message 提示消息
     * @return
     */
    public static CommonResult success(Object data,String message){
        return new CommonResult(ResultCode.SUCCESS.getCode(),message,data);
    }
    /***
     * 失败返回结果
     * @param errocode 错误码
     * @return
     */
    public static CommonResult failed(IErrCode errocode){
        return new CommonResult(errocode.getCode(),null,null);
    }
    /***
     * 失败返回结果
     * @param message 错误提示信息
     * @return
     */
    public static CommonResult failed(String message){
        return new CommonResult(ResultCode.FAILED.getCode(),message,null);
    }
    /***
     * 失败返回结果
     * @param
     * @return
     */
    public static CommonResult failed(){
        return failed(ResultCode.FAILED);
    }
    /***
     * 参数验证失败返回结果
     * @param
     * @return
     */
    public static CommonResult validateFailed(){
        return failed(ResultCode.VALIDATE_FAILED);
    }
    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static CommonResult validateFailed(String message) {
        return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(), message, null);

    }

    /**
     * 未登录返回结果
     */
    public static CommonResult unauthorized(Object data) {
        return new CommonResult(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static CommonResult forbidden(Object data) {
        return new CommonResult(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

}
