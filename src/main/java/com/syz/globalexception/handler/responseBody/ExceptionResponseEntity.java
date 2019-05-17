package com.syz.globalexception.handler.responseBody;

/**
 * 统一返回的异常信息的格式
 *
 * @author yangshangwei
 *
 */

public class ExceptionResponseEntity {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionResponseEntity() {

    }

    public ExceptionResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
