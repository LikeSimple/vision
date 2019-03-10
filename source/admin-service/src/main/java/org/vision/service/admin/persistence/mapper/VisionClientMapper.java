package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.controller.criteria.VisionClientCriteria;
import org.vision.service.admin.persistence.model.VisionClient;
import org.vision.service.admin.persistence.model.VisionClientView;

import java.util.List;

@Mapper
public interface VisionClientMapper {
    @Delete({
        "delete from vision_client",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into vision_client (id, wx_client_id, ",
        "name, gender, age, ",
        "id_number, native_place, ",
        "height, weight, ",
        "birthday, phone_number, ",
        "province, city, ",
        "county, detail_address, ",
        "vision_acuity_left, vision_acuity_right, ",
        "vision_acuity, diopters_left, ",
        "diopters_right, astigmatism_left, ",
        "astigmatism_right, joint_luminosity_left, ",
        "joint_luminosity_right, axis_left, ",
        "axis_right, pupil_distance, ",
        "created_time, modified_time)",
        "values (#{id,jdbcType=CHAR}, #{wxClientId,jdbcType=CHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{gender,jdbcType=BIT}, #{age,jdbcType=INTEGER}, ",
        "#{idNumber,jdbcType=VARCHAR}, #{nativePlace,jdbcType=VARCHAR}, ",
        "#{height,jdbcType=DECIMAL}, #{weight,jdbcType=DECIMAL}, ",
        "#{birthday,jdbcType=DATE}, #{phoneNumber,jdbcType=VARCHAR}, ",
        "#{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, ",
        "#{county,jdbcType=VARCHAR}, #{detailAddress,jdbcType=VARCHAR}, ",
        "#{visionAcuityLeft,jdbcType=DECIMAL}, #{visionAcuityRight,jdbcType=DECIMAL}, ",
        "#{visionAcuity,jdbcType=DECIMAL}, #{dioptersLeft,jdbcType=INTEGER}, ",
        "#{dioptersRight,jdbcType=INTEGER}, #{astigmatismLeft,jdbcType=INTEGER}, ",
        "#{astigmatismRight,jdbcType=INTEGER}, #{jointLuminosityLeft,jdbcType=INTEGER}, ",
        "#{jointLuminosityRight,jdbcType=INTEGER}, #{axisLeft,jdbcType=INTEGER}, ",
        "#{axisRight,jdbcType=INTEGER}, #{pupilDistance,jdbcType=INTEGER}, ",
        "#{createdTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(VisionClient record);

    @InsertProvider(type=VisionClientSqlProvider.class, method="insertSelective")
    int insertSelective(VisionClient record);

    @Select({
        "select",
        "id, wx_client_id, name, gender, age, id_number, native_place, height, weight, ",
        "birthday, phone_number, province, city, county, detail_address, vision_acuity_left, ",
        "vision_acuity_right, vision_acuity, diopters_left, diopters_right, astigmatism_left, ",
        "astigmatism_right, joint_luminosity_left, joint_luminosity_right, axis_left, ",
        "axis_right, pupil_distance, created_time, modified_time",
        "from vision_client",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="wx_client_id", property="wxClientId", jdbcType=JdbcType.CHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.INTEGER),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="id_number", property="idNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="native_place", property="nativePlace", jdbcType=JdbcType.VARCHAR),
        @Result(column="height", property="height", jdbcType=JdbcType.DECIMAL),
        @Result(column="weight", property="weight", jdbcType=JdbcType.DECIMAL),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="phone_number", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="county", property="county", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail_address", property="detailAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="vision_acuity_left", property="visionAcuityLeft", jdbcType=JdbcType.DECIMAL),
        @Result(column="vision_acuity_right", property="visionAcuityRight", jdbcType=JdbcType.DECIMAL),
        @Result(column="vision_acuity", property="visionAcuity", jdbcType=JdbcType.DECIMAL),
        @Result(column="diopters_left", property="dioptersLeft", jdbcType=JdbcType.INTEGER),
        @Result(column="diopters_right", property="dioptersRight", jdbcType=JdbcType.INTEGER),
        @Result(column="astigmatism_left", property="astigmatismLeft", jdbcType=JdbcType.INTEGER),
        @Result(column="astigmatism_right", property="astigmatismRight", jdbcType=JdbcType.INTEGER),
        @Result(column="joint_luminosity_left", property="jointLuminosityLeft", jdbcType=JdbcType.INTEGER),
        @Result(column="joint_luminosity_right", property="jointLuminosityRight", jdbcType=JdbcType.INTEGER),
        @Result(column="axis_left", property="axisLeft", jdbcType=JdbcType.INTEGER),
        @Result(column="axis_right", property="axisRight", jdbcType=JdbcType.INTEGER),
        @Result(column="pupil_distance", property="pupilDistance", jdbcType=JdbcType.INTEGER),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_time", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    VisionClient selectByPrimaryKey(String id);

    @UpdateProvider(type=VisionClientSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VisionClient record);

    @Update({
        "update vision_client",
        "set wx_client_id = #{wxClientId,jdbcType=CHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "gender = #{gender,jdbcType=BIT},",
          "age = #{age,jdbcType=INTEGER},",
          "id_number = #{idNumber,jdbcType=VARCHAR},",
          "native_place = #{nativePlace,jdbcType=VARCHAR},",
          "height = #{height,jdbcType=DECIMAL},",
          "weight = #{weight,jdbcType=DECIMAL},",
          "birthday = #{birthday,jdbcType=DATE},",
          "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
          "province = #{province,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "county = #{county,jdbcType=VARCHAR},",
          "detail_address = #{detailAddress,jdbcType=VARCHAR},",
          "vision_acuity_left = #{visionAcuityLeft,jdbcType=DECIMAL},",
          "vision_acuity_right = #{visionAcuityRight,jdbcType=DECIMAL},",
          "vision_acuity = #{visionAcuity,jdbcType=DECIMAL},",
          "diopters_left = #{dioptersLeft,jdbcType=INTEGER},",
          "diopters_right = #{dioptersRight,jdbcType=INTEGER},",
          "astigmatism_left = #{astigmatismLeft,jdbcType=INTEGER},",
          "astigmatism_right = #{astigmatismRight,jdbcType=INTEGER},",
          "joint_luminosity_left = #{jointLuminosityLeft,jdbcType=INTEGER},",
          "joint_luminosity_right = #{jointLuminosityRight,jdbcType=INTEGER},",
          "axis_left = #{axisLeft,jdbcType=INTEGER},",
          "axis_right = #{axisRight,jdbcType=INTEGER},",
          "pupil_distance = #{pupilDistance,jdbcType=INTEGER},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(VisionClient record);

    @Select({
            "select",
            "id, wx_client_id, name, gender, age, id_number, native_place, height, weight, ",
            "birthday, phone_number, province, city, county, detail_address, vision_acuity_left, ",
            "vision_acuity_right, vision_acuity, diopters_left, diopters_right, astigmatism_left, ",
            "astigmatism_right, joint_luminosity_left, joint_luminosity_right, axis_left, ",
            "axis_right, pupil_distance, created_time, modified_time",
            "from vision_client",
            "where id_number = #{idNumber,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "wx_client_id", property = "wxClientId", jdbcType = JdbcType.CHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.INTEGER),
            @Result(column = "age", property = "age", jdbcType = JdbcType.INTEGER),
            @Result(column = "id_number", property = "idNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "native_place", property = "nativePlace", jdbcType = JdbcType.VARCHAR),
            @Result(column = "height", property = "height", jdbcType = JdbcType.DECIMAL),
            @Result(column = "weight", property = "weight", jdbcType = JdbcType.DECIMAL),
            @Result(column = "birthday", property = "birthday", jdbcType = JdbcType.DATE),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "province", property = "province", jdbcType = JdbcType.VARCHAR),
            @Result(column = "city", property = "city", jdbcType = JdbcType.VARCHAR),
            @Result(column = "county", property = "county", jdbcType = JdbcType.VARCHAR),
            @Result(column = "detail_address", property = "detailAddress", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vision_acuity_left", property = "visionAcuityLeft", jdbcType = JdbcType.DECIMAL),
            @Result(column = "vision_acuity_right", property = "visionAcuityRight", jdbcType = JdbcType.DECIMAL),
            @Result(column = "vision_acuity", property = "visionAcuity", jdbcType = JdbcType.DECIMAL),
            @Result(column = "diopters_left", property = "dioptersLeft", jdbcType = JdbcType.INTEGER),
            @Result(column = "diopters_right", property = "dioptersRight", jdbcType = JdbcType.INTEGER),
            @Result(column = "astigmatism_left", property = "astigmatismLeft", jdbcType = JdbcType.INTEGER),
            @Result(column = "astigmatism_right", property = "astigmatismRight", jdbcType = JdbcType.INTEGER),
            @Result(column = "joint_luminosity_left", property = "jointLuminosityLeft", jdbcType = JdbcType.INTEGER),
            @Result(column = "joint_luminosity_right", property = "jointLuminosityRight", jdbcType = JdbcType.INTEGER),
            @Result(column = "axis_left", property = "axisLeft", jdbcType = JdbcType.INTEGER),
            @Result(column = "axis_right", property = "axisRight", jdbcType = JdbcType.INTEGER),
            @Result(column = "pupil_distance", property = "pupilDistance", jdbcType = JdbcType.INTEGER),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    VisionClient selectByIdNumber(String idNumber);

    List<VisionClientView> selectByCriteria(VisionClientCriteria visionClientCriteria);

}