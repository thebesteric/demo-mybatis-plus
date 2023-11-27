package com.example.mybatis.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatis.mapper.ProductMapper;
import com.example.mybatis.pojo.Product;
import com.example.mybatis.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-27 16:04:39
 */
@Service
// 多数据源配置，@DS 可以注解在方法上或类上，同时存在就近原则 方法上注解 优先于 类上注解
@DS("slave_1")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
