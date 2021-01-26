package com.hacker.exception;

import lombok.Getter;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 22:12
 * @Version 1.0
 */
@Getter
public class HttpException extends RuntimeException {
    protected Integer code;
    protected Integer httpStatusCode = 200;
}
