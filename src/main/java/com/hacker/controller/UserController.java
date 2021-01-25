package com.hacker.controller;


import com.hacker.dto.RegisterByPwdDTO;
import com.hacker.entity.UserLoginEntity;
import com.hacker.service.UserLoginService;
import com.hacker.service.UserService;
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
        UserLoginEntity userLoginEntity = userLoginService.selectByPhone(dto.getPhoneNumber());
        if (userLoginEntity !=null) {
            throw new
        }
        return null;
    }
}
