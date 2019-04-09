package org.vision.service.admin.service;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.controller.criteria.SysUserAddBO;
import org.vision.service.admin.controller.criteria.SysUserGetListBO;
import org.vision.service.admin.controller.criteria.SysUserUpdateBO;
import org.vision.service.admin.persistence.model.SystemUser;
import org.vision.service.admin.service.vo.SysUserListVO;
import org.vision.service.admin.service.vo.SysUserVO;
import org.vision.service.admin.service.vo.SystemUserProfileVO;

import com.github.pagehelper.PageInfo;

@Validated
public interface SystemUserService {

    UserDetails getUserByUsername(@NotNull String username);

    SystemUserProfileVO getProfileById(@NotNull String adminId);
    
    /**
     * 添加用户
     * @param bo
     * @return
     */
    ResponseData<Object> add(SysUserAddBO bo, SystemUser systemUser);
    
    /**
     * 分页查询用户数据
     * @param po
     * @return
     */
    ResponseData<PageInfo<SysUserListVO>> getList(SysUserGetListBO bo);
    
    /**
     * 通过id查询用户
     * @param sysUserId
     * @return
     */
    ResponseData<SysUserVO> findById(String sysUserId);
    
    /**
     * 更新用户数据
     * @param po
     * @return
     */
    ResponseData<Object> update(SysUserUpdateBO bo, SystemUser systemUser);
    
    /**
     * 禁用用户
     * @param po
     * @return
     */
    ResponseData<Object> disable(String sysUserId, SystemUser systemUser);
    
    /**
     * 启用用户
     * @param po
     * @return
     */
    ResponseData<Object> enable(String sysUserId, SystemUser systemUser);
    
    
    /**
     * 删除用户数据
     * @return
     */
    ResponseData<Object> delete(String sysUserId, SystemUser systemUser);
    
    /**
     * 新增角色
     * @return
     */
    ResponseData<Object> addRole(String sysUserId, String sysRoleId);
    
    /**
     * 删除角色
     * @return
     */
    ResponseData<Object> deleteRole(String sysUserId, String sysRoleId);
    

}
