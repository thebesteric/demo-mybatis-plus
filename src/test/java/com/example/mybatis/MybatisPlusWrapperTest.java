package com.example.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.mybatis.pojo.User;
import com.example.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * MybatisPlusWrapperTest
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-27 00:51:11
 */
@SpringBootTest
public class MybatisPlusWrapperTest {

    @Autowired
    UserService userService;

    /**
     * 查询用户名包含 a，年龄在 20～30 之间，邮箱信息不为 null 的用户信息
     */
    @Test
    void test01() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                .between("age", 20, 30)
                .isNotNull("email");

        // SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        List<User> users = userService.list(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 查询用户信息，按 age 降序排列，若年龄相同则按照 id 生序排序
     */
    @Test
    void test02() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");

        // SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0 ORDER BY age DESC,id ASC
        List<User> users = userService.list(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 删除邮箱地址为 null 的信息
     */
    @Test
    void test03() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");

        // UPDATE user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
        boolean result = userService.remove(queryWrapper);
        System.out.println("result = " + result);
    }

    /**
     * 将（年龄大于 20 并且用户名包含 a）或邮箱包为 null 的用户信息修改
     */
    @Test
    void test04() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .gt("age", 20).like("name", "a")
                .or()
                .isNull("email");

        List<User> users = userService.list(queryWrapper);
        users.forEach(System.out::println);

        User user = new User();
        user.setName("小明");
        user.setEmail("xiaoming@test.com");

        // UPDATE user SET name=?, email=? WHERE is_deleted=0 AND (age > ? AND name LIKE ? OR email IS NULL)
        boolean result = userService.update(user, queryWrapper);
        System.out.println("result = " + result);
    }

    /**
     * 将用户名包含 a 并且（年龄大于 20 并或邮箱包为 null）的用户信息修改
     */
    @Test
    void test05() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                .and(qw -> qw.gt("age", 20).or().isNull("email"));

        List<User> users = userService.list(queryWrapper);
        users.forEach(System.out::println);

        User user = new User();
        user.setName("小红");
        user.setEmail("xiaohong@test.com");

        // UPDATE user SET name=?, email=? WHERE is_deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
        boolean result = userService.update(user, queryWrapper);
        System.out.println("result = " + result);
    }

    /**
     * 只查询用户的 name 和 age
     */
    @Test
    void test06() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age");

        // SELECT name,age FROM user WHERE is_deleted=0
        List<Map<String, Object>> list = userService.listMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 利用自查询，查找 id <= 100 的用户信息
     */
    @Test
    void test07() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from user where id <= 100");

        // SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (id IN (select id from user where id <= 100))
        List<User> users = userService.list(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 使用 UpdateWrapper 将用户名包含 a 并且（年龄大于 20 并或邮箱包为 null）的用户信息修改
     */
    @Test
    void test08() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("name", "a").and(u -> u.gt("age", 20).or().isNull("email"));
        updateWrapper.set("name", "小红").set("email", "xiaohong@test.com");
        // UPDATE user SET name=?,email=? WHERE is_deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
        boolean result = userService.update(updateWrapper);
        System.out.println("result = " + result);
    }

    /**
     * 条件拼装
     */
    @Test
    void test09() {
        String name = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }

        // SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        List<User> users = userService.list(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 利用 condition 进行条件拼装
     */
    @Test
    void test10() {
        String name = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.ge(ageBegin != null, "age", ageBegin);
        queryWrapper.le(ageEnd != null, "age", ageEnd);

        // SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        List<User> users = userService.list(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 利用 LambdaQueryWrapper 进行条件拼装
     */
    @Test
    void test11() {
        String name = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(name), User::getName, name);
        lambdaQueryWrapper.ge(ageBegin != null, User::getAge, ageBegin);
        lambdaQueryWrapper.le(ageEnd != null, User::getAge, ageEnd);

        // SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        List<User> users = userService.list(lambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 使用 LambdaUpdateWrapper 将用户名包含 a 并且（年龄大于 20 并或邮箱包为 null）的用户信息修改
     */
    @Test
    void test12() {
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.like(User::getName, "a").and(u -> u.gt(User::getAge, 20).or().isNull(User::getEmail));
        lambdaUpdateWrapper.set(User::getName, "小红").set(User::getEmail, "xiaohong@test.com");
        // UPDATE user SET name=?,email=? WHERE is_deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
        boolean result = userService.update(lambdaUpdateWrapper);
        System.out.println("result = " + result);
    }

}
