package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.VisionSchoolClass;

@Mapper
public interface VisionSchoolClassMapper {
    @Delete({
        "delete from vision_school_class",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into vision_school_class (id, vision_school_id, ",
        "name, created_time, ",
        "modified_time)",
        "values (#{id,jdbcType=CHAR}, #{visionSchoolId,jdbcType=CHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{createdTime,jdbcType=TIMESTAMP}, ",
        "#{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(VisionSchoolClass record);

    @InsertProvider(type=VisionSchoolClassSqlProvider.class, method="insertSelective")
    int insertSelective(VisionSchoolClass record);

    @Select({
        "select",
        "id, vision_school_id, name, created_time, modified_time",
        "from vision_school_class",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="vision_school_id", property="visionSchoolId", jdbcType=JdbcType.CHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_time", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    VisionSchoolClass selectByPrimaryKey(String id);

    @UpdateProvider(type=VisionSchoolClassSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VisionSchoolClass record);

    @Update({
        "update vision_school_class",
        "set vision_school_id = #{visionSchoolId,jdbcType=CHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(VisionSchoolClass record);

    @Select({
            "select",
            "id, vision_school_id, name, created_time, modified_time",
            "from vision_school_class",
            "where name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "vision_school_id", property = "visionSchoolId", jdbcType = JdbcType.CHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    VisionSchoolClass selectByName(String name);
}