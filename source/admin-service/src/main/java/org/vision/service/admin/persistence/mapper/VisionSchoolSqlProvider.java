package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.VisionSchool;

public class VisionSchoolSqlProvider {

    public String insertSelective(VisionSchool record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("vision_school");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=CHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            sql.VALUES("province", "#{province,jdbcType=VARCHAR}");
        }
        
        if (record.getCounty() != null) {
            sql.VALUES("county", "#{county,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            sql.VALUES("city", "#{city,jdbcType=VARCHAR}");
        }
        
        if (record.getDetailAddress() != null) {
            sql.VALUES("detail_address", "#{detailAddress,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedTime() != null) {
            sql.VALUES("modified_time", "#{modifiedTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(VisionSchool record) {
        SQL sql = new SQL();
        sql.UPDATE("vision_school");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getProvince() != null) {
            sql.SET("province = #{province,jdbcType=VARCHAR}");
        }
        
        if (record.getCounty() != null) {
            sql.SET("county = #{county,jdbcType=VARCHAR}");
        }
        
        if (record.getCity() != null) {
            sql.SET("city = #{city,jdbcType=VARCHAR}");
        }
        
        if (record.getDetailAddress() != null) {
            sql.SET("detail_address = #{detailAddress,jdbcType=VARCHAR}");
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