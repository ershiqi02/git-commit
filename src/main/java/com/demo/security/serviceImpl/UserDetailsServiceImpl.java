package com.demo.security.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.security.entity.User;
import com.demo.security.lang.LoginUser;
import com.demo.security.mapper.MenuMapper;
import com.demo.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author: 郑嘉磊
 * @Date: 2022/3/27 14:43
 * 重写spring security的UserDetailsService方法
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        //TODO 查询对应的用户权限
        List<String> prems = menuMapper.selectPremsByUserid(user.getId());

        return new LoginUser(user,prems);
    }
}
