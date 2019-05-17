package com.syz.globalexception.handler.service;

import com.syz.globalexception.handler.Exception.MyCustomException;
import com.syz.globalexception.handler.responseBody.ExceptionResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TestService {

    public int add() {
        try {
            int i = 3;
            int j = 0;
            int c = i / j;
        } catch (Exception e) {
            throw new MyCustomException(11, "服务异常！");
        }

        return 0;
    }
}
