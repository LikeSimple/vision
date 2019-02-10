package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.SystemAuthority;

@Mapper
public interface SystemAuthorityMapper {
    @Delete({
            "delete from system_authority",
            "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into system_authority (id, name, `desc`, ",
            "created_time, modified_time)",
            "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{desc,jdbcType=VARCHAR}, ",
            "#{createdTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(SystemAuthority record);

    @InsertProvider(type = SystemAuthoritySqlProvider.class, method = "insertSelective")
    int insertSelective(SystemAuthority record);

    @Select({
            "select",
            "id, `name`, `desc`, created_time, modified_time",
            "from system_authority",
            "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "desc", property = "desc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    SystemAuthority selectByPrimaryKey(String id);

    @UpdateProvider(type = SystemAuthoritySqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SystemAuthority record);

    @Update({
            "update system_authority",
            "set `name` = #{name,jdbcType=VARCHAR},",
            "`desc` = #{desc,jdbcType=VARCHAR},",
            "created_time = #{createdTime,jdbcType=TIMESTAMP},",
            "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(SystemAuthority record);
}