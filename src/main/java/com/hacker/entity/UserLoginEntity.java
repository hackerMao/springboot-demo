package com.hacker.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 21:09
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user_login")
public class UserLoginEntity extends BaseEntity {
    private String phoneNumber;
    private String password;
    private String salt;
    private String openid;
    private String appleId;
    private Integer status;
}
