package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.vision.service.admin.persistence.model.VisionActivityClientCheckReport;

@Mapper
public interface VisionActivityClientCheckReportMapper {
    @Delete({
            "delete from vision_activity_client_check_report",
            "where vision_client_id = #{visionClientId,jdbcType=CHAR}",
            "and vision_activity_id = #{visionActivityId,jdbcType=CHAR}",
            "and vision_check_report_id = #{visionCheckReportId,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(@Param("visionClientId") String visionClientId, @Param("visionActivityId") String visionActivityId, @Param("visionCheckReportId") String visionCheckReportId);

    @Insert({
            "insert into vision_activity_client_check_report (vision_client_id, vision_activity_id, ",
            "vision_check_report_id)",
            "values (#{visionClientId,jdbcType=CHAR}, #{visionActivityId,jdbcType=CHAR}, ",
            "#{visionCheckReportId,jdbcType=CHAR})"
    })
    int insert(VisionActivityClientCheckReport record);

    @InsertProvider(type = VisionActivityClientCheckReportSqlProvider.class, method = "insertSelective")
    int insertSelective(VisionActivityClientCheckReport record);
}