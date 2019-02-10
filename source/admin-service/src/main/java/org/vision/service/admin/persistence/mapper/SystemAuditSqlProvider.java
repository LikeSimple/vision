package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.SystemAudit;

public class SystemAuditSqlProvider {

    public String insertSelective(SystemAudit record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("system_audit");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }

        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=CHAR}");
        }

        if (record.getRealName() != null) {
            sql.VALUES("real_name", "#{realName,jdbcType=VARCHAR}");
        }

        if (record.getAction() != null) {
            sql.VALUES("action", "#{action,jdbcType=VARCHAR}");
        }

        if (record.getParameters() != null) {
            sql.VALUES("parameters", "#{parameters,jdbcType=VARCHAR}");
        }

        if (record.getExecutionTime() != null) {
            sql.VALUES("execution_time", "#{executionTime,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SystemAudit record) {
        SQL sql = new SQL();
        sql.UPDATE("system_audit");

        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=CHAR}");
        }

        if (record.getRealName() != null) {
            sql.SET("real_name = #{realName,jdbcType=VARCHAR}");
        }

        if (record.getAction() != null) {
            sql.SET("action = #{action,jdbcType=VARCHAR}");
        }

        if (record.getParameters() != null) {
            sql.SET("parameters = #{parameters,jdbcType=VARCHAR}");
        }

        if (record.getExecutionTime() != null) {
            sql.SET("execution_time = #{executionTime,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("id = #{id,jdbcType=INTEGER}");

        return sql.toString();
    }
}