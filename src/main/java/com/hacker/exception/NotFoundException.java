package com.hacker.exception;

/**
 * @Description
 * @Author mr.mao_hacker
 * @Date 2021/1/26
 * @Version 1.0
 */
public class NotFoundException extends HttpException {

    public NotFoundException(int code) {
        super();
        this.code = code;
    }
}
