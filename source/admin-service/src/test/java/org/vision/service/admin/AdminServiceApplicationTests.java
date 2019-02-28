package org.vision.service.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.vision.service.admin.persistence.mapper.*;
import org.vision.service.admin.persistence.model.*;
import org.vision.service.admin.util.ShortUUIDGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceApplicationTests {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Autowired
    private SystemAuthorityMapper systemAuthorityMapper;

    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Autowired
    private SystemRoleAuthorityMapper systemRoleAuthorityMapper;

    @Test
    public void contextLoads() {

        String username = "admin";
        String password = "admin";

        SystemUser systemUser = systemUserMapper.selectByUsername(username);

        SystemRole systemRole = new SystemRole();
        systemRole.setId(ShortUUIDGenerator.newID());
        systemRole.setName("ROLE_ADMIN");
        systemRole.setDesc("");
        systemRoleMapper.insertSelective(systemRole);

        SystemUserRole systemUserRole = new SystemUserRole();
        systemUserRole.setSystemUserId(systemUser.getId());
        systemUserRole.setRoleId(systemRole.getId());
        systemUserRoleMapper.insertSelective(systemUserRole);

        SystemAuthority systemAuthority = new SystemAuthority();
        systemAuthority.setId(ShortUUIDGenerator.newID());
        systemAuthority.setName("AUTH_ADMIN");
        systemAuthority.setDesc("");
        systemAuthorityMapper.insertSelective(systemAuthority);

        SystemRoleAuthority systemRoleAuthority = new SystemRoleAuthority();
        systemRoleAuthority.setRoleId(systemRole.getId());
        systemRoleAuthority.setAuthorityId(systemAuthority.getId());
        systemRoleAuthorityMapper.insertSelective(systemRoleAuthority);
    }

}

