package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.Province;

public class ProvinceSqlProvider {

    public String insertSelective(Province record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("province");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=CHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedTime() != null) {
            sql.VALUES("modified_time", "#{modifiedTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Province record) {
        SQL sql = new SQL();
        sql.UPDATE("province");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedTime() != null) {
            sql.SET("modified_time = #{modifiedTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=CHAR}");
        
        return sql.toString();
    }
}