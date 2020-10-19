package com.longzhixing.demo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longzhixing.demo.mapper.UserMapper;
import com.longzhixing.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
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

    /**
     * 根据id查询用户
     */
    @Test
    void testSelectById(){
        // 根据id查询用户
        User user = userMapper.selectById(1l);
        System.out.println(user);
    }

    /**
     * 根据用户id批量查询多个用户
     */
    @Test
    void testSlectByBatchId(){
        //查询用户id 是 1，2，3的用户，传入一个用户id集合
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        userList.forEach(System.out::println);
    }

    /**
     * 根据条件查询，使用Map操作
     */
    @Test
    void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        //根据姓名和年龄查询
        map.put("name","狼之心");
        map.put("age",4);
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    /**
     * 测试分面
     */
    @Test
    void testPage(){
        // 参数1：第2页，参数2：每页显示2条
        Page<User> page = new Page<>(2,2);
        //参数1：分页，参数2：查询条件，没有条件传入 null
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }
}
