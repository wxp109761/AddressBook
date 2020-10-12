package com.example.wzp109761.addressbook.ui.core.model.impl;

import android.text.TextUtils;

import com.example.wzp109761.addressbook.R;
import com.example.wzp109761.addressbook.application.AppContext;
import com.example.wzp109761.addressbook.bean.User;
import com.example.wzp109761.addressbook.interfac.VerifyAccountCallback;
import com.example.wzp109761.addressbook.manager.UserInfoManager;
import com.example.wzp109761.addressbook.net.RxSchedulers;
import com.example.wzp109761.addressbook.net.callback.RxObserver;
import com.example.wzp109761.addressbook.ui.core.model.ILoginModel;
import com.google.gson.Gson;

import java.math.BigInteger;
import java.util.HashMap;

import okhttp3.RequestBody;


/**
 * Created by 康栋普 on 2018/2/1.
 */

public class LoginModel extends BaseModel implements ILoginModel {

    /**
     * 登录
     * @param phone 用户名
     * @param password 密码
     * @param callback
     */
    @Override
    public void login(BigInteger phone, String password, RxObserver<User> callback) {
        Gson gson=new Gson();
        HashMap<String,Object> paramsMap=new HashMap<>();
        paramsMap.put("phone",phone);
        paramsMap.put("password",password);
        String strEntity = gson.toJson(paramsMap);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);
        doRxRequest()
                .login(body)
                .compose(RxSchedulers.<User>io_main())
                .subscribe(callback);
    }

    /**
     * 注册
     * @param username   用户名
     * @param password   密码
     * @param callback
     */
    @Override
    public void register(final String username, final String password,  RxObserver<String> callback) {
//        doRxRequest()
//                .register(username, password, password)
//                .compose(RxSchedulers.<String>io_main())
//                .subscribe(callback);
    }



    /**
     * 保存用户信息
     * @param user
     */
    @Override
    public void saveUserInfo(User user) {
        //加密保存用户信息和密钥
        UserInfoManager.saveUserInfo(user);
        UserInfoManager.saveIsLogin(true);
    }



    /**
     * 账号密码判空
     * @param username
     * @param password
     * @param callback
     * @return
     */
    @Override
    public boolean verifyAccount(String username, String password, VerifyAccountCallback callback) {
        if (TextUtils.isEmpty(username)) {
            callback.onVerifyResult(AppContext.getContext().getString(R.string.username_not_empty));
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            callback.onVerifyResult(AppContext.getContext().getString(R.string.password_not_empty));
            return false;
        }
        return true;
    }

}
