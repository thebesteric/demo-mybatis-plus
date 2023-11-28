package com.example.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatis.pojo.Car;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@CacheNamespace
public interface CarMapper extends BaseMapper<Car> {

    /**
     * 使用 @Result 做字段转换
     * id：表示是否是主键，默认 false
     * column: 数据库中的字段名
     * property: 实体类的属性名
     */
    @Select("SELECT * FROM car")
    @Results(id = "carMap", value = {
            @Result(id = true, column = "id", property = "carId"),
            @Result(column = "name", property = "carName")
    })
    List<Car> selectAll();

    /**
     * 使用 @ResultMap 指定 @Results 的 id 值，完成转换
     */
    @Select("SELECT * FROM car WHERE id = #{id}")
    @ResultMap("carMap")
    Car findOneCar(long id);


}
