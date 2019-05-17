package com.syz.globalexception.handler.handler;

import com.syz.globalexception.handler.Exception.MyCustomException;
import com.syz.globalexception.handler.responseBody.ExceptionResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *@ControllerAdvice 捕获 Controller 层抛出的异常，如果添加 @ResponseBody 返回信息则为JSON 格式。
  @RestControllerAdvice 相当于 @ControllerAdvice 与 @ResponseBody 的结合体。
  @ExceptionHandler 统一处理一种类的异常，减少代码重复率，降低复杂度。


 因为我们这里全部异常信息都约定返回json，所以直接使用 @RestControllerAdvice 代替 @ControllerAdvice ，这样在方法上就可以不需要添加 @ResponseBody了

 步骤：
 1.创建一个 GlobalExceptionHandler 类，并添加上 @RestControllerAdvice 注解就可以实现异常通知类的定义了
 2.定义的方法中添加上 @ExceptionHandler 即可实现Controller层的异常捕捉
 *
 */

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 如果需要捕获多个异常   定义如下：@ExceptionHandler({})
     *
     * @param request
     * @param e
     * @param response
     * @return
     */
    // 捕获多个异常的写法
    @ExceptionHandler({MyCustomException.class,MyCustomException.class})
    public ExceptionResponseEntity customExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        MyCustomException exception = (MyCustomException) e;
        logger.info("MyCustomException");
        return new ExceptionResponseEntity(exception.getCode(), exception.getMessage());
    }

    /**
     *  捕获  RuntimeException 异常
     *  如果在一个 exceptionHandler 通过  if (e instanceof xxxException) 太麻烦，
     *  可以写多个方法标注@ExceptionHandler处理不同的异常
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @ExceptionHandler(RuntimeException.class)
    public ExceptionResponseEntity runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        RuntimeException exception = (RuntimeException) e;
        logger.info("RuntimeException");
        return new ExceptionResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

}
