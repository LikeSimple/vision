package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.VisionSchoolClassMember;

public class VisionSchoolClassMemberSqlProvider {

    public String insertSelective(VisionSchoolClassMember record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("vision_school_class_member");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=CHAR}");
        }

        if (record.getVisionClassId() != null) {
            sql.VALUES("vision_class_id", "#{visionClassId,jdbcType=CHAR}");
        }

        if (record.getStudentNumber() != null) {
            sql.VALUES("student_number", "#{studentNumber,jdbcType=VARCHAR}");
        }

        if (record.getVisionClientId() != null) {
            sql.VALUES("vision_client_id", "#{visionClientId,jdbcType=CHAR}");
        }

        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }

        if (record.getModifiedTime() != null) {
            sql.VALUES("modified_time", "#{modifiedTime,jdbcType=TIMESTAMP}");
        }

        if (record.getEnabled() != null) {
            sql.VALUES("enabled", "#{enabled,jdbcType=BIT}");
        }


        return sql.toString();
    }

    public String updateByPrimaryKeySelective(VisionSchoolClassMember record) {
        SQL sql = new SQL();
        sql.UPDATE("vision_school_class_member");

        if (record.getVisionClassId() != null) {
            sql.SET("vision_class_id = #{visionClassId,jdbcType=CHAR}");
        }

        if (record.getStudentNumber() != null) {
            sql.SET("student_number = #{studentNumber,jdbcType=VARCHAR}");
        }

        if (record.getVisionClientId() != null) {
            sql.SET("vision_client_id = #{visionClientId,jdbcType=CHAR}");
        }

        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }

        if (record.getModifiedTime() != null) {
            sql.SET("modified_time = #{modifiedTime,jdbcType=TIMESTAMP}");
        }

        if (record.getEnabled() != null) {
            sql.SET("enabled = #{enabled,jdbcType=BIT}");
        }

        sql.WHERE("id = #{id,jdbcType=CHAR}");

        return sql.toString();
    }
}