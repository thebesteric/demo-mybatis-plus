package com.example.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * UserCar
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-29 11:17:14
 */
@Data
public class UserCar {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("car_id")
    private Long carId;

    @TableField(exist = false)
    private Car car;
}
