package com.example.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.pojo.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * AccountMapper
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-28 13:35:19
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    @Select("select * from account")
    @Results(id="accountMap", value = {
            // @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            // @Result(column = "name", property = "name"),
            // @Result(column = "money", property = "money"),
            @Result(column = "user_id", property = "user",
                    one = @One(select = "com.example.mybatis.mapper.UserMapper.selectUser",
                            fetchType = FetchType.EAGER))
    })
    List<Account>  findAll();

    @Select("select * from account where user_id = #{userId}")
    List<Account> findAccountByUserId(Long userId);
}
