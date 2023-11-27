package com.example.mybatis;

import com.example.mybatis.enums.GenderEnum;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * MybatisPlusEnumTest
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-27 16:56:26
 */
@SpringBootTest
class MybatisPlusEnumTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void test() {
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        user.setEmail("admin@example.com");
        user.setGender(GenderEnum.MALE);

        int result = userMapper.insert(user);
        System.out.println("result = " + result);
    }

}
