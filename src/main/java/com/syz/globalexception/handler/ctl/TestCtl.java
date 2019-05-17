package com.syz.globalexception.handler.ctl;

import com.syz.globalexception.handler.Exception.MyCustomException;
import com.syz.globalexception.handler.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestCtl {

    @Autowired
    private TestService testService;
    @GetMapping("add")
    public void add(){
        throw new MyCustomException(11, "服务异常！");
          //testService.add();
    }
}
