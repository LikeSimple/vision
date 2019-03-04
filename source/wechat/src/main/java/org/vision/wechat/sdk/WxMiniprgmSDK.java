package org.vision.wechat.sdk;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.vision.wechat.common.AsyncHttpUtil;
import org.vision.wechat.common.RedisClient;
import org.vision.wechat.common.WxApiUrlConstants;
import org.vision.wechat.configuration.WxConfig;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WxMiniprgmSDK {

  @Autowired
  private WxConfig wxConfig;
  
  @Autowired
  private RedisClient redisClient;
  
  @Resource
  private RedisTemplate<Serializable, Object> redisTemplate;
  
  public String getAccessToken() {
    Object object = redisClient.get("accessToken");
    if (object != null && StringUtils.isNotBlank((String)object)) {
      log.info("缓存中存在accessToken");
      return (String)object;
    }
    log.info("缓存中不存在accessToken");
    
    String appid = wxConfig.getAppid();
    String secret = wxConfig.getSecret();
    
    String getAccessTokenUrl = WxApiUrlConstants.GET_ACCESS_TOKEN;
    getAccessTokenUrl.replace("$appid$", appid).replace("$secret$", secret);
    
    String resultString = AsyncHttpUtil.doGet(getAccessTokenUrl);
    if (StringUtils.isBlank(resultString)) {
      log.error("调用微信接口获取token失败，返回空");
      return null;
    }
    
    JSONObject jsonObject = JSONObject.parseObject(resultString);
    
    String accessToken = jsonObject.getString("access_token");
    if (StringUtils.isBlank(accessToken)) {
      log.error("调用微信接口获取token失败: " + resultString);
      return null;
    }
    
    redisClient.set("accessToken", accessToken, 7000L);
    log.info("重新获取accessToken成功");
    return accessToken;
    
  }
  
  public JSONObject getJscode2Session(String jscode) {
    
    String appid = wxConfig.getAppid();
    String secret = wxConfig.getSecret();
    
    String getJscode2SessionUrl = WxApiUrlConstants.GET_JSCODE2_SESSION;
    getJscode2SessionUrl = getJscode2SessionUrl.replace("$appid$", appid).replace("$secret$", secret).replace("$jscode$", jscode);
    
    String resultString = AsyncHttpUtil.doGet(getJscode2SessionUrl);
    if (StringUtils.isBlank(resultString)) {
      log.error("调用微信接口获取code2Session失败，返回空");
      return null;
    }
    
    JSONObject jsonObject = JSONObject.parseObject(resultString);
    
    String openid = jsonObject.getString("openid");
    if (StringUtils.isBlank(openid)) {
      log.error("调用微信接口获取openid失败: " + resultString);
      return null;
    }
    
    log.info("获取Jscode2Session成功");
    return jsonObject;
    
  }
  
  public static void main(String[] args) {
    
    String getJscode2SessionUrl = WxApiUrlConstants.GET_JSCODE2_SESSION;
    getJscode2SessionUrl = getJscode2SessionUrl.replace("$appid$", "wx499efff7ae899e05").replace("$secret$", "771e7ad9a65eea4b1e73238e25e7472c").replace("$jscode$", "023dSnbJ1AOWF20Ja5aJ1APgbJ1dSnbP");
    
    String resultString = AsyncHttpUtil.doGet(getJscode2SessionUrl);
    if (StringUtils.isBlank(resultString)) {
      log.error("调用微信接口获取code2Session失败，返回空");
    }
    
  }
  
  
}
