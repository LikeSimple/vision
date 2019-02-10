package org.vision.service.admin.persistence.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.vision.service.admin.persistence.model.SystemResourceAuthority;

@Mapper
public interface SystemResourceAuthorityMapper {
    @Insert({
            "insert into system_resource_authority (resource_id, authority_id, ",
            "created_time)",
            "values (#{resourceId,jdbcType=CHAR}, #{authorityId,jdbcType=CHAR}, ",
            "#{createdTime,jdbcType=TIMESTAMP})"
    })
    int insert(SystemResourceAuthority record);

    @InsertProvider(type = SystemResourceAuthoritySqlProvider.class, method = "insertSelective")
    int insertSelective(SystemResourceAuthority record);
}