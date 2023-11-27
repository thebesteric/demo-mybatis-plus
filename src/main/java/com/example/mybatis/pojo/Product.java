package com.example.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * Product
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-27 15:59:46
 */
@Data
@TableName("product")
public class Product {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("name")
    private String name;

    private Integer price;

    // 乐观锁版本号：每次更新钱都会带着之前的版本号先做查询，在做更新，更新后：version = version + 1
    // 需要在配置中，设置【乐观锁插件】
    @Version
    private Integer version;
}
