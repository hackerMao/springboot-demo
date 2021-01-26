package com.hacker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Random;

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
public class UserInfoEntity extends BaseEntity {
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

    public void generateDdId(Integer loginId) {
        Random random = new Random();
        Integer num = Math.toIntExact(loginId + 1001);
        String s = String.format("%7d", num).replace(" ", "0");
        this.ddId = 1 + s + random.nextInt(9) % 10;
    }
}
