package com.example.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mybatis.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
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

    @Select("SELECT * FROM user")
    @Results(id = "userMap", value = {
            @Result(property = "accounts", column = "id",
                    many = @Many(
                            select = "com.example.mybatis.mapper.AccountMapper.findAccountByUserId",
                            fetchType = FetchType.LAZY
                    ))
    })
    List<User> selectAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectUser(long id);

    // @Select("SELECT * FROM user WHERE name like #{name}")
    @Select("SELECT * FROM user WHERE name like '%${value}%'") // ${value} 如果要使用 % 做模糊查询，则此为固定写法
    List<User> selectUserByNameLike(String name);

    @Select("SELECT COUNT(*) FROM user")
    long selectCountUsers();

    @Insert("INSERT INTO user ( id, name, age, email, gender ) VALUES ( #{id}, #{name}, #{age}, #{email}, #{gender} )")
    long saveUser(User user);

    @Update("UPDATE user SET name = #{name}, age = #{age}, email = #{email}, gender = #{gender} WHERE id = #{id}")
    long updateUser(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    long deleteUser(long id);
}
