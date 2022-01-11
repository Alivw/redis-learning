package com.jalivv.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 用户类
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/9 15:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -978555740312118801L;

    private String username;

    private Integer age;
}
