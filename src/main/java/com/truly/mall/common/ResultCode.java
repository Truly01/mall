package com.truly.mall.common;

/**
 * @author truly
 * @date 2022/10/22 13:00
 * 枚举了一些常见的操作码
 */
public enum  ResultCode implements IErrCode {
    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    VALIDATE_FAILED(404,"参数验证失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;
    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
