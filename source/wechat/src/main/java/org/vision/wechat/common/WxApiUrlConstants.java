package org.vision.wechat.common;

public class WxApiUrlConstants {

  //生成二维码
  public static final String WX_QRCODE_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
  public static final String WX_QRCODE_SHOW_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

  // 获取access_token
  public static String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=$appid$&secret=$secret$";
  public static String GET_JSCODE2_SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=$appid$&secret=$secret$&js_code=$jscode$&grant_type=authorization_code";
      

  //获取用户基本信息（包括UnionID机制）
  public static String WX_GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";

  //通过code换取网页授权access_token
  public static String WX_GET_WEB_ACCESS_TOKEN_BY_CODE = "https://api.weixin.qq.com/sns/oauth2/component/access_token?";
  
  public static String WX_GET_SESSION_KEY_BY_CODE = "https://api.weixin.qq.com/sns/component/jscode2session?appid=$appid$&js_code=$jsCode$&grant_type=authorization_code&component_appid=$componentAppid$&component_access_token=$componentAccessToken$";

  //网页授权获取用户基本信息
  public static String WX_GET_USER_INFO_WEB = "https://api.weixin.qq.com/sns/userinfo?access_token=$at$&openid=$openid$&lang=zh_CN";

}
