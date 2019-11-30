package com.copyright.mall.util.wrapper;

import java.io.Serializable;

/**
 * @author : zhangyuchen
 * @date : 2019/10/22 14:16
 */
public class Wrapper<T> implements Serializable {
    private static final long serialVersionUID = 4893280118017319089L;
    public static final int SUCCESS_CODE = 0;
    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final int ERROR_CODE = 500;
    public static final String ERROR_MESSAGE = "操作失败";
    public static final int ILLEGAL_ARGUMENT_CODE_ = 100;
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";
    private int code;
    private String message;
    private T result;

    Wrapper() {
        this(0, "操作成功");
    }

    Wrapper(int code, String message) {
        this(code, message, null);
    }

    Wrapper(int code, String message, T result) {
        this.code(code).message(message).result(result);
    }

    private Wrapper<T> code(int code) {
        this.setCode(code);
        return this;
    }

    private Wrapper<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public Wrapper<T> result(T result) {
        this.setResult(result);
        return this;
    }

    public boolean success() {
        return 0 == this.code;
    }

    public boolean error() {
        return !this.success();
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getResult() {
        return this.result;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
