package com.example.wzp109761.addressbook.api;

import com.example.wzp109761.addressbook.bean.BaseBean;
import com.example.wzp109761.addressbook.bean.TestBean;
import com.example.wzp109761.addressbook.bean.User;
import com.example.wzp109761.addressbook.common.UrlConstainer;
import com.google.gson.JsonObject;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


/**
 * 接口
 * Created by 位展朋 on.2020.2.24
 */
public interface ApiServer {
    @Headers("Content-Type:application/json;charset=utf-8")
    @POST(UrlConstainer.POSTCONTACTS)
    Observable<BaseBean<String>> postContacts(@Body JsonObject body);

    @Headers("Content-Type:application/json")
    @GET(UrlConstainer.TEST)
    Observable<BaseBean<TestBean>> getData();
    /**
     * 登录
     * @param body
     * @return
     */
    @Headers("Content-Type:application/json")
    @POST(UrlConstainer.LOGIN)
    Observable<BaseBean<User>> login(@Body RequestBody body);

    /**
     * 上传头像
     * @param file
     * @return
     */

    @POST(UrlConstainer.UPLOADAVATAR)
    @Multipart
    Observable<BaseBean<String>> uploadAvatar(@Part MultipartBody.Part file);
    /**
     * 登录
     * @param body
     * @return
     */
    @Headers("Content-Type:application/json")
    @POST(UrlConstainer.MODIFYUSERINFO)
    Observable<BaseBean<Object>> modifyUserInfo(@Body RequestBody body);
    /**
     * 发送验证码
     * @return
     */
    @Headers({"Content-Type:application/json"})
    @GET(UrlConstainer.SENDCHECKCODE)
    Observable<BaseBean<Object>> sendCheckCode(@Header("Cookie") String phone);
}
