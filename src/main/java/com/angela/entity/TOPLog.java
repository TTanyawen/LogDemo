package com.angela.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("t_op_log")
public class TOPLog {
    private Integer id;
    private String user;
    private String operation;
    private Timestamp time;
}
