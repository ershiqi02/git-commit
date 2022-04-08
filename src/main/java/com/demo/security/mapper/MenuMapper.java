package com.demo.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.security.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 郑嘉磊
 * @Date 2022/4/8 14:34
 * @Version 1.0
 */
@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPremsByUserid(Long id);
}
