package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.vision.service.admin.persistence.model.VisionCheckReport;

public class VisionCheckReportSqlProvider {

    public String insertSelective(VisionCheckReport record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("vision_check_report");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=CHAR}");
        }

        if (record.getEyeType() != null) {
            sql.VALUES("eye_type", "#{eyeType,jdbcType=CHAR}");
        }

        if (record.getCheckDate() != null) {
            sql.VALUES("check_date", "#{checkDate,jdbcType=TIMESTAMP}");
        }

        if (record.getDataType() != null) {
            sql.VALUES("data_type", "#{dataType,jdbcType=BIT}");
        }

        if (record.getPictureFile() != null) {
            sql.VALUES("picture_file", "#{pictureFile,jdbcType=VARCHAR}");
        }

        if (record.getPupil() != null) {
            sql.VALUES("pupil", "#{pupil,jdbcType=DECIMAL}");
        }

        if (record.getSe1() != null) {
            sql.VALUES("se1", "#{se1,jdbcType=DECIMAL}");
        }

        if (record.getDs1() != null) {
            sql.VALUES("ds1", "#{ds1,jdbcType=DECIMAL}");
        }

        if (record.getDc1() != null) {
            sql.VALUES("dc1", "#{dc1,jdbcType=DECIMAL}");
        }

        if (record.getAxis1() != null) {
            sql.VALUES("axis1", "#{axis1,jdbcType=INTEGER}");
        }

        if (record.getSe2() != null) {
            sql.VALUES("se2", "#{se2,jdbcType=DECIMAL}");
        }

        if (record.getDs2() != null) {
            sql.VALUES("ds2", "#{ds2,jdbcType=DECIMAL}");
        }

        if (record.getDc2() != null) {
            sql.VALUES("dc2", "#{dc2,jdbcType=DECIMAL}");
        }

        if (record.getAxis2() != null) {
            sql.VALUES("axis2", "#{axis2,jdbcType=INTEGER}");
        }

        if (record.getPd() != null) {
            sql.VALUES("pd", "#{pd,jdbcType=INTEGER}");
        }

        if (record.getMmHg() != null) {
            sql.VALUES("mm_hg", "#{mmHg,jdbcType=DECIMAL}");
        }

        if (record.getGazeH() != null) {
            sql.VALUES("gaze_h", "#{gazeH,jdbcType=INTEGER}");
        }

        if (record.getGazeV() != null) {
            sql.VALUES("gaze_v", "#{gazeV,jdbcType=INTEGER}");
        }

        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }

        if (record.getEnabled() != null) {
            sql.VALUES("enabled", "#{enabled,jdbcType=BIT}");
        }

        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getModifiedTime() != null) {
            sql.VALUES("modified_time", "#{modifiedTime,jdbcType=TIMESTAMP}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(VisionCheckReport record) {
        SQL sql = new SQL();
        sql.UPDATE("vision_check_report");

        if (record.getEyeType() != null) {
            sql.SET("eye_type = #{eyeType,jdbcType=CHAR}");
        }

        if (record.getCheckDate() != null) {
            sql.SET("check_date = #{checkDate,jdbcType=TIMESTAMP}");
        }

        if (record.getDataType() != null) {
            sql.SET("data_type = #{dataType,jdbcType=BIT}");
        }

        if (record.getPictureFile() != null) {
            sql.SET("picture_file = #{pictureFile,jdbcType=VARCHAR}");
        }

        if (record.getPupil() != null) {
            sql.SET("pupil = #{pupil,jdbcType=DECIMAL}");
        }

        if (record.getSe1() != null) {
            sql.SET("se1 = #{se1,jdbcType=DECIMAL}");
        }

        if (record.getDs1() != null) {
            sql.SET("ds1 = #{ds1,jdbcType=DECIMAL}");
        }

        if (record.getDc1() != null) {
            sql.SET("dc1 = #{dc1,jdbcType=DECIMAL}");
        }

        if (record.getAxis1() != null) {
            sql.SET("axis1 = #{axis1,jdbcType=INTEGER}");
        }

        if (record.getSe2() != null) {
            sql.SET("se2 = #{se2,jdbcType=DECIMAL}");
        }

        if (record.getDs2() != null) {
            sql.SET("ds2 = #{ds2,jdbcType=DECIMAL}");
        }

        if (record.getDc2() != null) {
            sql.SET("dc2 = #{dc2,jdbcType=DECIMAL}");
        }

        if (record.getAxis2() != null) {
            sql.SET("axis2 = #{axis2,jdbcType=INTEGER}");
        }

        if (record.getPd() != null) {
            sql.SET("pd = #{pd,jdbcType=INTEGER}");
        }

        if (record.getMmHg() != null) {
            sql.SET("mm_hg = #{mmHg,jdbcType=DECIMAL}");
        }

        if (record.getGazeH() != null) {
            sql.SET("gaze_h = #{gazeH,jdbcType=INTEGER}");
        }

        if (record.getGazeV() != null) {
            sql.SET("gaze_v = #{gazeV,jdbcType=INTEGER}");
        }

        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }

        if (record.getEnabled() != null) {
            sql.SET("enabled = #{enabled,jdbcType=BIT}");
        }

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getModifiedTime() != null) {
            sql.SET("modified_time = #{modifiedTime,jdbcType=TIMESTAMP}");
        }

        sql.WHERE("id = #{id,jdbcType=CHAR}");

        return sql.toString();
    }
}