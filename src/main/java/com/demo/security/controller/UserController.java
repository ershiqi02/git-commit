package com.demo.security.controller;

import com.demo.security.entity.User;
import com.demo.security.lang.Result;
import com.demo.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 郑嘉磊
 * @since 2022-03-26
 */
@RestController
@ControllerAdvice
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @ResponseBody
    @GetMapping("{id}")
    public Result query(@PathVariable("id") long id){
        User user = userMapper.selectById(id);
        return new Result(200,user.getUserName(),12);
    }
    @GetMapping("test")
    public Result test(){
        return new Result(200,"测试");
    }

    @PreAuthorize("hasAuthority('system:dept')")
    @GetMapping("/hello")
    public Result hello(){
        return new Result(200,"hello");
    }
}
