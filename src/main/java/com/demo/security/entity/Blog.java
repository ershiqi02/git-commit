package com.demo.security.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 郑嘉磊
 * @since 2022-03-26
 */
@Getter
@Setter
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    private Long userId;

    private String title;

    private String description;

    private String content;

      @TableField(fill = FieldFill.INSERT)
    private LocalDateTime created;

    private Integer status;


}
