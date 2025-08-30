package com.angela.controller;

import cn.hutool.core.bean.BeanUtil;
import com.angela.annotations.LogRecord;
import com.angela.dto.TOPLogDTO;
import com.angela.entity.TOPLog;
import com.angela.service.TOPLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/op")
public class OperationController {
    @Autowired
    private TOPLogService logService;


    /**
     * SpringAOP方式log
     */
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

    /**
     * SpEL+自定义注解+AOP 方式log
     */
    @RequestMapping("/select02")
    @LogRecord(spEL = "T(com.angela.entity.TOPLog).createLog('select')")
    public String selectInfo02() {
//        doLog_SPEL("angela", "select");
        return "selectInfo";
    }

    /**
     * 美团 mzt-biz-log 操作日志框架
     */
    @RequestMapping("/select03")
    public String selectInfo03() {
        TOPLog log = new TOPLog();
        log.setUser("angela");
        log.setOperation("select");
        log.setTime(new Timestamp(new Date().getTime()));

        TOPLogDTO dto=new TOPLogDTO();
        BeanUtil.copyProperties(log,dto);
        logService.doLog02(dto);
        return "selectInfo";
    }

    //基础的 SpEL 示例
    public void doLog_SPEL(String user, String operation){
        // 定义 SpEL 表达式，调用静态方法生成日志
        String spELKey = "T(com.angela.entity.TOPLog).createLog(#user, #operation)";

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(spELKey);

        // 准备上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("user", "angela");
        context.setVariable("operation", "select");

        // 执行 SpEL 表达式
        TOPLog log = (TOPLog) expression.getValue(context);

        // 转 DTO 并调用日志服务
        TOPLogDTO dto = new TOPLogDTO();
        BeanUtil.copyProperties(log, dto);
        logService.doLog(dto);
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
