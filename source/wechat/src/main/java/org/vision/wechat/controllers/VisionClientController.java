package org.vision.wechat.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.vision.wechat.common.RedisClient;
import org.vision.wechat.common.ResponseData;
import org.vision.wechat.model.RelationBO;
import org.vision.wechat.persistence.model.VisionClientPO;
import org.vision.wechat.persistence.model.WxClientPO;
import org.vision.wechat.service.VisionClientService;

import io.swagger.annotations.Api;

@RequestMapping("/vision-client")
@RestController
@Api(value="睛锐客户")
public class VisionClientController{
  
  @Autowired
  private VisionClientService visionClientService;
  
  @Autowired
  private HttpServletRequest httpServletRequest;
  
  @Autowired
  private RedisClient redisClient;
  
  @PostMapping(value = "/list")
  @ResponseBody
  public ResponseData<List<VisionClientPO>> list() {
     
    String wechatSessionId = httpServletRequest.getHeader(HttpConstants.WECHAT_SESSION_ID);
    WxClientPO wxClientPO = (WxClientPO) redisClient.get(wechatSessionId + "_user");
    
    return visionClientService.list(wxClientPO.getId());
  }
  
  
  @PostMapping(value = "/relation")
  @ResponseBody
  public ResponseData<Object> relation(@RequestBody RelationBO bo) {
     
    String wechatSessionId = httpServletRequest.getHeader(HttpConstants.WECHAT_SESSION_ID);
    WxClientPO wxClientPO = (WxClientPO) redisClient.get(wechatSessionId + "_user");

    bo.setWxClientId(wxClientPO.getId());
    return visionClientService.relation(bo);
  }
  
  @PostMapping(value = "/select-client/{id}")
  @ResponseBody
  public ResponseData<Object> selectClient(@PathVariable("id") String id) {
     
    String wechatSessionId = httpServletRequest.getHeader(HttpConstants.WECHAT_SESSION_ID);
    
    redisClient.set(wechatSessionId + "_id", id, 2678400L);
    return new ResponseData<>();
  }

}
