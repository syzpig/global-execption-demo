package com.syz.globalexception.handler.Exception;

/**
 * 需要继承RuntimeException。
 * 另外Spring 对于 RuntimeException类型的 异常才会进行事务回滚
 * @author yangshangwei
 *
 */
public class MyCustomException extends RuntimeException {

    private static final long serialVersionUID = 8863339790253662109L;

    private int code ;

    private String message;

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public MyCustomException() {
        super();
    }

    public MyCustomException(int code , String message) {
        this.message = message;
        this.code = code;
    }

}
