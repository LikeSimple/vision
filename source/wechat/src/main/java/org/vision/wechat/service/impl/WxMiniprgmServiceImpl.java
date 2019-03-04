package org.vision.wechat.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vision.wechat.common.IDUtils;
import org.vision.wechat.common.RedisClient;
import org.vision.wechat.common.ResponseData;
import org.vision.wechat.common.SysResponseEnum;
import org.vision.wechat.model.WxUserInfoBO;
import org.vision.wechat.persistence.mapper.WxClientPOMapper;
import org.vision.wechat.persistence.model.WxClientPO;
import org.vision.wechat.persistence.model.WxClientPOExample;
import org.vision.wechat.sdk.WxMiniprgmSDK;
import org.vision.wechat.service.WxMiniprgmService;

import com.alibaba.fastjson.JSONObject;

@Service
public class WxMiniprgmServiceImpl implements WxMiniprgmService {

  @Autowired
  private WxMiniprgmSDK wxMiniprgmService;
  
  @Autowired
  private RedisClient redisClient;
  
  @Autowired
  private WxClientPOMapper wxClientPOMapper;
  
  @Override
  public ResponseData<String> byCode(String code) {
    
    ResponseData<String> responseData = new ResponseData<>();
    
    JSONObject jsonObject = wxMiniprgmService.getJscode2Session(code);
    if (jsonObject == null) {
      responseData.setCode(SysResponseEnum.WX_MINIGRGM_JSCODE_SESSION.getCode());
      responseData.setMessage(SysResponseEnum.WX_MINIGRGM_JSCODE_SESSION.getMessage());
      return responseData;
    }
    
    String wechatSessionId = UUID.randomUUID().toString().replaceAll("-", "");
    redisClient.set(wechatSessionId, jsonObject, 2678400L);
    
    responseData.setData(wechatSessionId);
    
    return responseData;
  }

  @Override
  public ResponseData<Object> saveUserInfo(String wechatSessionId, WxUserInfoBO wxUserInfo) {
    JSONObject jsonObject = (JSONObject) redisClient.get(wechatSessionId);
    String openid = jsonObject.getString("openid");
    
    wxUserInfo.getCountry();
    WxClientPO record = new WxClientPO();
    record.setOpenId(openid);
    record.setAvatarImage(wxUserInfo.getAvatarUrl());
    record.setWxNick(wxUserInfo.getNickName());
    record.setGender(wxUserInfo.getGender());
    record.setCity(wxUserInfo.getCity());
    record.setProvince(wxUserInfo.getProvince());
    record.setCounty(wxUserInfo.getCountry());
    
    WxClientPOExample example = new WxClientPOExample();
    example.createCriteria().andOpenIdEqualTo(openid);
    List<WxClientPO> list = wxClientPOMapper.selectByExample(example);
    
    if (list != null && !list.isEmpty()) {
      record.setId(list.get(0).getId());
      wxClientPOMapper.updateByPrimaryKeySelective(record);
    } else {
      record.setId(IDUtils.getId());
      wxClientPOMapper.insertSelective(record);
    }
    
    
    redisClient.set(wechatSessionId + "_user", record, 2678400L);
    return new ResponseData<>();
  }

}
