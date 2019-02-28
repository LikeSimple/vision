package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.SystemResource;

@Mapper
public interface SystemResourceMapper {
    @Delete({
        "delete from system_resource",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into system_resource (id, `name`, serial_key, ",
        "url, icon, remark, ",
        "enabled, created_time, ",
        "modified_time)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{serialKey,jdbcType=VARCHAR}, ",
        "#{url,jdbcType=VARCHAR}, #{icon,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{enabled,jdbcType=BIT}, #{createdTime,jdbcType=TIMESTAMP}, ",
        "#{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(SystemResource record);

    @InsertProvider(type=SystemResourceSqlProvider.class, method="insertSelective")
    int insertSelective(SystemResource record);

    @Select({
        "select",
        "id, `name`, serial_key, url, icon, remark, enabled, created_time, modified_time",
        "from system_resource",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="serial_key", property="serialKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="enabled", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_time", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SystemResource selectByPrimaryKey(String id);

    @UpdateProvider(type=SystemResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SystemResource record);

    @Update({
        "update system_resource",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "serial_key = #{serialKey,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "enabled = #{enabled,jdbcType=BIT},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(SystemResource record);
}