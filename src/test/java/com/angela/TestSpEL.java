package com.angela;


import com.angela.entity.TOPLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@Slf4j
public class TestSpEL {

    public static void main(String[] args) {
        String spELKey = "T(com.angela.entity.TOPLog).testSpEL()";
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(spELKey);
        log.info("{}",expression.getValue());

    }
}
