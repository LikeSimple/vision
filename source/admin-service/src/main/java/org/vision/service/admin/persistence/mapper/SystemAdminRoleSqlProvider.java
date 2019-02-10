package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.SystemAdminRole;

public class SystemAdminRoleSqlProvider {

    public String insertSelective(SystemAdminRole record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("system_admin_role");

        if (record.getAdminId() != null) {
            sql.VALUES("admin_id", "#{adminId,jdbcType=CHAR}");
        }

        if (record.getRoleId() != null) {
            sql.VALUES("role_id", "#{roleId,jdbcType=CHAR}");
        }

        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SystemAdminRole record) {
        SQL sql = new SQL();
        sql.UPDATE("system_admin_role");

        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("admin_id = #{adminId,jdbcType=CHAR}");
        sql.WHERE("role_id = #{roleId,jdbcType=CHAR}");

        return sql.toString();
    }
}