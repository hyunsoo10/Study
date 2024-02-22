package com.example.kotlin2.presentation.dto;

public class ApiResponse<T> {

    private T data;
    private String msg;

    public ApiResponse(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
