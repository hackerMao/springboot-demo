package com.hacker.service.impl;

import com.hacker.entity.UserLoginEntity;
import com.hacker.mapper.UserLoginMapper;
import com.hacker.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 21:50
 * @Version 1.0
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public UserLoginEntity selectByPhone(String phoneNumber) {
        return null;
    }

    @Override
    public boolean existed(String phoneNumber) {
        return userLoginMapper.existed(phoneNumber);
    }
}
