package com.example.wzp109761.addressbook.ui.core.model.impl;

import android.util.Log;

import com.example.wzp109761.addressbook.bean.TestBean;
import com.example.wzp109761.addressbook.net.RxSchedulers;
import com.example.wzp109761.addressbook.net.callback.RxObserver;
import com.example.wzp109761.addressbook.ui.core.model.ICommonModel;
public class CommonModel extends BaseModel implements ICommonModel {

    @Override
    public void GetTest(String test001, RxObserver<TestBean> callback) {
        doRxRequest()
                .getData()
                .compose(RxSchedulers.<TestBean>io_main())
                .subscribe(callback);
    }
}
