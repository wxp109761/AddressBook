package com.example.wzp109761.addressbook.ui.core.model.impl;

import com.example.wzp109761.addressbook.bean.PhoneListBean;
import com.example.wzp109761.addressbook.net.RxSchedulers;
import com.example.wzp109761.addressbook.net.callback.RxObserver;
import com.example.wzp109761.addressbook.ui.core.model.IContactModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import okhttp3.RequestBody;

public class ContactModel extends BaseModel implements IContactModel {
    /**
     * 上传数据
     * @param phoneListBean
     * @param callback
     */
    @Override
    public void PostContacts(List<PhoneListBean> phoneListBean, RxObserver<String> callback) {


        Gson gson=new Gson();
        String str="{\n" +
                "    \"phoneList\":"+gson.toJson(phoneListBean)+"}";
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=utf-8"), str);
        doRxRequest().postContacts(jsonObject)
                .compose(RxSchedulers.<String>io_main())
                .subscribe(callback);
    }
}
