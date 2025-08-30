package com.angela.service;

import com.angela.annotations.LogRecord;
import com.angela.dto.TOPLogDTO;
import com.angela.entity.TOPLog;
import com.angela.mapper.TOPLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TOPLogService {
    @Autowired
    private TOPLogMapper tOPLogMapper;
    public List<TOPLog> getTOPLog() {
        return tOPLogMapper.selectList( null);
    }

    @com.mzt.logapi.starter.annotation.LogRecord(
            success = """
                操作人：{MY_PARSER{#dto.user}}， \
                操作时间：{{#dto.time}}， \
                操作类型：{{#dto.operation}};
                """,
            type = "selectLog",
            bizNo = "{{#bizNo}}",
            extra = "{{#dto.toString()}}"
    )
    public void doLog02(TOPLogDTO dto) {
        TOPLog entity=new TOPLog();
        entity.setUser(dto.getUser());
        entity.setOperation(dto.getOperation());
        entity.setTime(dto.getTime());
        tOPLogMapper.insert(entity);
    }
    public String doLog(TOPLogDTO dto) {
        TOPLog entity=new TOPLog();
        entity.setUser(dto.getUser());
        entity.setOperation(dto.getOperation());
        entity.setTime(dto.getTime());
        tOPLogMapper.insert(entity);
        return "doLog";
    }
}
