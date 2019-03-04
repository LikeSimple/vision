package org.vision.wechat.common;

public enum SysResponseEnum {
    SUCCESS(0, "操作成功！"),
    FAILED(100, "操作失败！"),
    
    NOT_LOGIN(-1, "用户未登录！"),
    NOT_REGISTER(1001, "请先注册！"),
  
    WX_MINIGRGM_JSCODE_SESSION(1000, "获取用户身份失败！"),
    /**
     * >500 数据效验不通过
     */
    MODEL_FAILED_VALIDATION(500,"数据验证不通过"),

    OPERATE_FAILED_DATA_NOT_EXISTS(101, "查询数据不存在！"),
    OPERATE_FAILED_ADD_ERROR(102, "新增数据失败！"),
    OPERATE_FAILED_UPDATE_ERROR(103, "更新数据失败！"),
    OPERATE_FAILED_FILE_NOT_EXISTS(104, "操作失败，文件不存在！"),
    OPERATE_FAILED_FILE_UPLOAD_ERROR(105, "操作失败，文件上传错误！"),

    USER_LOGIN_FAILED_VALIDATECODE_NOT_EXISTS(106, "验证码不存在！"),
    USER_LOGIN_FAILED_VALIDATECODE_ERROR(107, "验证码错误！"),
    USER_LOGIN_SMS_CODE_OVERTIME(108, "短信验证码已过期！"),
    USER_LOGIN_FAILED_USERID_NOT_EXISTS(109, "登录失败，用户名不存在！"),
    USER_BIND_THIRD_LOGIN_ERROR(110, "登录失败，用户被禁用！"),
    USER_LOGIN_FAILED_USER_DISABLE(111, "登录失败，密码错误！"),
    USER_LOGIN_FAILED(120, "登录失败，用户名或密码错误！"),

    NETWORK_ERROR(114,"网络异常！"),
    SYSTEM_ERROR(115,"系统内部错误！"),
    
    VISION_CLIENT_NO_EXIST(200, "关联失败，客户不存在！"),
    VISION_CLIENT_RELATIONED(205, "关联失败，客户已被微信用户关联！"),

    UPLOAD_SUCCESS(116,"导入成功！"),
    UPLOAD_FAILED(117,"导入失败！");

    private int code;

    private String message;

    SysResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

} 

