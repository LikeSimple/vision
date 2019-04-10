package org.vision.service.admin.service;

import java.util.List;

import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.controller.criteria.SysRoleAddCriteria;
import org.vision.service.admin.controller.criteria.SysRoleGetListCriteria;
import org.vision.service.admin.controller.criteria.SysRoleUpdateBO;
import org.vision.service.admin.persistence.model.SystemRole;
import org.vision.service.admin.persistence.model.SystemUser;
import org.vision.service.admin.service.vo.SysUserRoleVO;
import org.vision.service.admin.service.vo.SystemAuthorityVO;

import com.github.pagehelper.PageInfo;

/**
 * 角色管理
 * @author 董争光
 * 2018年12月26日下午3:31:14
 */
public interface SysRoleService {
  
  /**
   * 添加角色
   * @param bo
   * @return
   */
  ResponseData<Object> add(SysRoleAddCriteria criteria, SystemUser systemUser);
  
  /**
   * 查询角色列表
   * @param roleName
   * @param pageNum
   * @param pageSize
   * @return
   */
  ResponseData<PageInfo<SystemRole>> getList(SysRoleGetListCriteria criteria);
  
  /**
   * 查询角色权限
   * @param sysRoleId
   * @return
   */
  ResponseData<List<SystemAuthorityVO>> findAuthority(String sysRoleId);
  
  /**
   * 通过用户id查询角色列表
   * @param sysRoleId
   * @return
   */
  ResponseData<List<SystemRole>> getListByUserId(String sysUserId);
  

  
  /**
   * 更新角色
   * @param bo
   * @return
   */
  ResponseData<Object> update(SysRoleUpdateBO bo, SystemUser systemUser);
  
  /**
   * 删除角色
   * @param sysRoleId
   * @return
   */
  ResponseData<Object> delete(String sysRoleId, SystemUser systemUser);

}
