package com.demo.security.service;

import com.demo.security.entity.User;
import com.demo.security.lang.Result;

/**
 * @Author: 郑嘉磊
 * @Date: 2022/3/27 20:41
 */
public interface LoginService {
    Result login(User user);
    Result logout();
}
