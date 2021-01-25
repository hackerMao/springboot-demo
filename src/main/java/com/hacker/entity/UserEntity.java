package com.hacker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 20:58
 * @Version 1.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    private Integer loginId;
    private String ddId;
    private String nickname;
    private String realName;
    private Integer gender;
    private Date birthday;
    private String idCard;
    private String phoneNumber;
    private String avatarUrl;
    private String signature;
    private Integer identity;
    private String backgroundUrl;
    private String emergencyContact;
    private String emergencyContactPhone;
    private Boolean isRealName;
    private Boolean hasChecking;
}
