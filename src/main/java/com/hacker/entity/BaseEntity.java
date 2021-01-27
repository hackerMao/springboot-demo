package com.hacker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 21:02
 * @Version 1.0
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer isDelete;
}
