package com.hacker.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/26 21:39
 * @Version 1.0
 */
@Setter
@Getter
@NoArgsConstructor
public class CaptchaDTO {

    @NotEmpty(message = "请填写手机号")
    @Size(max = 11, min = 11, message = "手机号格式错误！")
    @Pattern(regexp = "^[1][3,4,5,7,8,9][0-9]{9}$", message = "不合法的手机号！")
    private String phoneNumber;

    @NotNull(message = "验证码类型不能为空")
    private Integer type;

}
