package org.vision.wechat.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.vision.wechat.common.ResponseData;
import org.vision.wechat.model.WxUserInfoBO;
import org.vision.wechat.persistence.model.WxClientPO;
import org.vision.wechat.service.WxMiniprgmService;

import io.swagger.annotations.Api;

@RequestMapping("/wx-miniprgm")
@RestController
@Api(value="微信小程序相关接口")
public class WxMiniprgmController{
  
  @Autowired
  private WxMiniprgmService WxMiniprgmService;
  
  @Autowired
  private HttpServletRequest httpServletRequest;
  
  @PostMapping(value = "/by-code/{code}")
  public ResponseData<String> byCode(@NotNull @PathVariable("code") String code) {
    
    return WxMiniprgmService.byCode(code);
  }
  
  @PostMapping(value = "/save-user")
  public ResponseData<Object> byCode(@RequestBody WxUserInfoBO wxUserInfo) {
    
    String wechatSessionId = httpServletRequest.getHeader(HttpConstants.WECHAT_SESSION_ID);
    return WxMiniprgmService.saveUserInfo(wechatSessionId, wxUserInfo);
  }

}
