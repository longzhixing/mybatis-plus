package com.longzhixing.demo;

import com.longzhixing.demo.mapper.UserMapper;
import com.longzhixing.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    /**
     * 继承了 BaseMapper，所有的方法都来自父类 BaseMapper
     * 也可以编写自己的扩展方法
     */
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        /**
         * 查询所有的用户
         * selectList()方法参数为 MP 内置的条件构造器 Wrapper,传入 null 就是不传入任何条件
         */
        List<User> userList = userMapper.selectList(null);
        //遍历输出
        userList.forEach(System.out::println);
    }

    @Test
    void insertTest(){
        User user = new User();
        user.setName("虎之心");
        user.setAge(5);
        user.setEmail("123@hu.com");
        int result = userMapper.insert(user); //添加用户，没有指定主键id,自已生成主键id
        System.out.println(result); // 受影响的行数
        System.out.println(user);
    }

    // 更新用户
    @Test
    void updateTest(){
        User user = new User();
        user.setAge(3);
        user.setId(1317001264950743042l);
        //updateById() 方法参数是一个对象，更新时通过条件自动拼接动态sql
        int i = userMapper.updateById(user);
        System.out.println(i); //受景响的行数
    }

    /**
     * 测试乐观锁
     */
    @Test
    void testOptimisticLocker(){
        //根据id查询用户
        User user = userMapper.selectById(1316625543732461569l);
        user.setName("狼之心");
        user.setEmail("123@langzhixing.com");
        userMapper.updateById(user); //更新用户
    }
}
