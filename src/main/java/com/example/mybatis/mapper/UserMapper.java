package com.example.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * UserMapper
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-26 16:02:53
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    Map<String, Object> selectMapById(Long id);

    /**
     * 通过年龄查询用户信息并分页
     *
     * @param page mybatis-plus 提供的分页对象，必须位于第一个位置
     * @param age
     */
    IPage<User> selectPageVo(@Param("page") IPage<User> page, @Param("age") Integer age);

}
