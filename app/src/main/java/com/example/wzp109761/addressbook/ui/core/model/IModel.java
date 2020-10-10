package com.example.wzp109761.addressbook.ui.core.model;

import com.example.wzp109761.addressbook.api.ApiServer;


public interface IModel {
    /**
     * 使用RxRetrofit请求数据
     *
     * @return
     */
    ApiServer doRxRequest();

}
