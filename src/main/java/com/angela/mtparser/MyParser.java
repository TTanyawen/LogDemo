package com.angela.mtparser;

import cn.hutool.core.util.StrUtil;
import com.mzt.logapi.service.IParseFunction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyParser implements IParseFunction {

    @Override
    public String functionName() {
        return "MY_PARSER";
    }

    @Override
    public String apply(Object value) {
       return "Miss. "+value.toString();
    }

}
