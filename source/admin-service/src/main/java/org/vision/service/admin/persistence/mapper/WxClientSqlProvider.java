package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.WxClient;

public class WxClientSqlProvider {

    public String insertSelective(WxClient record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("wx_client");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=CHAR}");
        }

        if (record.getPublicId() != null) {
            sql.VALUES("public_id", "#{publicId,jdbcType=INTEGER}");
        }

        if (record.getAppId() != null) {
            sql.VALUES("app_id", "#{appId,jdbcType=VARCHAR}");
        }

        if (record.getOpenId() != null) {
            sql.VALUES("open_id", "#{openId,jdbcType=VARCHAR}");
        }

        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getWxNick() != null) {
            sql.VALUES("wx_nick", "#{wxNick,jdbcType=VARCHAR}");
        }

        if (record.getAvatarImage() != null) {
            sql.VALUES("avatar_image", "#{avatarImage,jdbcType=VARCHAR}");
        }

        if (record.getGender() != null) {
            sql.VALUES("gender", "#{gender,jdbcType=BIT}");
        }

        if (record.getAge() != null) {
            sql.VALUES("age", "#{age,jdbcType=INTEGER}");
        }

        if (record.getBirthday() != null) {
            sql.VALUES("birthday", "#{birthday,jdbcType=DATE}");
        }

        if (record.getIdNumber() != null) {
            sql.VALUES("id_number", "#{idNumber,jdbcType=VARCHAR}");
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

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(WxClient record) {
        SQL sql = new SQL();
        sql.UPDATE("wx_client");

        if (record.getPublicId() != null) {
            sql.SET("public_id = #{publicId,jdbcType=INTEGER}");
        }

        if (record.getAppId() != null) {
            sql.SET("app_id = #{appId,jdbcType=VARCHAR}");
        }

        if (record.getOpenId() != null) {
            sql.SET("open_id = #{openId,jdbcType=VARCHAR}");
        }

        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getWxNick() != null) {
            sql.SET("wx_nick = #{wxNick,jdbcType=VARCHAR}");
        }

        if (record.getAvatarImage() != null) {
            sql.SET("avatar_image = #{avatarImage,jdbcType=VARCHAR}");
        }

        if (record.getGender() != null) {
            sql.SET("gender = #{gender,jdbcType=BIT}");
        }

        if (record.getAge() != null) {
            sql.SET("age = #{age,jdbcType=INTEGER}");
        }

        if (record.getBirthday() != null) {
            sql.SET("birthday = #{birthday,jdbcType=DATE}");
        }

        if (record.getIdNumber() != null) {
            sql.SET("id_number = #{idNumber,jdbcType=VARCHAR}");
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

        sql.WHERE("id = #{id,jdbcType=CHAR}");

        return sql.toString();
    }
}