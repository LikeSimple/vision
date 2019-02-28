package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.SystemUserRole;

public class SystemUserRoleSqlProvider {

    public String insertSelective(SystemUserRole record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("system_user_role");
        
        if (record.getSystemUserId() != null) {
            sql.VALUES("system_user_id", "#{systemUserId,jdbcType=CHAR}");
        }
        
        if (record.getRoleId() != null) {
            sql.VALUES("role_id", "#{roleId,jdbcType=CHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SystemUserRole record) {
        SQL sql = new SQL();
        sql.UPDATE("system_user_role");
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("system_user_id = #{systemUserId,jdbcType=CHAR}");
        sql.WHERE("role_id = #{roleId,jdbcType=CHAR}");
        
        return sql.toString();
    }
}