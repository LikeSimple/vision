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
import org.vision.service.admin.persistence.model.City;

@Mapper
public interface CityMapper {
    @Delete({
        "delete from city",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into city (id, county_id, ",
        "name, created_time, ",
        "modified_time)",
        "values (#{id,jdbcType=CHAR}, #{countyId,jdbcType=CHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{createdTime,jdbcType=TIMESTAMP}, ",
        "#{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(City record);

    @InsertProvider(type=CitySqlProvider.class, method="insertSelective")
    int insertSelective(City record);

    @Select({
        "select",
        "id, county_id, name, created_time, modified_time",
        "from city",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="county_id", property="countyId", jdbcType=JdbcType.CHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_time", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    City selectByPrimaryKey(String id);

    @UpdateProvider(type=CitySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(City record);

    @Update({
        "update city",
        "set county_id = #{countyId,jdbcType=CHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(City record);
}