package com.angela.controller;

import cn.hutool.core.bean.BeanUtil;
import com.angela.dto.TOPLogDTO;
import com.angela.entity.TOPLog;
import com.angela.service.TOPLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/op")
public class OperationController {
    @Autowired
    private TOPLogService logService;
    @RequestMapping("/select")
    public String selectInfo() {
//        TOPLog log = new TOPLog();
//        log.setUser("angela");
//        log.setOperation("select");
//        log.setTime(new Timestamp(new Date().getTime()));
//
//        TOPLogDTO dto=new TOPLogDTO();
//        BeanUtil.copyProperties(log,dto);
//        logService.doLog(dto);

        return "selectInfo";
    }
//    @RequestMapping("/update")
//    public String updateInfo() {
//        return "updateInfo";
//    }
//    @RequestMapping("/delete")
//    public String deleteInfo() {
//        return "deleteInfo";
//    }

}
