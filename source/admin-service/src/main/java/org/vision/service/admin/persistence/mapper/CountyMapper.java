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
import org.vision.service.admin.persistence.model.County;

@Mapper
public interface CountyMapper {
    @Delete({
        "delete from county",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into county (id, name, province_id, ",
        "created_time, modified_time)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{provinceId,jdbcType=CHAR}, ",
        "#{createdTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(County record);

    @InsertProvider(type=CountySqlProvider.class, method="insertSelective")
    int insertSelective(County record);

    @Select({
        "select",
        "id, name, province_id, created_time, modified_time",
        "from county",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="province_id", property="provinceId", jdbcType=JdbcType.CHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_time", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    County selectByPrimaryKey(String id);

    @UpdateProvider(type=CountySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(County record);

    @Update({
        "update county",
        "set name = #{name,jdbcType=VARCHAR},",
          "province_id = #{provinceId,jdbcType=CHAR},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(County record);
}