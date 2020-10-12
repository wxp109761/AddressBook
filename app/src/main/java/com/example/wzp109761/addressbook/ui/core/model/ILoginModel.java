package com.example.wzp109761.addressbook.ui.core.model;


import com.example.wzp109761.addressbook.bean.User;
import com.example.wzp109761.addressbook.interfac.VerifyAccountCallback;
import com.example.wzp109761.addressbook.net.callback.RxObserver;

import java.math.BigInteger;

/**
 *
 */

public interface ILoginModel {
    /**
     * 登录
     *
     * @param phone 用户名
     * @param password 密码
     */
    void login(BigInteger phone, String password, RxObserver<User> callback);


      /**
     * 注册
     *
     * @param username   用户名
     * @param password   密码
     */
    void register(String username, String password, RxObserver<String> callback);



    /**
     * 保存用户信息
     * @param user 用户
     */
    void saveUserInfo(User user);

    /**
     * 账号密码判空
     * @param username 用户名
     * @param password 密码
     * @return
     */
    boolean verifyAccount(String username, String password, VerifyAccountCallback callback);
}
