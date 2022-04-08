package com.demo.security.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.security.entity.User;
import com.demo.security.lang.LoginUser;
import com.demo.security.lang.Result;
import com.demo.security.mapper.UserMapper;
import com.demo.security.service.LoginService;
import com.demo.security.utils.JwtUtil;
import com.demo.security.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Author: 郑嘉磊
 * @Date: 2022/3/27 20:41
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Override
    public Result login(User user) {
        //用户登录名和密码
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        //AuthenticationManager 用户认证
        Authentication authenticate = authenticationManager.authenticate(token);
        //如果认证没通过，给出相应提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("账户名或密码错误");
        }
        //如果认证通过了使用userid生成一个jwt，jwt存入Resultf
        LoginUser loginUser= (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入redis，userID作为key
        redisCache.setCacheObject("login:"+id,loginUser);
        return new Result(200,"登录成功",map);
    }

    @Override
    public Result logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:"+id);
        return new Result(200,"退出成功");
    }
}
