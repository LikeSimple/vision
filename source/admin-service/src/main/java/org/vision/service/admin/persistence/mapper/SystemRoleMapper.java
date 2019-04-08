package org.vision.service.admin.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.persistence.model.SystemRole;
import org.vision.service.admin.persistence.model.SystemRoleExample;

@Mapper
public interface SystemRoleMapper {
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    long countByExample(SystemRoleExample example);
    
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int deleteByExample(SystemRoleExample example);
    
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int deleteByPrimaryKey(String id);
    
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int insert(SystemRole record);
    
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int insertSelective(SystemRole record);
    
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    List<SystemRole> selectByExample(SystemRoleExample example);
    
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    SystemRole selectByPrimaryKey(String id);
    
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int updateByExampleSelective(@Param("record") SystemRole record, @Param("example") SystemRoleExample example);
    
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int updateByExample(@Param("record") SystemRole record, @Param("example") SystemRoleExample example);
    
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int updateByPrimaryKeySelective(SystemRole record);
    
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int updateByPrimaryKey(SystemRole record);
    
    @Select({
      "select",
      "r.id, r.`name`, r.`description`, r.created_time, r.modified_time",
      "from system_role r",
      " join system_user_role u on r.id = u.role_id ",
      " where system_user_id = #{userId,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified_time", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SystemRole> selectListByUserId(String userId);
}