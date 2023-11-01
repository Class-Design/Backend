package com.example.demo.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
public class Result<D> implements Serializable {

    @JsonProperty("isSuccess")
    private boolean success = false;

    private String code;
    private String message;

    private D data;

    public static <T> Result<T> create() {
        return new Result<T>();
    }

    public boolean isSuccess() {
        return success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Result<D> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<D> setMessage(String message) {
        this.message = message;
        return this;
    }

    public D getData() {
        return data;
    }

    public Result<D> setData(D data) {
        this.data = data;
        return this;
    }
}
