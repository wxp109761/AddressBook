package com.example.wzp109761.addressbook.ui.core.model.impl;

import android.graphics.Bitmap;

import com.example.wzp109761.addressbook.application.AppContext;
import com.example.wzp109761.addressbook.bean.User;
import com.example.wzp109761.addressbook.net.RxSchedulers;
import com.example.wzp109761.addressbook.net.callback.RxObserver;
import com.example.wzp109761.addressbook.ui.core.model.IUserModel;
import com.example.wzp109761.addressbook.utils.CommonUtils;
import com.google.gson.Gson;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserModel extends BaseModel implements IUserModel {
    @Override
    public void uploadAvatar(Bitmap bitmap, RxObserver<String> callback) {
        File file = CommonUtils.compressImage(bitmap);

        String[] bitmap1= AppContext.getContext().getResources().getAssets().getLocales();

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("headimg", file.getName(), requestFile);
        doRxRequest()
                .uploadAvatar(filePart)
                .compose(RxSchedulers.<String>io_main())
                .subscribe(callback);
    }

    @Override
    public void modifyUserInfo(User user, RxObserver<Object> callback) {
        Gson gson=new Gson();
        String strEntity = gson.toJson(user);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);
        doRxRequest()
                .modifyUserInfo(body)
                .compose(RxSchedulers.<Object>io_main())
                .subscribe(callback);
    }

    @Override
    public String setAvatarImg(String url) {
        return url;
    }
}
