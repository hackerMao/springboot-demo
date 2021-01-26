package com.hacker.mapper;

import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author mr.mao_hacker
 * @Date 2021/1/26
 * @Version 1.0
 */
@Repository
public interface UserLoginMapper {

    boolean existed(String phoneNumber);
}
