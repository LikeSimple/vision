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
import org.vision.service.admin.persistence.model.VisionSchool;

@Mapper
public interface VisionSchoolMapper {
    @Delete({
        "delete from vision_school",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into vision_school (id, name, province, ",
        "county, city, detail_address, ",
        "created_time, modified_time)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, ",
        "#{county,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, #{detailAddress,jdbcType=VARCHAR}, ",
        "#{createdTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(VisionSchool record);

    @InsertProvider(type=VisionSchoolSqlProvider.class, method="insertSelective")
    int insertSelective(VisionSchool record);

    @Select({
        "select",
        "id, name, province, county, city, detail_address, created_time, modified_time",
        "from vision_school",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="county", property="county", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail_address", property="detailAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_time", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    VisionSchool selectByPrimaryKey(String id);

    @UpdateProvider(type=VisionSchoolSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(VisionSchool record);

    @Update({
        "update vision_school",
        "set name = #{name,jdbcType=VARCHAR},",
          "province = #{province,jdbcType=VARCHAR},",
          "county = #{county,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "detail_address = #{detailAddress,jdbcType=VARCHAR},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(VisionSchool record);
}