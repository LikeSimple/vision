package org.vision.service.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.vision.service.admin.configuration.security.VisionUserDetail;
import org.vision.service.admin.persistence.mapper.*;
import org.vision.service.admin.persistence.model.SystemAdmin;
import org.vision.service.admin.persistence.model.SystemAuthority;
import org.vision.service.admin.persistence.model.SystemResource;
import org.vision.service.admin.persistence.model.SystemRole;
import org.vision.service.admin.util.ShortUUIDGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceApplicationTests {

    @Autowired
    private SystemAdminMapper systemAdminMapper;

    @Autowired
    private SystemAuthorityMapper systemAuthorityMapper;

    @Autowired
    private SystemAdminAuthorityMapper systemAdminAuthorityMapper;

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Autowired
    private SystemAdminRoleMapper systemAdminRoleMapper;


    @Test
    public void contextLoads() {

        String username = "admin";
        String password = "admin";

        SystemAdmin systemAdmin = systemAdminMapper.selectByUsername(username);



    }

}

