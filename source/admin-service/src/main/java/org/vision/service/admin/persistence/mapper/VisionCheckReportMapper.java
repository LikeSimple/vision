package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.VisionCheckReport;

@Mapper
public interface VisionCheckReportMapper {
    @Delete({
            "delete from vision_check_report",
            "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into vision_check_report (id, eye_type, check_date, ",
            "data_type, picture_file, ",
            "pupil, se1, ds1, ",
            "dc1, axis1, se2, ",
            "ds2, dc2, axis2, ",
            "pd, mm_hg, gaze_h, ",
            "gaze_v, remark, enabled, ",
            "create_time, modified_time)",
            "values (#{id,jdbcType=CHAR}, #{eyeType,jdbcType=CHAR}, #{checkDate,jdbcType=TIMESTAMP}, ",
            "#{dataType,jdbcType=BIT}, #{pictureFile,jdbcType=VARCHAR}, ",
            "#{pupil,jdbcType=DECIMAL}, #{se1,jdbcType=DECIMAL}, #{ds1,jdbcType=DECIMAL}, ",
            "#{dc1,jdbcType=DECIMAL}, #{axis1,jdbcType=INTEGER}, #{se2,jdbcType=DECIMAL}, ",
            "#{ds2,jdbcType=DECIMAL}, #{dc2,jdbcType=DECIMAL}, #{axis2,jdbcType=INTEGER}, ",
            "#{pd,jdbcType=INTEGER}, #{mmHg,jdbcType=DECIMAL}, #{gazeH,jdbcType=INTEGER}, ",
            "#{gazeV,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR}, #{enabled,jdbcType=BIT}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(VisionCheckReport record);

    @InsertProvider(type = VisionCheckReportSqlProvider.class, method = "insertSelective")
    int insertSelective(VisionCheckReport record);

    @Select({
            "select",
            "id, eye_type, check_date, data_type, picture_file, pupil, se1, ds1, dc1, axis1, ",
            "se2, ds2, dc2, axis2, pd, mm_hg, gaze_h, gaze_v, remark, enabled, create_time, ",
            "modified_time",
            "from vision_check_report",
            "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "eye_type", property = "eyeType", jdbcType = JdbcType.CHAR),
            @Result(column = "check_date", property = "checkDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "data_type", property = "dataType", jdbcType = JdbcType.BIT),
            @Result(column = "picture_file", property = "pictureFile", jdbcType = JdbcType.VARCHAR),
            @Result(column = "pupil", property = "pupil", jdbcType = JdbcType.DECIMAL),
            @Result(column = "se1", property = "se1", jdbcType = JdbcType.DECIMAL),
            @Result(column = "ds1", property = "ds1", jdbcType = JdbcType.DECIMAL),
            @Result(column = "dc1", property = "dc1", jdbcType = JdbcType.DECIMAL),
            @Result(column = "axis1", property = "axis1", jdbcType = JdbcType.INTEGER),
            @Result(column = "se2", property = "se2", jdbcType = JdbcType.DECIMAL),
            @Result(column = "ds2", property = "ds2", jdbcType = JdbcType.DECIMAL),
            @Result(column = "dc2", property = "dc2", jdbcType = JdbcType.DECIMAL),
            @Result(column = "axis2", property = "axis2", jdbcType = JdbcType.INTEGER),
            @Result(column = "pd", property = "pd", jdbcType = JdbcType.INTEGER),
            @Result(column = "mm_hg", property = "mmHg", jdbcType = JdbcType.DECIMAL),
            @Result(column = "gaze_h", property = "gazeH", jdbcType = JdbcType.INTEGER),
            @Result(column = "gaze_v", property = "gazeV", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "enabled", property = "enabled", jdbcType = JdbcType.BIT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    VisionCheckReport selectByPrimaryKey(String id);

    @UpdateProvider(type = VisionCheckReportSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VisionCheckReport record);

    @Update({
            "update vision_check_report",
            "set eye_type = #{eyeType,jdbcType=CHAR},",
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
    int updateByPrimaryKey(VisionCheckReport record);
}