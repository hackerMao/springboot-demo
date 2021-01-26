package com.hacker.controller;


import com.hacker.core.enumeration.CaptchaType;
import com.hacker.dto.RegisterByPwdDTO;
import com.hacker.entity.UserLoginEntity;
import com.hacker.exception.NotFoundException;
import com.hacker.exception.ParameterException;
import com.hacker.service.UserLoginService;
import com.hacker.service.UserService;
import com.hacker.utils.CaptchaUtil;
import com.hacker.vo.UserVO;
import com.hacker.vo.common.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description TODO
 * @Author mr.mao_hacker
 * @Date 2021/1/25 20:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public ResponseVO<String> helloHandler() {
        return ResponseVO.success("hello");
    }

    @PostMapping("/register/byPwd")
    public ResponseVO<Object> registerByPwdHandler(@Validated @RequestBody RegisterByPwdDTO dto) {
        boolean ok = CaptchaUtil.checkCaptcha(dto.getPhoneNumber(), CaptchaType.REGISTER, dto.getCaptcha());
        if (!ok) {
            throw new ParameterException(20007);
        }
        if (userLoginService.existed(dto.getPhoneNumber())) {
            throw new NotFoundException(20005);
        }

        UserVO userVO = userService.register(dto);
        return ResponseVO.success(userVO);
    }
}
