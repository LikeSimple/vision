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
import org.vision.service.admin.persistence.model.SystemAudit;

@Mapper
public interface SystemAuditMapper {
    @Delete({
        "delete from system_audit",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into system_audit (id, user_id, ",
        "name, action, parameters, ",
        "execution_time)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=CHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{action,jdbcType=VARCHAR}, #{parameters,jdbcType=VARCHAR}, ",
        "#{executionTime,jdbcType=TIMESTAMP})"
    })
    int insert(SystemAudit record);

    @InsertProvider(type=SystemAuditSqlProvider.class, method="insertSelective")
    int insertSelective(SystemAudit record);

    @Select({
        "select",
        "id, user_id, name, action, parameters, execution_time",
        "from system_audit",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.CHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="action", property="action", jdbcType=JdbcType.VARCHAR),
        @Result(column="parameters", property="parameters", jdbcType=JdbcType.VARCHAR),
        @Result(column="execution_time", property="executionTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SystemAudit selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SystemAuditSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SystemAudit record);

    @Update({
        "update system_audit",
        "set user_id = #{userId,jdbcType=CHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "action = #{action,jdbcType=VARCHAR},",
          "parameters = #{parameters,jdbcType=VARCHAR},",
          "execution_time = #{executionTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SystemAudit record);
}