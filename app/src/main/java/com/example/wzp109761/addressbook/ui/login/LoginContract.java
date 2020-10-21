package com.example.wzp109761.addressbook.ui.login;

import com.example.wzp109761.addressbook.ui.core.view.IView;

import java.math.BigInteger;

/**
 * 登录、注册协约类
 * author: 康栋普
 * date: 2018/3/6
 */

public interface LoginContract {

    interface ILoginPresenter {
        void login(BigInteger phone, String password);
        void register();
        void sendCheckCode(String phone);

    }


    interface ILoginRegisterView extends IView {

    }
}
