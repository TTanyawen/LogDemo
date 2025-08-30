package com.angela.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("t_op_log")
public class TOPLog {
    private Integer id;
    private String user;
    private String operation;
    private Timestamp time;

    public static String testSpEL() {
        return "testSpEL";
    }

    // 静态方法用于 SpEL
    public static TOPLog createLog(String operation) {
        TOPLog log = new TOPLog();
        log.setUser("angela");//先写死，后期从上下文获取
        log.setOperation(operation);
        log.setTime(new Timestamp(new Date().getTime()));
        return log;
    }
}
