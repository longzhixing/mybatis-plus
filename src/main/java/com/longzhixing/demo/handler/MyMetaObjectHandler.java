package com.longzhixing.demo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充策略
 */
@Slf4j
@Component // 一定不要忘记把处理器添加到IOC容器中
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入数据时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始插入数据时的填充策略");
        //在执行添加数据的时候自动添加创建时间 setFieldValByName(字段名称, 字段值, metaObject)
        this.setFieldValByName("createTime", new Date(), metaObject);
        //在执行添加的时候自动添加更新时间
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    /**
     * 修改数据时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始更新数据时的填充策略");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
