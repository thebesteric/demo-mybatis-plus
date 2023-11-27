package com.example.mybatis.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * GenderEnum
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-27 16:49:43
 */
@AllArgsConstructor
@Getter
public enum GenderEnum {

    UNKNOWN(0, "未知"), MALE(1, "男"), FEMALE(2, "女");

    // 将注解锁标识的属性的值，存储到数据库中
    // 老版本可能需要配置：mybatis-plus.type-enums-package: com.example.mybatis.enums
    @EnumValue
    private final Integer gender;
    private final String genderName;
}
