package com.hacker.vo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 21:19
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO<T> implements Serializable {
    private Integer code;

    private String message;

    private T data;

    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<T>(0, "成功！", data);
    }
}
