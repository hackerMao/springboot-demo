package com.hacker.core.enumeration;

import java.util.stream.Stream;

/**
 * @Description
 * @Author mr.mao_hacker
 * @Date 2021/1/26
 * @Version 1.0
 */
public enum CaptchaType {

    REGISTER(1, "register"),
    LOGIN(2, "login"),
    MODIFY_PHONE(3, "modify_phone"),
    MODIFY_PWD(4, "modify_pwd"),
    RESET_PWD(5, "reset_pwd"),
    BIND_PHONE(6, "bind_phone"),
    PAY(7, "pay");

    private int value;

    private String text;

    CaptchaType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static CaptchaType toType(int value) {
        return Stream.of(CaptchaType.values())
                .filter(c -> c.value == value)
                .findAny()
                .orElse(null);
    }
}
