package com.angela.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TOPLogDTO {
    private Integer id;
    private String user;
    private String operation;
    private Timestamp time;
}
