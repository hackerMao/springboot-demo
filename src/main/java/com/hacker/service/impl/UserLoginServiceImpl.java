package com.hacker.service.impl;

import com.hacker.entity.UserLoginEntity;
import com.hacker.mapper.UserLoginMapper;
import com.hacker.service.UserLoginService;
import com.hacker.utils.PBKDF2Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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

    @Autowired
    private PBKDF2Util pbkdf2Util;

    @Override
    public UserLoginEntity selectByPhone(String phoneNumber) {
        return null;
    }

    @Override
    public boolean existed(String phoneNumber) {
        return userLoginMapper.existed(phoneNumber);
    }

    @Override
    public void addUser(UserLoginEntity userLoginEntity) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String salt = pbkdf2Util.generateSalt();
        String passwordToAddSalt = pbkdf2Util.getEncryptedPassword(userLoginEntity.getPassword(), salt);

        userLoginEntity.setSalt(salt);
        userLoginEntity.setPassword(passwordToAddSalt);
        userLoginMapper.insert(userLoginEntity);
    }
}
