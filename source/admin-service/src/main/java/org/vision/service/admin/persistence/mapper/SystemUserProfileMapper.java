package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.SystemUserProfile;

import java.util.List;

@Mapper
public interface SystemUserProfileMapper {
    @Delete({
            "delete from system_user_profile",
            "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into system_user_profile (id, name, ",
            "avatar, gender, ",
            "created_time, modified_time)",
            "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, ",
            "#{avatar,jdbcType=VARCHAR}, #{gender,jdbcType=TINYINT}, ",
            "#{createdTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(SystemUserProfile record);

    @InsertProvider(type = SystemUserProfileSqlProvider.class, method = "insertSelective")
    int insertSelective(SystemUserProfile record);

    @Select({
            "select",
            "id, name, avatar, gender, created_time, modified_time",
            "from system_user_profile",
            "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "avatar", property = "avatar", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.TINYINT),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    SystemUserProfile selectByPrimaryKey(String id);

    @UpdateProvider(type = SystemUserProfileSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SystemUserProfile record);

    @Update({
            "update system_user_profile",
            "set name = #{name,jdbcType=VARCHAR},",
            "avatar = #{avatar,jdbcType=VARCHAR},",
            "gender = #{gender,jdbcType=TINYINT},",
            "created_time = #{createdTime,jdbcType=TIMESTAMP},",
            "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(SystemUserProfile record);

    @Select({
            "select",
            "id, name, avatar, gender, created_time, modified_time",
            "from system_user_profile",
            "order by real_name"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "avatar", property = "avatar", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.TINYINT),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SystemUserProfile> selectAll();
}