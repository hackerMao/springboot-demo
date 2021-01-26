package com.hacker.service.impl;

import com.hacker.dto.RegisterByPwdDTO;
import com.hacker.entity.UserInfoEntity;
import com.hacker.entity.UserLoginEntity;
import com.hacker.exception.ServerErrorException;
import com.hacker.mapper.UserInfoMapper;
import com.hacker.service.UserLoginService;
import com.hacker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 21:55
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserLoginService userLoginService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoEntity register(RegisterByPwdDTO dto) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        UserLoginEntity userLoginEntity = new UserLoginEntity();

        try {
            BeanUtils.copyProperties(dto, userLoginEntity);
            userLoginService.addUser(userLoginEntity);

            userInfoEntity.setLoginId(userInfoEntity.getId());
            userInfoEntity.generateDdId(userInfoEntity.getId());
            userInfoEntity.setNickname(dto.getPhoneNumber().replace(
                    dto.getPhoneNumber().substring(3, 7), "****"));
            userInfoMapper.insert(userInfoEntity);
        } catch (Exception e) {
            log.error("用户注册发生异常，详细信息：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServerErrorException(9999);
        }
        return userInfoEntity;
    }
}
