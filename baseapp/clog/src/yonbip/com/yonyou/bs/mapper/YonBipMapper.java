package com.yonyou.bs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.data.repository.query.Param;

/**
 * 
 **
 * @qualiFild nc.bs.mapper.Yon_YBipMapper.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2016年7月1日<br>
 * @version 1.0<br>
 */
@Mapper
public interface YonBipMapper
{
    /**
     * *********************************************************** <br>
     * *说明： 查询表所在的库(schema)，表信息 <br>
     * @see <br>
     * @param table_name
     * @return <br>
     * @Map<String,String> <br>
     * @methods nc.bs.mapper.Yon_YBipMapper#getSchema <br>
     * @author LiBencheng <br>
     * @date Created on 2016年7月1日 <br>
     * @time 上午12:16:18 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    @Select("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE table_name = #{table_name} ")
    Map<String, String> getSchema(@Param("table_name") String table_name);
    
    /**
     * *********************************************************** <br>
     * *说明： <br>
     * @see <br>
     * @param selectSql
     * @return <br>
     * @List<Object> <br>
     * @methods nc.bs.mapper.Yon_YBipMapper#selectStr <br>
     * @author LiBencheng <br>
     * @date Created on 2016年7月1日 <br>
     * @time 上午12:16:15 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    @Select("${selectSql}")
    List<Object> selectStr(@Param("selectSql") String selectSql);
    
    /**
     * *********************************************************** <br>
     * *说明： <br>
     * @see <br>
     * @param selectSql
     * @return <br>
     * @List<Object> <br>
     * @methods nc.bs.mapper.Yon_YBipMapper#selectList <br>
     * @author LiBencheng <br>
     * @date Created on 2016年7月1日 <br>
     * @time 上午12:16:11 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    @Select("${selectSql}")
    List<Object> selectList(@Param("selectSql") String selectSql);
    
    /**
     * *********************************************************** <br>
     * *说明： <br>
     * @see <br>
     * @param selectSql
     * @return <br>
     * @List<Map<String,Object>> <br>
     * @methods nc.bs.mapper.Yon_YBipMapper#selectListMap <br>
     * @author LiBencheng <br>
     * @date Created on 2016年7月1日 <br>
     * @time 上午12:16:07 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    @Select("${selectSql}")
    List<Map<String, Object>> selectListMap(@Param("selectSql") String selectSql);
    
    /**
     * *********************************************************** <br>
     * *说明： <br>
     * @see <br>
     * @param selectSql
     * @return <br>
     * @Map<String,Object> <br>
     * @methods nc.bs.mapper.Yon_YBipMapper#selectMap <br>
     * @author LiBencheng <br>
     * @date Created on 2016年7月1日 <br>
     * @time 上午12:16:03 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    @Select("${selectSql}")
    Map<String, Object> selectMap(@Param("selectSql") String selectSql);
    
    /**
     * *********************************************************** <br>
     * *说明： <br>
     * @see <br>
     * @param inseSql
     * @return <br>
     * @int <br>
     * @methods nc.bs.mapper.Yon_YBipMapper#insert <br>
     * @author LiBencheng <br>
     * @date Created on 2016年7月1日 <br>
     * @time 上午12:16:00 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    @org.apache.ibatis.annotations.Insert("${inseSql}")
    int insert(@Param("inseSql") String inseSql);
    
    /**
     * *********************************************************** <br>
     * *说明： <br>
     * @see <br>
     * @param updateSql
     * @return <br>
     * @int <br>
     * @methods nc.bs.mapper.Yon_YBipMapper#update <br>
     * @author LiBencheng <br>
     * @date Created on 2016年7月1日 <br>
     * @time 上午12:15:57 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    @org.apache.ibatis.annotations.Update("${updateSql}")
    int update(@Param("updateSql") String updateSql);
    
    /**
     * *********************************************************** <br>
     * *说明： <br>
     * @see <br>
     * @param deleteSql
     * @return <br>
     * @int <br>
     * @methods nc.bs.mapper.Yon_YBipMapper#delete <br>
     * @author LiBencheng <br>
     * @date Created on 2016年7月1日 <br>
     * @time 上午12:15:54 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    @org.apache.ibatis.annotations.Delete("${deleteSql}")
    int delete(@Param("deleteSql") String deleteSql);
    
}
