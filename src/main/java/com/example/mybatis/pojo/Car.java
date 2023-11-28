package com.example.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Car
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-28 01:19:42
 */
@Data
@TableName("car")
public class Car implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private long carId;

    @TableField("name")
    private String carName;
}
