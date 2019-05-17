package com.syz.globalexception.handler.Exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.syz.globalexception.handler.utils.PropertiesUtil;


@JsonIgnoreProperties({"stackTrace"})
public class BadRequestException extends HystrixBadRequestException {
    private static String DEFAULT_FAILTURE = "failture";
    private int code;
    private String message;
    private String serviceId;

    public BadRequestException() {
        super(DEFAULT_FAILTURE);
        this.code = -1;
    }

    public BadRequestException(String errorKey) {
        this(PropertiesUtil.getErrCode(errorKey), PropertiesUtil.getErrMsg(errorKey));
    }

    public BadRequestException(String errorKey, Object... obj) {
        this(PropertiesUtil.getErrCode(errorKey), PropertiesUtil.getErrMsg(errorKey, obj));
    }

    public BadRequestException(int errorCode, String message) {
        super(message);
        this.code = -1;
        this.code = errorCode;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
