package com.small.business.service.exceptionhandler;

public class ErrorResponse {

    private String code;
    private String msg;

    public ErrorResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {

        this.msg = msg;
    }

}
