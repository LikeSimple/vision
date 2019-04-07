package org.vision.service.admin.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.configuration.security.VisionUserDetail;
import org.vision.service.admin.controller.criteria.SysUserAddBO;
import org.vision.service.admin.controller.criteria.SysUserGetListBO;
import org.vision.service.admin.controller.criteria.SysUserUpdateBO;
import org.vision.service.admin.persistence.mapper.SystemAuthorityMapper;
import org.vision.service.admin.persistence.mapper.SystemUserMapper;
import org.vision.service.admin.persistence.mapper.SystemUserProfileMapper;
import org.vision.service.admin.persistence.mapper.SystemUserRoleMapper;
import org.vision.service.admin.persistence.model.SystemUser;
import org.vision.service.admin.persistence.model.SystemUserProfile;
import org.vision.service.admin.persistence.model.SystemUserRole;
import org.vision.service.admin.persistence.model.SystemAuthority;
import org.vision.service.admin.persistence.model.SystemRole;
import org.vision.service.admin.service.SysRoleService;
import org.vision.service.admin.service.SystemUserService;
import org.vision.service.admin.service.vo.SysUserVO;
import org.vision.service.admin.service.vo.SystemUserProfileVO;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

      String createUserId = systemUser.getId();

      Date nowDate = new Date();
      SystemUser record = new SystemUser();
      record.setUsername(bo.getName());
      record.setPassword(bo.getPassword());;
      record.setCreatedTime(nowDate);;
      this.systemUserMapper.insertSelective(record);

      String sysUserId = record.getId();

      List<String> sysRoleIdList = bo.getSysRoleIdList();
      for (String sysRoleId : sysRoleIdList) {

        SystemUserRole po = new SystemUserRole();
        po.setSystemUserId(sysUserId);
        po.setRoleId(sysRoleId);
        po.setCreatedTime(nowDate);

        this.systemUserRoleMapper.insertSelective(po);
      }

      return new ResponseData<>();
    }

    @Override
    public ResponseData<PageInfo<SystemUser>> getList(SysUserGetListBO bo) {
      String name = bo.getName();
      String sysUserCode = bo.getSysUserCode();
      String phone = bo.getPhone();
      
      PageHelper.startPage(bo.getPageNum(), bo.getPageSize());
      List<SystemUser> sysUserPOList = systemUserMapper.selectList();
      
      ResponseData<PageInfo<SystemUser>> responseData = new ResponseData<>();
      responseData.setData(new PageInfo<>(sysUserPOList));
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

      Date nowDate = new Date();
      SystemUser record = new SystemUser();
      record.setUsername(bo.getName());
      record.setPassword(bo.getPassword());;
      record.setCreatedTime(nowDate);;
      this.systemUserMapper.updateByPrimaryKeySelective(record);

      List<String> sysRoleIdList = bo.getSysRoleIdList();
      if (CollectionUtils.isNotEmpty(sysRoleIdList)) {
        
        this.systemUserRoleMapper.deleteByUserId(sysUserId);
        
        for (String sysRoleId : sysRoleIdList) {
          SystemUserRole po = new SystemUserRole();
          po.setSystemUserId(sysUserId);
          po.setRoleId(sysRoleId);
          po.setCreatedTime(nowDate);
          this.systemUserRoleMapper.insertSelective(po);
        }
      }
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

}
