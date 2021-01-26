package com.hacker.core;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @Author mr.mao_hacker
 * @Date 2021/1/26
 * @Version 1.0
 */
@Getter
@Setter
public class UnifyErrorResponse {

    private int code;

    private String message;

    private String request;

    private long timestamp;

    public UnifyErrorResponse(int code, String message, String request, long timestamp) {
        this.code = code;
        this.message = message;
        this.request = request;
        this.timestamp = timestamp;
    }
}
