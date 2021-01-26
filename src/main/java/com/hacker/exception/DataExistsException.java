package com.hacker.exception;

/**
 * @Description 数据已存在异常
 * @Author mr.mao_hacker
 * @Date 2021/1/26
 * @Version 1.0
 */
public class DataExistsException extends HttpException {

    public DataExistsException(int code) {
        super();
        this.code = code;
    }
}
