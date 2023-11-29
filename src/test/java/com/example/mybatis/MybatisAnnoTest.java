package com.example.mybatis;

import com.example.mybatis.convert.UserConvert;
import com.example.mybatis.enums.GenderEnum;
import com.example.mybatis.mapper.AccountMapper;
import com.example.mybatis.mapper.CarMapper;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.pojo.Account;
import com.example.mybatis.pojo.Car;
import com.example.mybatis.pojo.User;
import com.example.mybatis.pojo.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * MybatisAnnoTest
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-28 00:09:37
 */
@SpringBootTest
public class MybatisAnnoTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CarMapper carMapper;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    UserConvert userConvert;

    @Test
    void selectAll() {
        List<User> users = userMapper.selectAll();
        List<UserVO> userVOs = userConvert.toUserVO(users);
        userVOs.forEach(System.out::println);
    }

    @Test
    void selectUser() {
        User user = userMapper.selectUser(2L);
        UserVO userVO = userConvert.toUserVO(user);
        System.out.println("userVO = " + userVO);
    }

    @Test
    void selectUserByNameLike() {
        List<User> users = userMapper.selectUserByNameLike("a");
        List<UserVO> userVOs = userConvert.toUserVO(users);
        userVOs.forEach(System.out::println);
    }

    @Test
    void selectCountUsers() {
        long count = userMapper.selectCountUsers();
        System.out.println("count = " + count);
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setId(1701104095655L);
        user.setName("test");
        user.setAge(22);
        user.setEmail("test@test.com");
        user.setGender(GenderEnum.FEMALE);
        long key = userMapper.saveUser(user);
        System.out.println("key = " + key);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(1701104095655L);
        user.setName("test1");
        user.setAge(221);
        user.setEmail("test1@test.com");
        user.setGender(GenderEnum.UNKNOWN);
        long key = userMapper.updateUser(user);
        System.out.println("key = " + key);
    }

    @Test
    void deleteUser() {
        long key = userMapper.deleteUser(1701104095655L);
        System.out.println("key = " + key);
    }

    @Test
    void selectAllCars() {
        List<Car> cars = carMapper.selectAll();
        cars.forEach(System.out::println);
    }

    @Test
    void findOneCar() {
        Car car1 = carMapper.findOneCar(1L);
        Car car2 = carMapper.findOneCar(1L);
        System.out.println("car1 = " + car1);
        System.out.println("car1 = " + car2);
        System.out.println(car1 == car2);

    }

    /**
     * 一对一，属性注入
     */
    @Test
    void findAllAccount() {
        List<Account> accounts = accountMapper.findAll();
        accounts.forEach(System.out::println);
    }

    /**
     * 一对多，属性注入
     */
    @Test
    void findUserAccounts() {
        List<User> users = userMapper.selectAll();
        users.forEach(System.out::println);
    }

    /**
     * 多对多，属性注入
     */
    @Test
    void findUserCars() {
        UserVO userVO = userMapper.selectUserById(1L);
        System.out.println("userVO = " + userVO);
    }

}
