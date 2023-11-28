package com.example.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Account
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-28 13:29:06
 */
@Data
public class Account {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Long userId;
    private Long money;

    // 一对一：该账号属于哪一个用户，这里需要在 mapper 中最对应关系
    @TableField(exist = false)
    private User user;
}
