package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.VisionActivity;

@Mapper
public interface VisionActivityMapper {
    @Delete({
            "delete from vision_activity",
            "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into vision_activity (id, name, address, ",
            "begin_date, end_date, ",
            "content, contact_man, ",
            "contact_phone_number, remark)",
            "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
            "#{beginDate,jdbcType=TIMESTAMP}, #{endDate,jdbcType=TIMESTAMP}, ",
            "#{content,jdbcType=VARCHAR}, #{contactMan,jdbcType=VARCHAR}, ",
            "#{contactPhoneNumber,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(VisionActivity record);

    @InsertProvider(type = VisionActivitySqlProvider.class, method = "insertSelective")
    int insertSelective(VisionActivity record);

    @Select({
            "select",
            "id, name, address, begin_date, end_date, content, contact_man, contact_phone_number, ",
            "remark",
            "from vision_activity",
            "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR),
            @Result(column = "begin_date", property = "beginDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "end_date", property = "endDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contact_man", property = "contactMan", jdbcType = JdbcType.VARCHAR),
            @Result(column = "contact_phone_number", property = "contactPhoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    VisionActivity selectByPrimaryKey(String id);

    @UpdateProvider(type = VisionActivitySqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VisionActivity record);

    @Update({
            "update vision_activity",
            "set name = #{name,jdbcType=VARCHAR},",
            "address = #{address,jdbcType=VARCHAR},",
            "begin_date = #{beginDate,jdbcType=TIMESTAMP},",
            "end_date = #{endDate,jdbcType=TIMESTAMP},",
            "content = #{content,jdbcType=VARCHAR},",
            "contact_man = #{contactMan,jdbcType=VARCHAR},",
            "contact_phone_number = #{contactPhoneNumber,jdbcType=VARCHAR},",
            "remark = #{remark,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(VisionActivity record);
}