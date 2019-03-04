package org.vision.wechat.service;

import org.vision.wechat.common.ResponseData;
import org.vision.wechat.model.WxUserInfoBO;

public interface WxMiniprgmService {
  
  ResponseData<String> byCode(String code);
  
  ResponseData<Object> saveUserInfo(String wechatSessionId, WxUserInfoBO wxUserInfo);
  
}
