package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.SystemUserProfile;

public class SystemUserProfileSqlProvider {

    public String insertSelective(SystemUserProfile record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("system_user_profile");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=CHAR}");
        }

        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getAvatar() != null) {
            sql.VALUES("avatar", "#{avatar,jdbcType=VARCHAR}");
        }

        if (record.getGender() != null) {
            sql.VALUES("gender", "#{gender,jdbcType=TINYINT}");
        }

        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }

        if (record.getModifiedTime() != null) {
            sql.VALUES("modified_time", "#{modifiedTime,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SystemUserProfile record) {
        SQL sql = new SQL();
        sql.UPDATE("system_user_profile");

        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getAvatar() != null) {
            sql.SET("avatar = #{avatar,jdbcType=VARCHAR}");
        }

        if (record.getGender() != null) {
            sql.SET("gender = #{gender,jdbcType=TINYINT}");
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