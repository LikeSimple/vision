package org.vision.wechat.configuration.intercepors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.vision.wechat.common.RedisClient;
import org.vision.wechat.common.ResponseData;
import org.vision.wechat.common.SysResponseEnum;
import org.vision.wechat.controllers.HttpConstants;

import com.alibaba.fastjson.JSONObject;

@Component
public class LoginInterceptor implements HandlerInterceptor {

  @Autowired
  private RedisClient redisClient;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    String wechatSessionId = request.getHeader(HttpConstants.WECHAT_SESSION_ID);

    boolean exists = this.redisClient.exists(wechatSessionId);

    if (!exists) {
      ResponseData<Object> responseData = new ResponseData<>();
      responseData.setCode(SysResponseEnum.NOT_LOGIN.getCode());
      responseData.setMessage(SysResponseEnum.NOT_LOGIN.getMessage());
      response.getWriter().write(JSONObject.toJSONString(responseData));
      return false;
    }

    return exists;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      @Nullable ModelAndView modelAndView) throws Exception {}

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
      @Nullable Exception ex) throws Exception {}
}
