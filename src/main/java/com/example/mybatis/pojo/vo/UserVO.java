package com.example.mybatis.pojo.vo;

import com.example.mybatis.enums.GenderEnum;
import lombok.Data;

/**
 * UserVO
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-26 16:01:07
 */
@Data
public class UserVO {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Integer isDeleted = 0;
    private GenderEnum gender;
}
