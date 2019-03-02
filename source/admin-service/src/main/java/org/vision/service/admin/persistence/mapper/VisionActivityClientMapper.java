package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.VisionActivityClient;
import org.vision.service.admin.persistence.model.VisionActivityClientView;

import java.util.List;

@Mapper
public interface VisionActivityClientMapper {
    @Delete({
        "delete from vision_activity_client",
        "where vision_activity_id = #{visionActivityId,jdbcType=CHAR}",
          "and vision_client_id = #{visionClientId,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(@Param("visionActivityId") String visionActivityId, @Param("visionClientId") String visionClientId);

    @Insert({
        "insert into vision_activity_client (vision_activity_id, vision_client_id, ",
            "vision_member_id, enabled, ",
            "created_time)",
        "values (#{visionActivityId,jdbcType=CHAR}, #{visionClientId,jdbcType=CHAR}, ",
            "#{visionMemberId,jdbcType=CHAR}, #{enabled,jdbcType=BIT}, ",
            "#{createdTime,jdbcType=TIMESTAMP})"
    })
    int insert(VisionActivityClient record);

    @InsertProvider(type=VisionActivityClientSqlProvider.class, method="insertSelective")
    int insertSelective(VisionActivityClient record);

    @Select({
        "select",
            "vision_activity_id, vision_client_id, vision_member_id, enabled, created_time",
        "from vision_activity_client",
        "where vision_activity_id = #{visionActivityId,jdbcType=CHAR}",
          "and vision_client_id = #{visionClientId,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="vision_activity_id", property="visionActivityId", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="vision_client_id", property="visionClientId", jdbcType=JdbcType.CHAR, id=true),
            @Result(column = "vision_member_id", property = "visionMemberId", jdbcType = JdbcType.CHAR),
        @Result(column="enabled", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP)
    })
    VisionActivityClient selectByPrimaryKey(@Param("visionActivityId") String visionActivityId, @Param("visionClientId") String visionClientId);

    @UpdateProvider(type=VisionActivityClientSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VisionActivityClient record);

    @Update({
        "update vision_activity_client",
            "set vision_member_id = #{visionMemberId,jdbcType=CHAR},",
            "enabled = #{enabled,jdbcType=BIT},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP}",
        "where vision_activity_id = #{visionActivityId,jdbcType=CHAR}",
          "and vision_client_id = #{visionClientId,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(VisionActivityClient record);

    VisionActivityClientView selectByClientId(String id);

    List<VisionActivityClientView> selectByActivityId(String id);
}