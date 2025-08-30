package com.angela.aspect;

import cn.hutool.core.bean.BeanUtil;
import com.angela.dto.TOPLogDTO;
import com.angela.entity.TOPLog;
import com.angela.service.TOPLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class OpPostAspect {
    @Autowired
    private TOPLogService logService;
    // 切点：匹配 com.angela.controller 包下所有类的 public String selectInfo() 方法
    @Pointcut("execution(public String com.angela.controller..*.selectInfo(..))")
    public void selectInfoMethod() {}

    // 方法执行成功后记录日志
    @AfterReturning(pointcut = "selectInfoMethod()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        log.info("执行完成: {} | 返回值: {}", methodName, result);
        TOPLog log = new TOPLog();
        log.setUser("angela");
        log.setOperation("select");
        log.setTime(new Timestamp(new Date().getTime()));

        TOPLogDTO dto=new TOPLogDTO();
        BeanUtil.copyProperties(log,dto);
        logService.doLog(dto);

    }
}
