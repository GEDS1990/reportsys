package com.midnight.reportsys.exception;

/**
 * 自定义异常类
 * @author Midnight
 * @create 2016-08-20 19:58
 */
public class CustomException extends Exception{
    //异常消息
    private String message;
    public CustomException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}