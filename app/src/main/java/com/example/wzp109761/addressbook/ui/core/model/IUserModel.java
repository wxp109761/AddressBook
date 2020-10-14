package com.example.wzp109761.addressbook.ui.core.model;

import android.graphics.Bitmap;

import com.example.wzp109761.addressbook.bean.User;
import com.example.wzp109761.addressbook.net.callback.RxObserver;

public interface IUserModel {
    void uploadAvatar(Bitmap bitmap, RxObserver<String> callback);
    void modifyUserInfo(User user, RxObserver<Object> callback);
    String setAvatarImg(String url);
}
