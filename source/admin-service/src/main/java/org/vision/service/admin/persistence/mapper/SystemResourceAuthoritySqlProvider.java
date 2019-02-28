package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.SystemResourceAuthority;

public class SystemResourceAuthoritySqlProvider {

    public String insertSelective(SystemResourceAuthority record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("system_resource_authority");
        
        if (record.getResourceId() != null) {
            sql.VALUES("resource_id", "#{resourceId,jdbcType=CHAR}");
        }
        
        if (record.getAuthorityId() != null) {
            sql.VALUES("authority_id", "#{authorityId,jdbcType=CHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SystemResourceAuthority record) {
        SQL sql = new SQL();
        sql.UPDATE("system_resource_authority");
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("resource_id = #{resourceId,jdbcType=CHAR}");
        sql.WHERE("authority_id = #{authorityId,jdbcType=CHAR}");
        
        return sql.toString();
    }
}