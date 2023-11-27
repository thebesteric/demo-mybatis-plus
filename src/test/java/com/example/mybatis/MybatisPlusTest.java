package com.example.mybatis;

import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MybatisPlusTest
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-26 16:12:13
 */
@SpringBootTest
class MybatisPlusTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void initDatabase() {
        // DELETE FROM user
        userMapper.delete(null);

        List<User> users = Arrays.asList(
                createUser(1L, "Jone", 18, "jone@test.com"),
                createUser(2L, "Jack", 20, "join@test.com"),
                createUser(3L, "Tom", 28, "join@test.com"),
                createUser(4L, "Sandy", 21, "join@test.com"),
                createUser(5L, "Billie", 24, "join@test.com")
        );
        for (User user : users) {
            userMapper.insert(user);
        }
    }


    @Test
    void testSelectList() {
        // SELECT id,name,age,email FROM user
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    long testInsert() {
        User user = createUser();
        // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        int result = userMapper.insert(user);
        System.out.println("result = " + result);
        System.out.println("user.id = " + user.getId());

        return user.getId();
    }

    @Test
    void testDelete() {
        long id = testInsert();
        // DELETE FROM user WHERE id=?
        // 如果实体类上有 @TableLogic 属性：UPDATE user SET is_deleted=1 WHERE id=? AND is_deleted=0
        int result = userMapper.deleteById(id);
        System.out.println("result = " + result);

        testInsert();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        // DELETE FROM user WHERE (name = ? AND age = ?)
        // 如果实体类上有 @TableLogic 属性：UPDATE user SET is_deleted=1 WHERE is_deleted=0 AND (name = ? AND age = ?)
        result = userMapper.deleteByMap(map);
        System.out.println("result = " + result);

        id = testInsert();
        List<Long> ids = Arrays.asList(id);
        // DELETE FROM user WHERE id IN ( ? )
        // 如果实体类上有 @TableLogic 属性：UPDATE user SET is_deleted=1 WHERE id IN ( ? ) AND is_deleted=0
        result = userMapper.deleteBatchIds(ids);
        System.out.println("result = " + result);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setName("李四");
        user.setAge(18);
        // UPDATE user SET name=?, age=? WHERE id=?
        int result = userMapper.updateById(user);
        System.out.println("result = " + result);
    }

    @Test
    void testSelect() {
        // SELECT id,name,age,email FROM user WHERE id=?
        // 如果实体类上有 @TableLogic 属性：SELECT id,name,age,email,is_deleted FROM user WHERE id=? AND is_deleted=0
        User user = userMapper.selectById(1L);
        System.out.println("user = " + user);

        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        // 如果实体类上有 @TableLogic 属性：SELECT id,name,age,email,is_deleted FROM user WHERE id IN ( ? , ? , ? ) AND is_deleted=0
        List<User> users = userMapper.selectBatchIds(ids);
        users.forEach(System.out::println);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Jack");
        map.put("age", 20);
        // SELECT id,name,age,email FROM user WHERE (name = ? AND age = ?)
        // 如果实体类上有 @TableLogic 属性：SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (name = ? AND age = ?)
        users = userMapper.selectByMap(map);
        users.forEach(System.out::println);

        // SELECT id,name,age,email FROM user
        // 如果实体类上有 @TableLogic 属性：SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0
        users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testSelectMapById() {
        // select id, name, age, email from user where id = ?
        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);
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
