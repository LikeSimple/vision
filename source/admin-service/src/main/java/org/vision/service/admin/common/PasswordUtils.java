package org.vision.service.admin.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

  public static String bcrypt(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
    return passwordEncoder.encode(password);
  }


}
