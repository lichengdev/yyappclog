package com.yonyou.bs.mapper;

import java.util.Map;

import com.yonyou.bs.entity.ClogEntity;

/**
 * 
 **
 * @qualiFild nc.bs.mapper.MyLogMapper.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2016年7月1日<br>
 * @version 1.0<br>
 */
@org.apache.ibatis.annotations.Mapper
public interface MyLogMapper
{
    
    int insertMyLog(@org.apache.ibatis.annotations.Param("clog") ClogEntity clog);
    
    @org.apache.ibatis.annotations.MapKey("pk_log")
    int updateMyLog(@org.apache.ibatis.annotations.Param("clog") ClogEntity clog);
    
    @org.apache.ibatis.annotations.MapKey("pk_log")
    int deleteMyLog(@org.apache.ibatis.annotations.Param("clog") ClogEntity clog);
    
    @org.apache.ibatis.annotations.MapKey("pk_log")
    int deleteMyLogByPk(@org.apache.ibatis.annotations.Param("clog") ClogEntity clog);
    
    int insertMyLog(@org.apache.ibatis.annotations.Param("logMap") Map<String, Object> logMap);
    
    @org.apache.ibatis.annotations.MapKey("pk_log")
    int updateMyLog(@org.apache.ibatis.annotations.Param("logMap") Map<String, Object> logMap);
    
    int deleteMyLogByPk(@org.apache.ibatis.annotations.Param("pk_log") String pk_log);
    
    @org.apache.ibatis.annotations.MapKey("pk_log")
    int deleteMyLog(@org.apache.ibatis.annotations.Param("logMap") Map<String, Object> logMap);
    
    @org.apache.ibatis.annotations.MapKey("pk_log")
    int deleteMyLogByPk(@org.apache.ibatis.annotations.Param("logMap") Map<String, Object> logMap);
    
}
