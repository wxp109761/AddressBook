package com.example.wzp109761.addressbook.ui.core.model.impl;


import android.util.Log;

import com.example.wzp109761.addressbook.api.ApiServer;
import com.example.wzp109761.addressbook.net.RxRetrofit;
import com.example.wzp109761.addressbook.ui.core.model.IModel;

public class BaseModel implements IModel {

    @Override
    public ApiServer doRxRequest() {
        Log.d("---xxxx--","doRxRequest");
        return RxRetrofit.Api();
    }
}
