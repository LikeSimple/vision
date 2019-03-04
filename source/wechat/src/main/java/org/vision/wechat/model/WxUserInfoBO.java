package org.vision.wechat.model;

import lombok.Data;

@Data
public class WxUserInfoBO {

  private String nickName;
  private String avatarUrl;
  private String gender; // 性别 0：未知、1：男、2：女
  private String province;
  private String city;
  private String country;
}
