package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.VisionClient;

public class VisionClientSqlProvider {

    public String insertSelective(VisionClient record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("vision_client");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=CHAR}");
        }

        if (record.getWxClientId() != null) {
            sql.VALUES("wx_client_id", "#{wxClientId,jdbcType=CHAR}");
        }

        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getAge() != null) {
            sql.VALUES("age", "#{age,jdbcType=INTEGER}");
        }

        if (record.getBirthday() != null) {
            sql.VALUES("birthday", "#{birthday,jdbcType=DATE}");
        }

        if (record.getPhoneNumber() != null) {
            sql.VALUES("phone_number", "#{phoneNumber,jdbcType=VARCHAR}");
        }

        if (record.getProvince() != null) {
            sql.VALUES("province", "#{province,jdbcType=VARCHAR}");
        }

        if (record.getCity() != null) {
            sql.VALUES("city", "#{city,jdbcType=VARCHAR}");
        }

        if (record.getCounty() != null) {
            sql.VALUES("county", "#{county,jdbcType=VARCHAR}");
        }

        if (record.getDetailAddress() != null) {
            sql.VALUES("detail_address", "#{detailAddress,jdbcType=VARCHAR}");
        }

        if (record.getGender() != null) {
            sql.VALUES("gender", "#{gender,jdbcType=BIT}");
        }

        if (record.getIdNumber() != null) {
            sql.VALUES("id_number", "#{idNumber,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(VisionClient record) {
        SQL sql = new SQL();
        sql.UPDATE("vision_client");

        if (record.getWxClientId() != null) {
            sql.SET("wx_client_id = #{wxClientId,jdbcType=CHAR}");
        }

        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getAge() != null) {
            sql.SET("age = #{age,jdbcType=INTEGER}");
        }

        if (record.getBirthday() != null) {
            sql.SET("birthday = #{birthday,jdbcType=DATE}");
        }

        if (record.getPhoneNumber() != null) {
            sql.SET("phone_number = #{phoneNumber,jdbcType=VARCHAR}");
        }

        if (record.getProvince() != null) {
            sql.SET("province = #{province,jdbcType=VARCHAR}");
        }

        if (record.getCity() != null) {
            sql.SET("city = #{city,jdbcType=VARCHAR}");
        }

        if (record.getCounty() != null) {
            sql.SET("county = #{county,jdbcType=VARCHAR}");
        }

        if (record.getDetailAddress() != null) {
            sql.SET("detail_address = #{detailAddress,jdbcType=VARCHAR}");
        }

        if (record.getGender() != null) {
            sql.SET("gender = #{gender,jdbcType=BIT}");
        }

        if (record.getIdNumber() != null) {
            sql.SET("id_number = #{idNumber,jdbcType=VARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=CHAR}");

        return sql.toString();
    }
}