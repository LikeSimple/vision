package org.vision.service.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.configuration.security.VisionUserDetail;
import org.vision.service.admin.controller.criteria.SysRoleAddCriteria;
import org.vision.service.admin.controller.criteria.SysRoleGetListCriteria;
import org.vision.service.admin.controller.criteria.SysRoleUpdateBO;
import org.vision.service.admin.persistence.mapper.SystemAuthorityMapper;
import org.vision.service.admin.persistence.model.SystemRole;
import org.vision.service.admin.service.SysRoleService;
import org.vision.service.admin.service.vo.SystemAuthorityVO;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/sys-role")
@RestController
@Api(value = "角色相关接口")
public class SysRoleController {

  @Autowired
  private SysRoleService sysRoleService;
  
  @Autowired
  private SystemAuthorityMapper systemAuthorityMapper;

  @ApiOperation(value = "获取权限列表")
  @RequestMapping(value = "/get-authority-list", method = RequestMethod.POST)
  public List<SystemAuthorityVO> getAuthorityList() {
    
    return this.systemAuthorityMapper.selectAll();


  }
  
  @ApiOperation(value = "新增角色")
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseData<Object> add(@RequestBody @Valid SysRoleAddCriteria bo) {
    
    VisionUserDetail userDetail = (VisionUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
    return this.sysRoleService.add(bo, userDetail.getSystemUser());


  }


  @ApiOperation(value = "删除角色")
  @RequestMapping(value = "/delete/{sysRoleId}", method = RequestMethod.POST)
  public ResponseData<Object> delete(@PathVariable("sysRoleId")String sysRoleId) {

    VisionUserDetail userDetail = (VisionUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return this.sysRoleService.delete(sysRoleId, userDetail.getSystemUser());

  }


  @ApiOperation(value = "查找角色")
  @RequestMapping(value = "/find/{sysRoleId}", method = RequestMethod.POST)
  public ResponseData<List<SystemAuthorityVO>> find(@PathVariable("sysRoleId")String sysRoleId) {
    
    return this.sysRoleService.findAuthority(sysRoleId);

  }

  @ApiOperation(value = "查询角色列表")
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public ResponseData<PageInfo<SystemRole>> getList(@RequestBody SysRoleGetListCriteria bo) {
    return this.sysRoleService.getList(bo);

  }


  @ApiOperation(value = "更新角色")
  @RequestMapping(value = "/update/{sysRoleId}", method = RequestMethod.POST)
  public ResponseData<Object> update(@PathVariable("sysRoleId")String sysRoleId, @RequestBody SysRoleUpdateBO bo) {

    VisionUserDetail userDetail = (VisionUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return this.sysRoleService.update(bo, userDetail.getSystemUser());

  }



}
