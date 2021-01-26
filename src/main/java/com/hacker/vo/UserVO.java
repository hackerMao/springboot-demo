package com.hacker.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @Description
 * @Author mr.mao_hacker
 * @Date 2021/1/26
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class UserVO {
    @JsonProperty("userId")
    private Integer id;
    private String ddId;
    private String nickname;
    private Integer gender;
    private Date birthday;
    private String avatarUrl;
    private String signature;
    private Integer identity;
    private String backgroundUrl;
    private String emergencyContact;
    private String emergencyContactPhone;
    private Boolean isRealName;
    private Boolean hasChecking;
}
