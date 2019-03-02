package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.VisionActivityClient;

public class VisionActivityClientSqlProvider {

    public String insertSelective(VisionActivityClient record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("vision_activity_client");
        
        if (record.getVisionActivityId() != null) {
            sql.VALUES("vision_activity_id", "#{visionActivityId,jdbcType=CHAR}");
        }
        
        if (record.getVisionClientId() != null) {
            sql.VALUES("vision_client_id", "#{visionClientId,jdbcType=CHAR}");
        }

        if (record.getVisionMemberId() != null) {
            sql.VALUES("vision_member_id", "#{visionMemberId,jdbcType=CHAR}");
        }
        
        if (record.getEnabled() != null) {
            sql.VALUES("enabled", "#{enabled,jdbcType=BIT}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(VisionActivityClient record) {
        SQL sql = new SQL();
        sql.UPDATE("vision_activity_client");

        if (record.getVisionMemberId() != null) {
            sql.SET("vision_member_id = #{visionMemberId,jdbcType=CHAR}");
        }
        
        if (record.getEnabled() != null) {
            sql.SET("enabled = #{enabled,jdbcType=BIT}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("vision_activity_id = #{visionActivityId,jdbcType=CHAR}");
        sql.WHERE("vision_client_id = #{visionClientId,jdbcType=CHAR}");
        
        return sql.toString();
    }
}