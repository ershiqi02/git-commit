package com.demo.security.hanlder;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: 郑嘉磊
 * @Date: 2022/3/26 15:21
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler  {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("lastLogin",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
