package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.VisionSchoolClassMember;

@Mapper
public interface VisionSchoolClassMemberMapper {
    @Delete({
            "delete from vision_school_class_member",
            "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into vision_school_class_member (id, vision_class_id, ",
            "student_number, vision_client_id, ",
            "created_time, modified_time)",
            "values (#{id,jdbcType=CHAR}, #{visionClassId,jdbcType=CHAR}, ",
            "#{studentNumber,jdbcType=VARCHAR}, #{visionClientId,jdbcType=CHAR}, ",
            "#{createdTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(VisionSchoolClassMember record);

    @InsertProvider(type = VisionSchoolClassMemberSqlProvider.class, method = "insertSelective")
    int insertSelective(VisionSchoolClassMember record);

    @Select({
            "select",
            "id, vision_class_id, student_number, vision_client_id, created_time, modified_time",
            "from vision_school_class_member",
            "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "vision_class_id", property = "visionClassId", jdbcType = JdbcType.CHAR),
            @Result(column = "student_number", property = "studentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vision_client_id", property = "visionClientId", jdbcType = JdbcType.CHAR),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    VisionSchoolClassMember selectByPrimaryKey(String id);

    @UpdateProvider(type = VisionSchoolClassMemberSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VisionSchoolClassMember record);

    @Update({
            "update vision_school_class_member",
            "set vision_class_id = #{visionClassId,jdbcType=CHAR},",
            "student_number = #{studentNumber,jdbcType=VARCHAR},",
            "vision_client_id = #{visionClientId,jdbcType=CHAR},",
            "created_time = #{createdTime,jdbcType=TIMESTAMP},",
            "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(VisionSchoolClassMember record);

    @Select({
            "select",
            "id, vision_class_id, student_number, vision_client_id, created_time, modified_time",
            "from vision_school_class_member",
            "where vision_class_id = #{visionClassId,jdbcType=CHAR} " +
                    "and vision_client_id = #{visionClientId,jdbcType=CHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "vision_class_id", property = "visionClassId", jdbcType = JdbcType.CHAR),
            @Result(column = "student_number", property = "studentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vision_client_id", property = "visionClientId", jdbcType = JdbcType.CHAR),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    VisionSchoolClassMember selectByCombinedKeys(String visionClassId, String visionClientId);
}