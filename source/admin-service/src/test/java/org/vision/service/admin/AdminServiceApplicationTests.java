package org.vision.service.admin;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.vision.service.admin.util.ShortUUIDGenerator;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AdminServiceApplicationTests {

    @Test
    public void contextLoads() {

        String username = "admin";
        String password = "admin";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);

        String cryptPassword = passwordEncoder.encode(password);

        System.out.println(String.format("ADMIN ID: %s User: %s  Password: %s Length: %d", ShortUUIDGenerator.newID(), username, cryptPassword, cryptPassword.length()));

        System.out.println(String.format("AUTHORITY ID: %s Name: %s ", ShortUUIDGenerator.newID(), "ROLE_ADMIN"));

    }

}

