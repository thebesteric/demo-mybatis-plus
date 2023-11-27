package com.example.mybatis;

import com.example.mybatis.pojo.User;
import com.example.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * MybatisPlusServiceTest
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-26 22:12:15
 */
@SpringBootTest
public class MybatisPlusServiceTest {

    @Autowired
    UserService userService;

    @Test
    void testCount() {
        // SELECT COUNT( * ) AS total FROM user
        long count = userService.count();
        System.out.println("count = " + count);
    }

    @Test
    void testBatchInsert() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            User user = createUser("test" + i, i, "mail-" + i + "@test.com");
            users.add(user);
        }
        // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        boolean result = userService.saveBatch(users);
        System.out.println("result = " + result);
    }

    private User createUser(Long id, String name, int age, String email) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        return user;
    }

    private User createUser(String name, int age, String email) {
        return createUser(null, name, age, email);
    }

    private User createUser() {
        return createUser("张三", 23, "zhangsan@test.com");
    }
}
