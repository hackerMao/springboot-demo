package com.hacker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 21:09
 * @Version 1.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserLoginEntity extends BaseEntity {
    private String phoneNumber;
    private String password;
    private String salt;
    private String openid;
    private String appleId;
    private Integer status;
}
