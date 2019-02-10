package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.SystemAdminRole;

@Mapper
public interface SystemAdminRoleMapper {
    @Delete({
            "delete from system_admin_role",
            "where admin_id = #{adminId,jdbcType=CHAR}",
            "and role_id = #{roleId,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(@Param("adminId") String adminId, @Param("roleId") String roleId);

    @Insert({
            "insert into system_admin_role (admin_id, role_id, ",
            "created_time)",
            "values (#{adminId,jdbcType=CHAR}, #{roleId,jdbcType=CHAR}, ",
            "#{createdTime,jdbcType=TIMESTAMP})"
    })
    int insert(SystemAdminRole record);

    @InsertProvider(type = SystemAdminRoleSqlProvider.class, method = "insertSelective")
    int insertSelective(SystemAdminRole record);

    @Select({
            "select",
            "admin_id, role_id, created_time",
            "from system_admin_role",
            "where admin_id = #{adminId,jdbcType=CHAR}",
            "and role_id = #{roleId,jdbcType=CHAR}"
    })
    @Results({
            @Result(column = "admin_id", property = "adminId", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "role_id", property = "roleId", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP)
    })
    SystemAdminRole selectByPrimaryKey(@Param("adminId") String adminId, @Param("roleId") String roleId);

    @UpdateProvider(type = SystemAdminRoleSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SystemAdminRole record);

    @Update({
            "update system_admin_role",
            "set created_time = #{createdTime,jdbcType=TIMESTAMP}",
            "where admin_id = #{adminId,jdbcType=CHAR}",
            "and role_id = #{roleId,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(SystemAdminRole record);
}