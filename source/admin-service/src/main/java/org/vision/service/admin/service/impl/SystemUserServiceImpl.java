package org.vision.service.admin.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.xml.crypto.Data;

import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.weaver.ResolvableTypeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.vision.service.admin.common.PasswordUtils;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.common.SysResponseEnum;
import org.vision.service.admin.configuration.security.VisionUserDetail;
import org.vision.service.admin.controller.criteria.SysUserAddBO;
import org.vision.service.admin.controller.criteria.SysUserGetListBO;
import org.vision.service.admin.controller.criteria.SysUserUpdateBO;
import org.vision.service.admin.persistence.mapper.SystemAuthorityMapper;
import org.vision.service.admin.persistence.mapper.SystemUserMapper;
import org.vision.service.admin.persistence.mapper.SystemUserProfileMapper;
import org.vision.service.admin.persistence.mapper.SystemUserRoleMapper;
import org.vision.service.admin.persistence.model.SystemAuthority;
import org.vision.service.admin.persistence.model.SystemRole;
import org.vision.service.admin.persistence.model.SystemUser;
import org.vision.service.admin.persistence.model.SystemUserExample;
import org.vision.service.admin.persistence.model.SystemUserExample.Criteria;
import org.vision.service.admin.persistence.model.SystemUserProfile;
import org.vision.service.admin.persistence.model.SystemUserRole;
import org.vision.service.admin.service.SysRoleService;
import org.vision.service.admin.service.SystemUserService;
import org.vision.service.admin.service.vo.SysUserListVO;
import org.vision.service.admin.service.vo.SysUserVO;
import org.vision.service.admin.service.vo.SystemUserProfileVO;
import org.vision.service.admin.util.ShortUUIDGenerator;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

