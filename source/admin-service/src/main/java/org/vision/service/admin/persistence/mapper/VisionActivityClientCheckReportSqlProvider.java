package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.VisionActivityClientCheckReport;

public class VisionActivityClientCheckReportSqlProvider {

    public String insertSelective(VisionActivityClientCheckReport record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("vision_activity_client_check_report");

        if (record.getVisionClientId() != null) {
            sql.VALUES("vision_client_id", "#{visionClientId,jdbcType=CHAR}");
        }

        if (record.getVisionActivityId() != null) {
            sql.VALUES("vision_activity_id", "#{visionActivityId,jdbcType=CHAR}");
        }

        if (record.getVisionCheckReportId() != null) {
            sql.VALUES("vision_check_report_id", "#{visionCheckReportId,jdbcType=CHAR}");
        }

        return sql.toString();
    }
}