package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.VisionCheckRecord;

@Mapper
public interface VisionCheckRecordMapper {
    @Delete({
        "delete from vision_check_record",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into vision_check_record (id, vision_client_id, ",
        "eye_type, check_date, ",
        "data_type, picture_file, ",
        "pupil, se1, ds1, ",
        "dc1, axis1, se2, ",
        "ds2, dc2, axis2, ",
        "pd, mm_hg, gaze_h, ",
        "gaze_v, remark, enabled, ",
        "create_time, modified_time)",
        "values (#{id,jdbcType=CHAR}, #{visionClientId,jdbcType=CHAR}, ",
        "#{eyeType,jdbcType=CHAR}, #{checkDate,jdbcType=TIMESTAMP}, ",
        "#{dataType,jdbcType=BIT}, #{pictureFile,jdbcType=VARCHAR}, ",
        "#{pupil,jdbcType=DECIMAL}, #{se1,jdbcType=DECIMAL}, #{ds1,jdbcType=DECIMAL}, ",
        "#{dc1,jdbcType=DECIMAL}, #{axis1,jdbcType=INTEGER}, #{se2,jdbcType=DECIMAL}, ",
        "#{ds2,jdbcType=DECIMAL}, #{dc2,jdbcType=DECIMAL}, #{axis2,jdbcType=INTEGER}, ",
        "#{pd,jdbcType=INTEGER}, #{mmHg,jdbcType=DECIMAL}, #{gazeH,jdbcType=INTEGER}, ",
        "#{gazeV,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR}, #{enabled,jdbcType=BIT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(VisionCheckRecord record);

    @InsertProvider(type=VisionCheckRecordSqlProvider.class, method="insertSelective")
    int insertSelective(VisionCheckRecord record);

    @Select({
        "select",
        "id, vision_client_id, eye_type, check_date, data_type, picture_file, pupil, ",
        "se1, ds1, dc1, axis1, se2, ds2, dc2, axis2, pd, mm_hg, gaze_h, gaze_v, remark, ",
        "enabled, create_time, modified_time",
        "from vision_check_record",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="vision_client_id", property="visionClientId", jdbcType=JdbcType.CHAR),
        @Result(column="eye_type", property="eyeType", jdbcType=JdbcType.CHAR),
        @Result(column="check_date", property="checkDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_type", property="dataType", jdbcType=JdbcType.BIT),
        @Result(column="picture_file", property="pictureFile", jdbcType=JdbcType.VARCHAR),
        @Result(column="pupil", property="pupil", jdbcType=JdbcType.DECIMAL),
        @Result(column="se1", property="se1", jdbcType=JdbcType.DECIMAL),
        @Result(column="ds1", property="ds1", jdbcType=JdbcType.DECIMAL),
        @Result(column="dc1", property="dc1", jdbcType=JdbcType.DECIMAL),
        @Result(column="axis1", property="axis1", jdbcType=JdbcType.INTEGER),
        @Result(column="se2", property="se2", jdbcType=JdbcType.DECIMAL),
        @Result(column="ds2", property="ds2", jdbcType=JdbcType.DECIMAL),
        @Result(column="dc2", property="dc2", jdbcType=JdbcType.DECIMAL),
        @Result(column="axis2", property="axis2", jdbcType=JdbcType.INTEGER),
        @Result(column="pd", property="pd", jdbcType=JdbcType.INTEGER),
        @Result(column="mm_hg", property="mmHg", jdbcType=JdbcType.DECIMAL),
        @Result(column="gaze_h", property="gazeH", jdbcType=JdbcType.INTEGER),
        @Result(column="gaze_v", property="gazeV", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="enabled", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_time", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    VisionCheckRecord selectByPrimaryKey(String id);

    @UpdateProvider(type=VisionCheckRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VisionCheckRecord record);

    @Update({
        "update vision_check_record",
        "set vision_client_id = #{visionClientId,jdbcType=CHAR},",
          "eye_type = #{eyeType,jdbcType=CHAR},",
          "check_date = #{checkDate,jdbcType=TIMESTAMP},",
          "data_type = #{dataType,jdbcType=BIT},",
          "picture_file = #{pictureFile,jdbcType=VARCHAR},",
          "pupil = #{pupil,jdbcType=DECIMAL},",
          "se1 = #{se1,jdbcType=DECIMAL},",
          "ds1 = #{ds1,jdbcType=DECIMAL},",
          "dc1 = #{dc1,jdbcType=DECIMAL},",
          "axis1 = #{axis1,jdbcType=INTEGER},",
          "se2 = #{se2,jdbcType=DECIMAL},",
          "ds2 = #{ds2,jdbcType=DECIMAL},",
          "dc2 = #{dc2,jdbcType=DECIMAL},",
          "axis2 = #{axis2,jdbcType=INTEGER},",
          "pd = #{pd,jdbcType=INTEGER},",
          "mm_hg = #{mmHg,jdbcType=DECIMAL},",
          "gaze_h = #{gazeH,jdbcType=INTEGER},",
          "gaze_v = #{gazeV,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "enabled = #{enabled,jdbcType=BIT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(VisionCheckRecord record);
}