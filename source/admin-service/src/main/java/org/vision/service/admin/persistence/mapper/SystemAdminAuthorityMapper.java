package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.SystemAuthority;

import java.util.List;

public interface SystemAdminAuthorityMapper {

    @Select({
            "select distinct",
            "sa.id, sa.`name`, sa.`desc`, sa.created_time, sa.modified_time",
            "from system_authority sa",
            "inner join system_role_authority sra on sa.id = sra.authority_id",
            "inner join system_admin_role sar on sra.role_id = sar.role_id",
            "where sar.admin_id = #{adminId, jdbcType=CHAR}",
            "order by sa.name"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "desc", property = "desc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SystemAuthority> selectByAdminId(String adminId);
}
