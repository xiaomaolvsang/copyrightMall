/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.copyright.mall.message;

import java.io.Serializable;

/**
 * controller Resp
 * <p>
 * 范型 主要包装业务参数序列化
 *
 * @param <T>
 */
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 4945113432114845513L;
    /**
     * 错误码
     */
    private long errorCode = 0;
    private String errorMessage = "成功";
    private T data;

    public ApiResult() {
    }

    public ApiResult(T data) {
        this.data = data;
    }

    public ApiResult(long errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApiResult(long errorCode, String errorMessage, T data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApiResult{");
        sb.append("errorCode=").append(errorCode);
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
