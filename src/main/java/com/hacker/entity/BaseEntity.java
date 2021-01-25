package com.hacker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 21:02
 * @Version 1.0
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class BaseEntity implements Serializable {
    private Integer id;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
}
