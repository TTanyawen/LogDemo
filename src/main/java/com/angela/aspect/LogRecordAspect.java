package com.angela.aspect;


import cn.hutool.core.bean.BeanUtil;
import com.angela.annotations.LogRecord;
import com.angela.dto.TOPLogDTO;
import com.angela.entity.TOPLog;

import com.angela.service.TOPLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LogRecordAspect {

    @Autowired
    private TOPLogService logService;


    // 拦截所有带 @LogRecord 注解的方法
    @AfterReturning(value = "@annotation(com.angela.annotations.LogRecord)", returning = "result")
    public void recordLog(JoinPoint joinPoint, Object result) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            LogRecord logRecord = method.getAnnotation(LogRecord.class);
            String spEL = logRecord.spEL();

            if (!spEL.isEmpty()) {
                // 创建 SpEL 上下文
                StandardEvaluationContext context = new StandardEvaluationContext();
                Object[] args = joinPoint.getArgs();
                String[] paramNames = signature.getParameterNames();
                if (paramNames != null) {
                    for (int i = 0; i < paramNames.length; i++) {
                        context.setVariable(paramNames[i], args[i]);
                    }
                }
                context.setVariable("result", result); // 方法返回值也可以用 #result 引用

                // 解析 SpEL
                ExpressionParser parser = new SpelExpressionParser();
                TOPLog logObj = (TOPLog) parser.parseExpression(spEL).getValue(context);

                // 转 DTO 并调用日志服务
                TOPLogDTO dto = new TOPLogDTO();
                BeanUtil.copyProperties(logObj, dto);
                logService.doLog(dto);

                log.info("日志记录完成: {}", dto);
            }
        } catch (Exception e) {
            log.error("日志记录异常", e);
        }
    }
}

