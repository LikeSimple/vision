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
import org.vision.service.admin.persistence.model.SystemAuthority;

import java.util.List;

@Mapper
public interface SystemAuthorityMapper {
    @Delete({
        "delete from system_authority",
        "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into system_authority (id, `name`, `desc`, ",
        "created_time, modified_time)",
        "values (#{id,jdbcType=CHAR}, #{name,jdbcType=VARCHAR}, #{desc,jdbcType=VARCHAR}, ",
        "#{createdTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    int insert(SystemAuthority record);

    @InsertProvider(type=SystemAuthoritySqlProvider.class, method="insertSelective")
    int insertSelective(SystemAuthority record);

    @Select({
        "select",
        "id, `name`, `desc`, created_time, modified_time",
        "from system_authority",
        "where id = #{id,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_time", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SystemAuthority selectByPrimaryKey(String id);

    @UpdateProvider(type=SystemAuthoritySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SystemAuthority record);

    @Update({
        "update system_authority",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(SystemAuthority record);

    @Select({
            "select distinct",
            "sa.id, sa.`name`, sa.`desc`, sa.created_time, sa.modified_time",
            "from system_authority sa",
            "inner join system_role_authority sra on sa.id = sra.authority_id",
            "inner join system_user_role sur on sra.role_id = sur.role_id",
            "where sur.system_user_id = #{systemUserId, jdbcType=CHAR}",
            "order by sa.name"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "desc", property = "desc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SystemAuthority> selectByUserId(String systemUserId);
}