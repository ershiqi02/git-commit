package com.demo.security.hanlder;

import com.alibaba.fastjson.JSON;
import com.demo.security.lang.Result;
import com.demo.security.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * @Author 郑嘉磊
 * @Date 2022/4/8 15:31
 * @Version 1.0
 * 异常处理类
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result=new Result(HttpStatus.UNAUTHORIZED.value(), "用户认证失败");
        String json = JSON.toJSONString(result);
        //处理异常
        WebUtil.renderString(response,json);
    }
}
