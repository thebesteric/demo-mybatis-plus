package com.example.mybatis.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.pojo.User;
import com.example.mybatis.service.UserService;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-26 17:53:05
 */
@Service
// 多数据源配置，@DS 可以注解在方法上或类上，同时存在就近原则 方法上注解 优先于 类上注解
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
