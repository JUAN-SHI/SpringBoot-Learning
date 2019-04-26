package com.springboot.Dto;

/**
 * Created by shijuan on 2018/1/30.
 */
public class ErrorInfo<T> {
    public static final Integer OK=0;
    public static final Integer ERROr=100;
    private Integer code;
    private String  message;
    private String url;
    private T data;

    public static Integer getOK() {
        return OK;
    }

    public static Integer getERROr() {
        return ERROr;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
