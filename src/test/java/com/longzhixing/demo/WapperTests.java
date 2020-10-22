package com.longzhixing.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longzhixing.demo.mapper.UserMapper;
import com.longzhixing.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WapperTests {

    /**
     * 继承了 BaseMapper，所有的方法都来自父类 BaseMapper
     * 也可以编写自己的扩展方法
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 多条件查询
     */
    @Test
    void contextLoads() {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.isNotNull("name") //查询姓名不为空
                .isNotNull("email") // 并且邮箱不为空
                .ge("age", 12); //年龄大于等于12的用户
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    /**
     * 根据姓名查询
     */
    @Test
    void queryNameTest(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 查询姓名叫狼之心的人
        wrapper.eq("name","狼之心");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    /**
     * between...and...区间查询
     */
    @Test
    void betweenTest(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 查询年龄在10到20岁之间的用户
        wrapper.between("age",10,20);
        // 查询总个数，在年龄10~20岁之间有多少人
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    /**
     * 模糊查询
     */
    @Test
    void likeTest(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.notLike("name","o") //查询姓名中不包含o
        .likeRight("email","t"); // 并且邮箱是以t开头的用户
        List<Map<String,Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    /**
     * 子查询
     */
    @Test
    void chilequeryTest(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 查询id小于3的用户
        wrapper.inSql("id","select id from user where id < 3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    /**
     * 排序
     */
    @Test
    void descTest(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 根据id降序排序
        wrapper.orderByDesc("id");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }
}