package com.example.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.example.mybatis.enums.GenderEnum;
import lombok.Data;

import java.util.List;

/**
 * User
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-26 16:01:07
 */
@Data
// 设置实体类对应的表名
@TableName("user")
public class User {

    // 将属性对应的字段名，作为主键
    // value：指的是数据库中主键的名称
    // type：AUTO：自动递增，ASSIGN_ID（默认）：雪花算法
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("name")
    private String name;
    private Integer age;
    private String email;

    // 加上 @TableLogic 注解后，删除就变成了逻辑删除
    @TableLogic
    private Integer isDeleted = 0;

    private GenderEnum gender;

    @TableField(exist = false)
    private List<Account> accounts;
}
