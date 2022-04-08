package com.demo.security.mapper;

import com.demo.security.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 郑嘉磊
 * @since 2022-03-26
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User>{

}
