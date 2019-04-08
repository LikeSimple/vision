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
import org.vision.service.admin.persistence.model.SystemRoleExample;
import org.vision.service.admin.persistence.model.SystemRoleExample.Criteria;
import org.vision.service.admin.persistence.model.SystemUser;
import org.vision.service.admin.service.SysRoleService;
import org.vision.service.admin.service.vo.SystemAuthorityVO;
import org.vision.service.admin.util.ShortUUIDGenerator;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.github.pagehelper.util.StringUtil;

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
    
    String id = ShortUUIDGenerator.newID();
    SystemRole sysRolePO = new SystemRole();
    sysRolePO.setId(id);
    sysRolePO.setName(criteria.getRoleName());
    sysRolePO.setCreatedTime(nowDate);
    this.systemRoleMapper.insertSelective(sysRolePO);

    List<String> sysMenuIdList = criteria.getSystemAuthorityList();
    for (String sysMenuId : sysMenuIdList) {

      SystemRoleAuthority po = new SystemRoleAuthority();
      po.setRoleId(id);
      po.setAuthorityId(sysMenuId);
      po.setCreatedTime(nowDate);

      this.systemRoleAuthorityMapper.insertSelective(po);
    }


    return new ResponseData<>();
  }

  @Override
  public ResponseData<PageInfo<SystemRole>> getList(SysRoleGetListCriteria criteria) {
    
    String roleName = criteria.getRoleName();
    
    SystemRoleExample example = new SystemRoleExample();
    Criteria createCriteria  = example.createCriteria();
    if (StringUtil.isNotEmpty(roleName)) {
      createCriteria.andNameLike("%" + roleName + "%");
    }
    
    PageMethod.startPage(criteria.getPageNum(), criteria.getPageSize());
    List<SystemRole> list = this.systemRoleMapper.selectByExample(example);

    ResponseData<PageInfo<SystemRole>> responseData = new ResponseData<>();
    responseData.setData(new PageInfo<>(list));
    return responseData;
  }

  @Override
  public ResponseData<List<SystemAuthorityVO>> findAuthority(String sysRoleId) {
    List<SystemAuthorityVO> allList = systemAuthorityMapper.selectAll();
    
    List<SystemAuthority> list = systemAuthorityMapper.selectByRoleId(sysRoleId);

    dealAuthorityVO(allList, list);
    
    ResponseData<List<SystemAuthorityVO>> responseData = new ResponseData<>();
    responseData.setData(allList);
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

  private void dealAuthorityVO(List<SystemAuthorityVO> allList, List<SystemAuthority> list) {
    if (allList != null && list != null && !allList.isEmpty() && !list.isEmpty()) {
      for (SystemAuthorityVO vo : allList) {
        for (SystemAuthority po : list) {
          if (vo.getId().equals(po.getId())) {
            vo.setAuthorize(true);
          }
        }
      }
    }
  }
}
