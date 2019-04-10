package org.vision.service.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.configuration.security.VisionUserDetail;
import org.vision.service.admin.controller.criteria.SysUserAddBO;
import org.vision.service.admin.controller.criteria.SysUserGetListBO;
import org.vision.service.admin.controller.criteria.SysUserUpdateBO;
import org.vision.service.admin.persistence.model.SystemUser;
import org.vision.service.admin.service.SystemUserService;
import org.vision.service.admin.service.vo.SysUserListVO;
import org.vision.service.admin.service.vo.SysUserRoleVO;
import org.vision.service.admin.service.vo.SysUserVO;
import org.vision.service.admin.service.vo.SystemUserProfileVO;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
public class SystemUserController {

    private SystemUserService systemUserService;

    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @GetMapping("/profile")
    public ResponseData<SystemUserProfileVO> getCurrentAdminProfile() {
        VisionUserDetail userDetail = (VisionUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseData<>(systemUserService.getProfileById(userDetail.getId()));
    }
    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData<Object> add(@RequestBody @Valid SysUserAddBO bo) {
      
      VisionUserDetail userDetail = (VisionUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return this.systemUserService.add(bo, userDetail.getSystemUser());

    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete/{sysUserId}", method = RequestMethod.POST)
    public ResponseData<Object> delete(@PathVariable("sysUserId") String sysUserId) {
      VisionUserDetail userDetail = (VisionUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return this.systemUserService.delete(sysUserId, userDetail.getSystemUser());

    }

    @ApiOperation(value = "查找用户")
    @RequestMapping(value = "/find-byId/{sysUserId}", method = RequestMethod.POST)
    public ResponseData<SysUserVO> findById(@PathVariable("sysUserId") String sysUserId) {

      return this.systemUserService.findById(sysUserId);

    }
    
    @ApiOperation(value = "查找用户角色列表")
    @RequestMapping(value = "/find-role/{sysUserId}", method = RequestMethod.POST)
    public ResponseData<List<SysUserRoleVO>> findRole(@PathVariable("sysUserId") String sysUserId) {

      return this.systemUserService.findRole(sysUserId);

    }
    

    @ApiOperation(value = "获得用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseData<PageInfo<SysUserListVO>> getList(@RequestBody SysUserGetListBO bo) {
      return this.systemUserService.getList(bo);


    }

    @ApiOperation(value = "修改用户")
    @RequestMapping(value = "/update/{userId}", method = RequestMethod.POST)
    public ResponseData<Object> update(@PathVariable("userId")String userId, @RequestBody SysUserUpdateBO bo) {
      bo.setUserId(userId);
      VisionUserDetail userDetail = (VisionUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return this.systemUserService.update(bo, userDetail.getSystemUser());

    }
    
    @ApiOperation(value = "重置密码")
    @RequestMapping(value = "/reset-password/{userId}", method = RequestMethod.POST)
    public ResponseData<Object> resetPassword(@PathVariable("userId")String userId) {
      return this.systemUserService.resetPassword(userId);

    }
    
    @ApiOperation(value = "禁用用户")
    @RequestMapping(value = "/disable/{sysUserId}", method = RequestMethod.POST)
    public ResponseData<Object> disable(@PathVariable("sysUserId")String sysUserId) {
      VisionUserDetail userDetail = (VisionUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return this.systemUserService.disable(sysUserId, userDetail.getSystemUser());

    }
    
    @ApiOperation(value = "启用用户")
    @RequestMapping(value = "/enable/{sysUserId}", method = RequestMethod.POST)
    public ResponseData<Object> enable(@PathVariable("sysUserId")String sysUserId) {
      VisionUserDetail userDetail = (VisionUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return this.systemUserService.enable(sysUserId, userDetail.getSystemUser());

    }
    
    @ApiOperation(value = "增加角色")
    @RequestMapping(value = "/add-role/{sysUserId}/{sysRoleId}", method = RequestMethod.POST)
    public ResponseData<Object> addRole(@PathVariable("sysUserId")String sysUserId,
        @PathVariable("sysRoleId")String sysRoleId) {
      return this.systemUserService.addRole(sysUserId, sysRoleId);

    }
    @ApiOperation(value = "删除角色")
    @RequestMapping(value = "/delete-role/{sysUserId}/{sysRoleId}", method = RequestMethod.POST)
    public ResponseData<Object> updateRole(@PathVariable("sysUserId")String sysUserId,
        @PathVariable("sysRoleId")String sysRoleId) {
      return this.systemUserService.deleteRole(sysUserId, sysRoleId);
    }
}
