package com.hacker.service;

import com.hacker.entity.UserLoginEntity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @Description 用户登录业务层接口
 * @Author mr.mao_hacker
 * @Date 2021/1/25 21:49
 * @Version 1.0
 */
public interface UserLoginService {

    /**
     * 通过手机号查找用户登录实体
     *
     * @param phoneNumber 手机号
     * @return com.hacker.entity.UserLoginEntity
     */
    UserLoginEntity selectByPhone(String phoneNumber);

    /**
     * 判断该手机号是否已注册
     *
     * @param phoneNumber 手机号
     * @return boolean
     */
    boolean existed(String phoneNumber);

    /**
     * 添加新用户
     *
     * @param userLoginEntity com.hacker.entity.UserLoginEntity
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    void addUser(UserLoginEntity userLoginEntity) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
