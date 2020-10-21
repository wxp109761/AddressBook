package com.example.wzp109761.addressbook.application;

import android.content.Context;

import com.example.wzp109761.addressbook.common.UrlConstainer;
import com.example.wzp109761.addressbook.net.RxRetrofit;
import com.example.wzp109761.addressbook.utils.PreUtils;


/**
 * author: 康栋普
 * date: 2018/3/13
 */

public class AppConfig {

    static void init(Context context){
        //初始化网络框架
        RxRetrofit.getInstance().initRxRetrofit(context, UrlConstainer.baseUrl);
        //初始化缓存
        new PreUtils(context);

    }

}
