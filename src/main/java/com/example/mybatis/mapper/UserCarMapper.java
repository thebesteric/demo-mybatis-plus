package com.example.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.pojo.UserCar;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface UserCarMapper extends BaseMapper<UserCar> {

    @Select("select * from user_car where user_id = #{user_id}")
    @Results(id = "userCarMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "car", column = "id",
                    one = @One(select = "com.example.mybatis.mapper.CarMapper.findOneCar", fetchType = FetchType.EAGER))
    })
    List<UserCar> findByUserIdCars(String userId);


}
