package com.hacker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 22:08
 * @Version 1.0
 */

@Getter
@Setter
@ToString
public class RegisterByPwdDTO {

    @NotEmpty(message = "请填写手机号")
    @Size(max = 11, min = 11, message = "手机号格式错误！")
    @Pattern(regexp = "^[1][3,4,5,7,8,9][0-9]{9}$", message = "不合法的手机号！")
    private String phoneNumber;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 20, min = 6, message = "密码长度介于6~20之间！")
    private String password;

    @NotEmpty(message = "验证码错误")
    private String captcha;
}
