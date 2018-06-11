package com.ics.exception;

/**
 * Created by Tony on 2016/05/18.
 */
public class BadRequestException extends RuntimeException {

    private String code;
    private String msg;

    public BadRequestException() {
    }

    public BadRequestException(String code) {
        this.code = code;
    }

    public BadRequestException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
