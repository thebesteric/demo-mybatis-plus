package com.example.mybatis;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatis.mapper.ProductMapper;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.pojo.Product;
import com.example.mybatis.pojo.User;
import com.example.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * MybatisPlusPluginsTest
 *
 * @author wangweijun
 * @version v1.0
 * @since 2023-11-27 14:38:21
 */
@SpringBootTest
public class MybatisPlusPluginsTest {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ProductMapper productMapper;

    /**
     * 分页测试
     */
    @Test
    void testPage() {
        // limit ?, ?
        // limit 的第一个参数：当前页的页面 - 1 * pageSize
        // limit 的第二个参数：pageSize
        // Page 的 current 表示第几页，size 表示每页显示数量
        Page<User> page = new Page<>(2, 3);

        // SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0 LIMIT ?,?
        page = userMapper.selectPage(page, null);

        // List<User> users = userService.list(page);
        // users.forEach(System.out::println);
        // page.setRecords(users);

        System.out.println("当前分页数据 = " + page.getRecords());
        System.out.println("当前页 = " + page.getCurrent());
        System.out.println("每页显示数量 = " + page.getSize());
        System.out.println("总页数 = " + page.getPages());
        System.out.println("总记录数 = " + page.getTotal());
        System.out.println("是否有下一页 = " + page.hasNext());
        System.out.println("是否有上一页 = " + page.hasPrevious());
    }

    /**
     * 自定义分页测试
     */
    @Test
    void testPageVo() {
        Page<User> page = new Page<>(1, 3);
        // select id, name, age, email from user where age >= ? LIMIT ?
        userMapper.selectPageVo(page, 19);

        System.out.println("当前分页数据 = " + page.getRecords());
        System.out.println("当前页 = " + page.getCurrent());
        System.out.println("每页显示数量 = " + page.getSize());
        System.out.println("总页数 = " + page.getPages());
        System.out.println("总记录数 = " + page.getTotal());
        System.out.println("是否有下一页 = " + page.hasNext());
        System.out.println("是否有上一页 = " + page.hasPrevious());
    }

    /**
     * 乐观锁模拟
     */
    @Test
    void testProduct01() {
        // 小李查询商品价格
        Product productLi = productMapper.selectById(1L);
        System.out.println("Li 查询的商品价格 = " + productLi);

        // 小王查询商品价格
        Product productWang = productMapper.selectById(1L);
        System.out.println("Wang 查询的商品价格 = " + productWang);

        // 小李将商品 +50 元
        productLi.setPrice(productLi.getPrice() + 50);
        // 增加乐观锁插件后：UPDATE product SET name=?, price=?, version=? WHERE id=? AND version=?
        productMapper.updateById(productLi);
        System.out.println("Li 修改后的商品价格 = " + productLi); // 150

        // 小李将商品 -30 元
        productWang.setPrice(productWang.getPrice() - 30);
        // 增加乐观锁插件后：UPDATE product SET name=?, price=?, version=? WHERE id=? AND version=?
        productMapper.updateById(productWang);
        System.out.println("Wang 修改后的商品价格 = " + productWang); // 70

        // 实际商品价格
        Product productBoss = productMapper.selectById(1L);
        System.out.println("Boss 查询的商品价格 = " + productBoss); // 70
    }

    /**
     * 乐观锁模拟（优化）
     */
    @Test
    void testProduct02() {
        // 小李查询商品价格
        Product productLi = productMapper.selectById(1L);
        System.out.println("Li 查询的商品价格 = " + productLi);

        // 小王查询商品价格
        Product productWang = productMapper.selectById(1L);
        System.out.println("Wang 查询的商品价格 = " + productWang);

        // 小李将商品 +50 元
        productLi.setPrice(productLi.getPrice() + 50);
        // 增加乐观锁插件后：UPDATE product SET name=?, price=?, version=? WHERE id=? AND version=?
        productMapper.updateById(productLi);
        System.out.println("Li 修改后的商品价格 = " + productLi); // 150

        // 小李将商品 -30 元
        productWang.setPrice(productWang.getPrice() - 30);
        // 增加乐观锁插件后：UPDATE product SET name=?, price=?, version=? WHERE id=? AND version=?
        int result = productMapper.updateById(productWang);
        while (result == 0) {
            productWang = productMapper.selectById(1L);
            productWang.setPrice(productWang.getPrice() - 30);
            result = productMapper.updateById(productWang);
        }
        System.out.println("Wang 修改后的商品价格 = " + productWang); // 70

        // 实际商品价格
        Product productBoss = productMapper.selectById(1L);
        System.out.println("Boss 查询的商品价格 = " + productBoss); // 70
    }
}
