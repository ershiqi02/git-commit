package com.demo.security.serviceImpl;

import com.demo.security.entity.Blog;
import com.demo.security.mapper.BlogMapper;
import com.demo.security.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 郑嘉磊
 * @since 2022-03-26
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

}
