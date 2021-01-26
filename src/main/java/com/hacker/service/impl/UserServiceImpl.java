package com.hacker.service.impl;

import com.hacker.dto.RegisterByPwdDTO;
import com.hacker.mapper.UserInfoMapper;
import com.hacker.service.UserService;
import com.hacker.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 21:55
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public UserVO register(RegisterByPwdDTO dto) {
        return null;
    }
}
