package org.vision.service.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.controller.criteria.SysRoleAddCriteria;
import org.vision.service.admin.controller.criteria.SysRoleGetListCriteria;
import org.vision.service.admin.controller.criteria.SysRoleUpdateBO;
import org.vision.service.admin.persistence.mapper.SystemAuthorityMapper;
import org.vision.service.admin.persistence.mapper.SystemRoleAuthorityMapper;
import org.vision.service.admin.persistence.mapper.SystemRoleMapper;
import org.vision.service.admin.persistence.model.SystemAuthority;
import org.vision.service.admin.persistence.model.SystemRole;
import org.vision.service.admin.persistence.model.SystemRoleAuthority;
import org.vision.service.admin.persistence.model.SystemUser;
import org.vision.service.admin.service.SysRoleService;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;

@Service
public class SysRoleServiceImpl implements SysRoleService {

  @Autowired
  private SystemRoleMapper systemRoleMapper;

  @Autowired
  private SystemRoleAuthorityMapper systemRoleAuthorityMapper;
  
  @Autowired
  private SystemAuthorityMapper systemAuthorityMapper;
  

  @Override
  public ResponseData<Object> add(SysRoleAddCriteria criteria, SystemUser systemUser) {

    Date nowDate = new Date();
    SystemRole sysRolePO = new SystemRole();
    sysRolePO.setName(criteria.getRoleName());
    sysRolePO.setCreatedTime(nowDate);
    this.systemRoleMapper.insertSelective(sysRolePO);

    String sysRoleId = sysRolePO.getId();

    List<String> sysMenuIdList = criteria.getSystemResourceList();
    for (String sysMenuId : sysMenuIdList) {

      SystemRoleAuthority po = new SystemRoleAuthority();
      po.setRoleId(sysRoleId);
      po.setAuthorityId(sysMenuId);
      po.setCreatedTime(nowDate);

      this.systemRoleAuthorityMapper.insertSelective(po);
    }


    return new ResponseData<>();
  }

  @Override
  public ResponseData<PageInfo<SystemRole>> getList(SysRoleGetListCriteria criteria) {

    PageMethod.startPage(criteria.getPageNum(), criteria.getPageSize());
    List<SystemRole> list = this.systemRoleMapper.selectList();

    ResponseData<PageInfo<SystemRole>> responseData = new ResponseData<>();
    responseData.setData(new PageInfo<>(list));
    return responseData;
  }

  @Override
  public ResponseData<List<SystemAuthority>> findAuthority(String sysRoleId) {

    List<SystemAuthority> list = systemAuthorityMapper.selectByRoleId(sysRoleId);

    ResponseData<List<SystemAuthority>> responseData = new ResponseData<>();
    responseData.setData(list);
    return responseData;
  }

  @Override
  public ResponseData<Object> update(SysRoleUpdateBO bo, SystemUser systemUser) {
    String sysRoleId = bo.getId();

    Date nowDate = new Date();
    SystemRole sysRolePO = new SystemRole();
    sysRolePO.setId(bo.getId());
    sysRolePO.setName(bo.getName());
    sysRolePO.setModifiedTime(nowDate);
    this.systemRoleMapper.updateByPrimaryKeySelective(sysRolePO);

    List<String> list = bo.getSystemAuthorityList();
    if (CollectionUtils.isNotEmpty(list)) {
      this.systemRoleAuthorityMapper.deleteByRoleId(sysRoleId);
      
      for (String authorityId : list) {
        SystemRoleAuthority po = new SystemRoleAuthority();
        po.setRoleId(sysRoleId);
        po.setAuthorityId(authorityId);
        po.setCreatedTime(nowDate);
        this.systemRoleAuthorityMapper.insertSelective(po);
      }
    }

    return new ResponseData<>();
  }

  @Override
  public ResponseData<Object> delete(String sysRoleId, SystemUser systemUser) {


    this.systemRoleMapper.deleteByPrimaryKey(sysRoleId);
    return new ResponseData<>();
  }

  @Override
  public ResponseData<List<SystemRole>> getListByUserId(String sysUserId) {
    ResponseData<List<SystemRole>> responseData = new ResponseData<>();
    
    List<SystemRole> list = this.systemRoleMapper.selectListByUserId(sysUserId);
    
    responseData.setData(list);
    
    return responseData;
  }

}
