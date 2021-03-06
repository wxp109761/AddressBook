package com.example.wzp109761.addressbook.common;

/**
 * Api接口地址
 */
public class UrlConstainer {

    public static final String baseUrl = "http://47.100.57.17:8090/";
    public static final String POSTCONTACTS= "phone/getAll";
    public static final String TEST= "stu_info?stu_name=test001";
    /**
     * 登录
     */
    public static final String LOGIN = "user/login";

    /**
     * 注册
     */
    public static final String ADDUSER = "user";

    /**
     * 头像上传
     */
    public static final String UPLOADAVATAR="file/upload";
    /**
     * 修改用户信息
     */
    public static final String MODIFYUSERINFO="user/modifyUser";
    /**
     * 发送验证码
     */
    public static final String SENDCHECKCODE="user/sendMessageCode";
}
