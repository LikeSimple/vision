package org.vision.wechat.controllers;

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
import org.vision.wechat.model.CheckRecordGetListBO;
import org.vision.wechat.model.CheckRecordListBO;
import org.vision.wechat.model.CheckRecordVO;
import org.vision.wechat.persistence.model.VisionCheckRecordPO;
import org.vision.wechat.persistence.model.WxClientPO;
import org.vision.wechat.service.CheckRecordPOService;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;

@RequestMapping("/check-record")
@RestController
@Api(value="检测记录")
public class CheckRecordController{
  
  @Autowired
  private CheckRecordPOService checkRecordPOService;
  
  @Autowired
  private HttpServletRequest httpServletRequest;
  
  @Autowired
  private RedisClient redisClient;
  
  @PostMapping(value = "/list")
  @ResponseBody
  public ResponseData<PageInfo<CheckRecordListBO>> list(@RequestBody CheckRecordGetListBO bo) {
    
    String wechatSessionId = httpServletRequest.getHeader(HttpConstants.WECHAT_SESSION_ID);
    WxClientPO wxClientPO = (WxClientPO) redisClient.get(wechatSessionId + "_user");
    System.out.println(wxClientPO);
    bo.setWxClientId(wxClientPO.getId());
    return checkRecordPOService.list(bo);
  }
  
  @PostMapping(value = "/find/{activityId}/{clientId}")
  @ResponseBody
  public ResponseData<CheckRecordVO> find(@PathVariable("activityId") String activityId, @PathVariable("clientId") String clientId) { 
    
    return checkRecordPOService.find(activityId, clientId);
  }
  

  


}
