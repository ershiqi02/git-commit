package com.demo.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.security.entity.User;
import com.demo.security.mapper.MenuMapper;
import com.demo.security.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@SpringBootTest
class SecurityApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,"张安");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }
    @Test
    void test(){
        User user = new User();
        user.setUserName("李松");
        user.setAvatar("https://s4.ax1x.com/2022/02/23/bCo99e.png");
        user.setNickName("hah");
        user.setPassword("123");
        userMapper.insert(user);
    }
    @Test
    void test2(){
        String password="1234";
        String encode = passwordEncoder.encode(password);
        String encode1 = passwordEncoder.encode(password);
        System.out.println(encode);
        System.out.println(encode1);
        System.out.println(passwordEncoder.matches("1234",
                "$2a$10$iWNUp0xzWbYns3MsfY/xwelUvYl32TeXldKo/qblu9GCl4tVXDRPu"));
        System.out.println(passwordEncoder.matches("1234",
                "$2a$10$KIOaiGrSOYRI8WzYfb7xieGhCWHEY0IqaW1BDu5rKTZr8PeZXkusO"));
    }
    @Test
    void test3(){
        List<String> strings = menuMapper.selectPremsByUserid(1507987468609056769L);
        System.out.println(strings);
    }
}
