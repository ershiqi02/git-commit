package com.demo.security.filter;

import com.demo.security.lang.LoginUser;
import com.demo.security.lang.Result;
import com.demo.security.utils.JwtUtil;
import com.demo.security.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author 郑嘉磊
 * @Date 2022/4/7 9:58
 * @Version 1.0
 * 定义jwt认证过滤器
 */
@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    private String userid;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)){
           //如果token为空，放行，后续会有拦截器进行拦截
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        try {
            Claims claims = JwtUtil.parseJWT(token);
             userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String rediskey="login:"+userid;
        LoginUser loginUser = redisCache.getCacheObject(rediskey);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        //todo 获取权限信息，封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        //存入securityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request,response);

    }
}
