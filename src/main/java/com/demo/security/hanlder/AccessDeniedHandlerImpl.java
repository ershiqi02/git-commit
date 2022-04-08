package com.demo.security.hanlder;

import com.alibaba.fastjson.JSON;
import com.demo.security.lang.Result;
import com.demo.security.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 郑嘉磊
 * @Date 2022/4/8 15:47
 * @Version 1.0
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result result=new Result(HttpStatus.FORBIDDEN.value(),"权限不足");
        String json = JSON.toJSONString(result);
        WebUtil.renderString(response,json);
    }
}
