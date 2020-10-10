package com.example.wzp109761.addressbook.api;

import com.example.wzp109761.addressbook.bean.BaseBean;
import com.example.wzp109761.addressbook.bean.TestBean;
import com.example.wzp109761.addressbook.common.UrlConstainer;
import com.google.gson.JsonObject;
import io.reactivex.Observable;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


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

}
