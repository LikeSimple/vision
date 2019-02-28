package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.SystemRoleAuthority;

public class SystemRoleAuthoritySqlProvider {

    public String insertSelective(SystemRoleAuthority record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("system_role_authority");
        
        if (record.getRoleId() != null) {
            sql.VALUES("role_id", "#{roleId,jdbcType=CHAR}");
        }
        
        if (record.getAuthorityId() != null) {
            sql.VALUES("authority_id", "#{authorityId,jdbcType=CHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SystemRoleAuthority record) {
        SQL sql = new SQL();
        sql.UPDATE("system_role_authority");
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("role_id = #{roleId,jdbcType=CHAR}");
        sql.WHERE("authority_id = #{authorityId,jdbcType=CHAR}");
        
        return sql.toString();
    }
}