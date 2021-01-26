package com.hacker.exception;

/**
 * @Description
 * @Author mr.mao_hacker
 * @Date 2021/1/26
 * @Version 1.0
 */
public class ForbiddenException extends HttpException {
    public ForbiddenException(int code) {
        super();
        this.code = code;
    }
}
