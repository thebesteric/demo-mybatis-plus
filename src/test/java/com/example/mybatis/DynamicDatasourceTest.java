package com.example.mybatis;

import com.example.mybatis.pojo.Product;
import com.example.mybatis.pojo.User;
import com.example.mybatis.service.ProductService;
import com.example.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * DynamicDatasourceTest
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-27 17:58:42
 */
@SpringBootTest
class DynamicDatasourceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Test
    void test() {
        List<User> users = userService.list();
        users.forEach(System.out::println);

        List<Product> products = productService.list();
        products.forEach(System.out::println);
    }

}
