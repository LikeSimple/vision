package org.vision.service.admin.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.vision.service.admin.controller.criteria.SysUserGetListBO;
import org.vision.service.admin.persistence.model.SystemUser;
import org.vision.service.admin.persistence.model.SystemUserExample;
import org.vision.service.admin.service.vo.SysUserListVO;

@Mapper
public interface SystemUserMapper {

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    long countByExample(SystemUserExample example);
  
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int deleteByExample(SystemUserExample example);
  
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
    int insert(SystemUser record);
  
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int insertSelective(SystemUser record);
  
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    List<SystemUser> selectByExample(SystemUserExample example);
  
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    SystemUser selectByPrimaryKey(String id);
  
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int updateByExampleSelective(@Param("record") SystemUser record, @Param("example") SystemUserExample example);
  
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int updateByExample(@Param("record") SystemUser record, @Param("example") SystemUserExample example);
  
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int updateByPrimaryKeySelective(SystemUser record);
  
    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    int updateByPrimaryKey(SystemUser record);
    @Select({
            "select",
            "id, username, password, enabled, locked, account_expire, credential_expire, ",
            "created_time, modified_time",
            "from system_user",
            "where username = #{username,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.CHAR),
            @Result(column = "enabled", property = "enabled", jdbcType = JdbcType.BIT),
            @Result(column = "locked", property = "locked", jdbcType = JdbcType.BIT),
            @Result(column = "account_expire", property = "accountExpire", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "credential_expire", property = "credentialExpire", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    SystemUser selectByUsername(String username);
    
    @Select({
      "select",
      "id, username, password, enabled, locked, account_expire, credential_expire, ",
      "created_time, modified_time",
      "from system_user",
      "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
          @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
          @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
          @Result(column = "password", property = "password", jdbcType = JdbcType.CHAR),
          @Result(column = "enabled", property = "enabled", jdbcType = JdbcType.BIT),
          @Result(column = "locked", property = "locked", jdbcType = JdbcType.BIT),
          @Result(column = "account_expire", property = "accountExpire", jdbcType = JdbcType.TIMESTAMP),
          @Result(column = "credential_expire", property = "credentialExpire", jdbcType = JdbcType.TIMESTAMP),
          @Result(column = "created_time", property = "createdTime", jdbcType = JdbcType.TIMESTAMP),
          @Result(column = "modified_time", property = "modifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    SystemUser selectById(String id);
    
    List<SysUserListVO> getDetailList(SysUserGetListBO bo);
    
    
}