package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.VisionActivityClientCheckRecord;

public class VisionActivityClientCheckRecordSqlProvider {

    public String insertSelective(VisionActivityClientCheckRecord record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("vision_activity_client_check_record");
        
        if (record.getVisionClientId() != null) {
            sql.VALUES("vision_client_id", "#{visionClientId,jdbcType=CHAR}");
        }
        
        if (record.getVisionActivityId() != null) {
            sql.VALUES("vision_activity_id", "#{visionActivityId,jdbcType=CHAR}");
        }
        
        if (record.getVisionCheckRecordId() != null) {
            sql.VALUES("vision_check_record_id", "#{visionCheckRecordId,jdbcType=CHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(VisionActivityClientCheckRecord record) {
        SQL sql = new SQL();
        sql.UPDATE("vision_activity_client_check_record");
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("vision_client_id = #{visionClientId,jdbcType=CHAR}");
        sql.WHERE("vision_activity_id = #{visionActivityId,jdbcType=CHAR}");
        sql.WHERE("vision_check_record_id = #{visionCheckRecordId,jdbcType=CHAR}");
        
        return sql.toString();
    }
}