@Service("SystemUserService")
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SysRoleService sysRoleService;
    
    private SystemUserMapper systemUserMapper;
    
    private SystemUserRoleMapper systemUserRoleMapper;

    private SystemUserProfileMapper systemUserProfileMapper;

    private SystemAuthorityMapper systemAuthorityMapper;


    public SystemUserServiceImpl(SystemUserMapper systemUserMapper, SystemUserProfileMapper systemUserProfileMapper, SystemAuthorityMapper systemAuthorityMapper,
        SystemUserRoleMapper systemUserRoleMapper) {
        this.systemUserMapper = systemUserMapper;
        this.systemUserProfileMapper = systemUserProfileMapper;
        this.systemAuthorityMapper = systemAuthorityMapper;
        this.systemUserRoleMapper = systemUserRoleMapper;
    }

    @Override
    public UserDetails getUserByUsername(@NotNull String username) {
        SystemUser systemUser = systemUserMapper.selectByUsername(username);
        Assert.notNull(systemUser, String.format("user %s not found.", username));

        List<SystemAuthority> systemAuthorities = systemAuthorityMapper.selectByUserId(systemUser.getId());
        return new VisionUserDetail(systemUser, systemAuthorities);
    }

    @Override
    public SystemUserProfileVO getProfileById(@NotNull String systemUserId) {

        SystemUserProfile profile = systemUserProfileMapper.selectByPrimaryKey(systemUserId);
        Assert.notNull(profile, "用户信息丢失，请联系管理员");

        List<SystemAuthority> systemAuthorities = systemAuthorityMapper.selectByUserId(systemUserId);

        return new SystemUserProfileVOImpl(profile, systemAuthorities);
    }


    private class SystemUserProfileVOImpl implements SystemUserProfileVO, Serializable {

        private static final long serialVersionUID = -5416487459974681138L;

        private SystemUserProfile systemUserProfile;

        private List<SystemAuthority> systemAuthorities;

        public SystemUserProfileVOImpl(SystemUserProfile systemUserProfile, List<SystemAuthority> systemAuthorities) {
            this.systemUserProfile = systemUserProfile;
            this.systemAuthorities = systemAuthorities;
        }

        @Override
        public String getId() {
            return systemUserProfile.getId();
        }

        @Override
        public String getRealName() {
            return systemUserProfile.getName();
        }

        @Override
        public String getAvatarImage() {
            return systemUserProfile.getAvatar();
        }

        @Override
        public Byte getGender() {
            return systemUserProfile.getGender();
        }

        @Override
        public List<String> getAuthorities() {
            return systemAuthorities.parallelStream().map(SystemAuthority::getName).collect(Collectors.toList());
        }

    }

    @Override
    public ResponseData<Object> add(SysUserAddBO bo, SystemUser systemUser) {
      
      String loginName = bo.getLoginName();
      
      SystemUser selectByUsername = this.systemUserMapper.selectByUsername(loginName);
      if (selectByUsername != null) {
        return new ResponseData<>(SysResponseEnum.USER_USERNAME_EXIST.getCode(), SysResponseEnum.USER_USERNAME_EXIST.getMessage());
      }
      
      String passwordEncoder = PasswordUtils.bcrypt(bo.getPassword());
      String id = ShortUUIDGenerator.newID();
      Date nowDate = new Date();
      SystemUser record = new SystemUser();
      record.setId(id);
      record.setUsername(loginName);
      record.setPassword(passwordEncoder);;
      record.setCreatedTime(nowDate);;
      this.systemUserMapper.insertSelective(record);

      SystemUserProfile profile = new SystemUserProfile();
      profile.setId(id);
      profile.setName(bo.getName());
      profile.setAvatar(bo.getAvatar());
      profile.setGender(bo.getGender());
      this.systemUserProfileMapper.insertSelective(profile);

      return new ResponseData<>();
    }

    @Override
    public ResponseData<PageInfo<SysUserListVO>> getList(SysUserGetListBO bo) {
      
      PageHelper.startPage(bo.getPageNum(), bo.getPageSize());

      List<SysUserListVO> detailList = systemUserMapper.getDetailList(bo);
      
      ResponseData<PageInfo<SysUserListVO>> responseData = new ResponseData<>();
      responseData.setData(new PageInfo<>(detailList));
      return responseData;
    }

    @Override
    public ResponseData<SysUserVO> findById(String sysUserId) {
      // TODO Auto-generated method stub
      ResponseData<SysUserVO> responseData = new ResponseData<>();
      SysUserVO sysUserVO = new SysUserVO();
      
      SystemUser systemUser = systemUserMapper.selectById(sysUserId);
      sysUserVO.setSystemUser(systemUser);

      List<SystemRole> roleList = sysRoleService.getListByUserId(sysUserId).getData();
      sysUserVO.setRoleList(roleList);
      
      responseData.setData(sysUserVO);
      return responseData;
    }

    @Override
    public ResponseData<Object> update(SysUserUpdateBO bo, SystemUser systemUser) {
      
      String sysUserId = bo.getSysUserId();
      String name = bo.getName();
      
      SystemUser selectByUsername = this.systemUserMapper.selectByUsername(name);
      if (selectByUsername != null && !sysUserId.equals(selectByUsername.getId())) {
        return new ResponseData<>(SysResponseEnum.USER_USERNAME_EXIST.getCode(), SysResponseEnum.USER_USERNAME_EXIST.getMessage());
      }
      
      Date nowDate = new Date();
      SystemUser record = new SystemUser();
      record.setId(sysUserId);
      record.setUsername(bo.getName());
      record.setCreatedTime(nowDate);;
      this.systemUserMapper.updateByPrimaryKeySelective(record);

      return new ResponseData<>();
    }

    @Override
    public ResponseData<Object> delete(String sysUserId, SystemUser systemUser) {

      systemUserMapper.deleteByPrimaryKey(sysUserId);
      return new ResponseData<>();
    }

    @Override
    public ResponseData<Object> disable(String sysUserId, SystemUser systemUser) {

      SystemUser record = new SystemUser();
      record.setId(sysUserId);
      record.setEnabled(false);
      
      systemUserMapper.updateByPrimaryKeySelective(record);
      
      return new ResponseData<>();
    }

    @Override
    public ResponseData<Object> enable(String sysUserId, SystemUser systemUser) {

      SystemUser record = new SystemUser();
      record.setId(sysUserId);
      record.setEnabled(true);
      
      systemUserMapper.updateByPrimaryKeySelective(record);
      
      return new ResponseData<>();
    }

    @Override
    public ResponseData<Object> addRole(String sysUserId, String sysRoleId) {

      systemUserRoleMapper.deleteByPrimaryKey(sysUserId, sysRoleId);
      return new ResponseData<>();
    }

    @Override
    public ResponseData<Object> deleteRole(String sysUserId, String sysRoleId) {
      SystemUserRole po = new SystemUserRole();
      po.setSystemUserId(sysUserId);
      po.setRoleId(sysRoleId);
      po.setCreatedTime(new Date());
      systemUserRoleMapper.insertSelective(po);
      return new ResponseData<>();
    }

}
