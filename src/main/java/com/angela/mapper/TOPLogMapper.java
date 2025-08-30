package com.angela.mapper;

import com.angela.dto.TOPLogDTO;
import com.angela.entity.TOPLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TOPLogMapper extends BaseMapper<TOPLog> {

//    @Select("select * from t_op_log")
//    public TOPLog getTOPLog();

//    @Insert("insert into t_op_log(user,operation) values(#{user},#{operation})")
//    public String doLog(TOPLogDTO dto);


}
