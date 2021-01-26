package com.hacker.service;

import com.hacker.dto.RegisterByPwdDTO;
import com.hacker.vo.UserVO;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 21:55
 * @Version 1.0
 */
public interface UserService {

    /**
     * 通过手机号、密码注册
     *
     * @param dto com.hacker.dto.RegisterByPwdDTO
     * @return com.hacker.vo.UserVO
     */
    UserVO register(RegisterByPwdDTO dto);
}
