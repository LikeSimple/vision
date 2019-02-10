package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.SystemAdminProfile;

import java.util.List;

@Mapper
public interface SystemAdminProfileMapper {
    @Delete({
            "delete from system_admin_profile",
            "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into system_admin_profile (id, real_name, ",
            "avatar_image, gender, ",
            "created_time, modified_time)",
            "values (#{id,jdbcType=CHAR}, #{realName,jdbcType=VARCHAR}, ",
            "#{avatarImage,jdbcType=VARCHAR}, #{gender,jdbcType=TINYINT}, ",
            "#{createdTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(SystemAdminProfile record);

    @InsertProvider(type = SystemAdminProfileSqlProvider.class, method = "insertSelective")
    int insertSelective(SystemAdminProfile record);

    @Select({
            "select",
            "id, real_name, avatar_image, gender, created_time, modified_time",
            "from system_admin_profile",
            "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "real_name", property = "realName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "avatar_image", property = "avatarImage", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.TINYINT),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    SystemAdminProfile selectByPrimaryKey(String id);

    @UpdateProvider(type = SystemAdminProfileSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SystemAdminProfile record);

    @Update({
            "update system_admin_profile",
            "set real_name = #{realName,jdbcType=VARCHAR},",
            "avatar_image = #{avatarImage,jdbcType=VARCHAR},",
            "gender = #{gender,jdbcType=TINYINT},",
            "created_time = #{createdTime,jdbcType=TIMESTAMP},",
            "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(SystemAdminProfile record);

    @Select({
            "select",
            "id, real_name, avatar_image, gender, created_time, modified_time",
            "from system_admin_profile",
            "order by real_name"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "real_name", property = "realName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "avatar_image", property = "avatarImage", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.TINYINT),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SystemAdminProfile> selectAll();
}