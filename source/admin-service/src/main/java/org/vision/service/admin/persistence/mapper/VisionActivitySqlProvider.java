package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.VisionActivity;

public class VisionActivitySqlProvider {

    public String insertSelective(VisionActivity record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("vision_activity");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=CHAR}");
        }

        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getAddress() != null) {
            sql.VALUES("address", "#{address,jdbcType=VARCHAR}");
        }

        if (record.getBeginDate() != null) {
            sql.VALUES("begin_date", "#{beginDate,jdbcType=TIMESTAMP}");
        }

        if (record.getEndDate() != null) {
            sql.VALUES("end_date", "#{endDate,jdbcType=TIMESTAMP}");
        }

        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }

        if (record.getContactMan() != null) {
            sql.VALUES("contact_man", "#{contactMan,jdbcType=VARCHAR}");
        }

        if (record.getContactPhoneNumber() != null) {
            sql.VALUES("contact_phone_number", "#{contactPhoneNumber,jdbcType=VARCHAR}");
        }

        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(VisionActivity record) {
        SQL sql = new SQL();
        sql.UPDATE("vision_activity");

        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getAddress() != null) {
            sql.SET("address = #{address,jdbcType=VARCHAR}");
        }

        if (record.getBeginDate() != null) {
            sql.SET("begin_date = #{beginDate,jdbcType=TIMESTAMP}");
        }

        if (record.getEndDate() != null) {
            sql.SET("end_date = #{endDate,jdbcType=TIMESTAMP}");
        }

        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=VARCHAR}");
        }

        if (record.getContactMan() != null) {
            sql.SET("contact_man = #{contactMan,jdbcType=VARCHAR}");
        }

        if (record.getContactPhoneNumber() != null) {
            sql.SET("contact_phone_number = #{contactPhoneNumber,jdbcType=VARCHAR}");
        }

        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=CHAR}");

        return sql.toString();
    }
}