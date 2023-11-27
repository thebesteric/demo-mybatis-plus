package com.example.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * ProductMapper
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-27 16:03:19
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
