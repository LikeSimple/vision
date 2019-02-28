package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.VisionActivityClientCheckRecord;

@Mapper
public interface VisionActivityClientCheckRecordMapper {
    @Delete({
        "delete from vision_activity_client_check_record",
        "where vision_client_id = #{visionClientId,jdbcType=CHAR}",
          "and vision_activity_id = #{visionActivityId,jdbcType=CHAR}",
          "and vision_check_record_id = #{visionCheckRecordId,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(@Param("visionClientId") String visionClientId, @Param("visionActivityId") String visionActivityId, @Param("visionCheckRecordId") String visionCheckRecordId);

    @Insert({
        "insert into vision_activity_client_check_record (vision_client_id, vision_activity_id, ",
        "vision_check_record_id, created_time)",
        "values (#{visionClientId,jdbcType=CHAR}, #{visionActivityId,jdbcType=CHAR}, ",
        "#{visionCheckRecordId,jdbcType=CHAR}, #{createdTime,jdbcType=TIMESTAMP})"
    })
    int insert(VisionActivityClientCheckRecord record);

    @InsertProvider(type=VisionActivityClientCheckRecordSqlProvider.class, method="insertSelective")
    int insertSelective(VisionActivityClientCheckRecord record);

    @Select({
        "select",
        "vision_client_id, vision_activity_id, vision_check_record_id, created_time",
        "from vision_activity_client_check_record",
        "where vision_client_id = #{visionClientId,jdbcType=CHAR}",
          "and vision_activity_id = #{visionActivityId,jdbcType=CHAR}",
          "and vision_check_record_id = #{visionCheckRecordId,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="vision_client_id", property="visionClientId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="vision_activity_id", property="visionActivityId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="vision_check_record_id", property="visionCheckRecordId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP)
    })
    VisionActivityClientCheckRecord selectByPrimaryKey(@Param("visionClientId") String visionClientId, @Param("visionActivityId") String visionActivityId, @Param("visionCheckRecordId") String visionCheckRecordId);

    @UpdateProvider(type=VisionActivityClientCheckRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VisionActivityClientCheckRecord record);

    @Update({
        "update vision_activity_client_check_record",
        "set created_time = #{createdTime,jdbcType=TIMESTAMP}",
        "where vision_client_id = #{visionClientId,jdbcType=CHAR}",
          "and vision_activity_id = #{visionActivityId,jdbcType=CHAR}",
          "and vision_check_record_id = #{visionCheckRecordId,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(VisionActivityClientCheckRecord record);
}