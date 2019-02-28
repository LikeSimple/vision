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
        
        if (record.getGender() != null) {
            sql.VALUES("gender", "#{gender,jdbcType=BIT}");
        }
        
        if (record.getAge() != null) {
            sql.VALUES("age", "#{age,jdbcType=INTEGER}");
        }
        
        if (record.getIdNumber() != null) {
            sql.VALUES("id_number", "#{idNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getNativePlace() != null) {
            sql.VALUES("native_place", "#{nativePlace,jdbcType=VARCHAR}");
        }
        
        if (record.getHeight() != null) {
            sql.VALUES("height", "#{height,jdbcType=DECIMAL}");
        }
        
        if (record.getWeight() != null) {
            sql.VALUES("weight", "#{weight,jdbcType=DECIMAL}");
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
        
        if (record.getVisionAcuityLeft() != null) {
            sql.VALUES("vision_acuity_left", "#{visionAcuityLeft,jdbcType=DECIMAL}");
        }
        
        if (record.getVisionAcuityRight() != null) {
            sql.VALUES("vision_acuity_right", "#{visionAcuityRight,jdbcType=DECIMAL}");
        }
        
        if (record.getVisionAcuity() != null) {
            sql.VALUES("vision_acuity", "#{visionAcuity,jdbcType=DECIMAL}");
        }
        
        if (record.getDioptersLeft() != null) {
            sql.VALUES("diopters_left", "#{dioptersLeft,jdbcType=INTEGER}");
        }
        
        if (record.getDioptersRight() != null) {
            sql.VALUES("diopters_right", "#{dioptersRight,jdbcType=INTEGER}");
        }
        
        if (record.getAstigmatismLeft() != null) {
            sql.VALUES("astigmatism_left", "#{astigmatismLeft,jdbcType=INTEGER}");
        }
        
        if (record.getAstigmatismRight() != null) {
            sql.VALUES("astigmatism_right", "#{astigmatismRight,jdbcType=INTEGER}");
        }
        
        if (record.getJointLuminosityLeft() != null) {
            sql.VALUES("joint_luminosity_left", "#{jointLuminosityLeft,jdbcType=INTEGER}");
        }
        
        if (record.getJointLuminosityRight() != null) {
            sql.VALUES("joint_luminosity_right", "#{jointLuminosityRight,jdbcType=INTEGER}");
        }
        
        if (record.getAxisLeft() != null) {
            sql.VALUES("axis_left", "#{axisLeft,jdbcType=INTEGER}");
        }
        
        if (record.getAxisRight() != null) {
            sql.VALUES("axis_right", "#{axisRight,jdbcType=INTEGER}");
        }
        
        if (record.getPupilDistance() != null) {
            sql.VALUES("pupil_distance", "#{pupilDistance,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedTime() != null) {
            sql.VALUES("modified_time", "#{modifiedTime,jdbcType=TIMESTAMP}");
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
        
        if (record.getGender() != null) {
            sql.SET("gender = #{gender,jdbcType=BIT}");
        }
        
        if (record.getAge() != null) {
            sql.SET("age = #{age,jdbcType=INTEGER}");
        }
        
        if (record.getIdNumber() != null) {
            sql.SET("id_number = #{idNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getNativePlace() != null) {
            sql.SET("native_place = #{nativePlace,jdbcType=VARCHAR}");
        }
        
        if (record.getHeight() != null) {
            sql.SET("height = #{height,jdbcType=DECIMAL}");
        }
        
        if (record.getWeight() != null) {
            sql.SET("weight = #{weight,jdbcType=DECIMAL}");
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
        
        if (record.getVisionAcuityLeft() != null) {
            sql.SET("vision_acuity_left = #{visionAcuityLeft,jdbcType=DECIMAL}");
        }
        
        if (record.getVisionAcuityRight() != null) {
            sql.SET("vision_acuity_right = #{visionAcuityRight,jdbcType=DECIMAL}");
        }
        
        if (record.getVisionAcuity() != null) {
            sql.SET("vision_acuity = #{visionAcuity,jdbcType=DECIMAL}");
        }
        
        if (record.getDioptersLeft() != null) {
            sql.SET("diopters_left = #{dioptersLeft,jdbcType=INTEGER}");
        }
        
        if (record.getDioptersRight() != null) {
            sql.SET("diopters_right = #{dioptersRight,jdbcType=INTEGER}");
        }
        
        if (record.getAstigmatismLeft() != null) {
            sql.SET("astigmatism_left = #{astigmatismLeft,jdbcType=INTEGER}");
        }
        
        if (record.getAstigmatismRight() != null) {
            sql.SET("astigmatism_right = #{astigmatismRight,jdbcType=INTEGER}");
        }
        
        if (record.getJointLuminosityLeft() != null) {
            sql.SET("joint_luminosity_left = #{jointLuminosityLeft,jdbcType=INTEGER}");
        }
        
        if (record.getJointLuminosityRight() != null) {
            sql.SET("joint_luminosity_right = #{jointLuminosityRight,jdbcType=INTEGER}");
        }
        
        if (record.getAxisLeft() != null) {
            sql.SET("axis_left = #{axisLeft,jdbcType=INTEGER}");
        }
        
        if (record.getAxisRight() != null) {
            sql.SET("axis_right = #{axisRight,jdbcType=INTEGER}");
        }
        
        if (record.getPupilDistance() != null) {
            sql.SET("pupil_distance = #{pupilDistance,jdbcType=INTEGER}");
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