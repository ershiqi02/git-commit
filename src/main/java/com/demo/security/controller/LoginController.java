package com.demo.security.controller;

import com.demo.security.entity.User;
import com.demo.security.lang.Result;
import com.demo.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 郑嘉磊
 * @Date: 2022/3/27 18:54
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }

    @RequestMapping("/user/logout")
    public Result logout(){
        return loginService.logout();
    }
}
