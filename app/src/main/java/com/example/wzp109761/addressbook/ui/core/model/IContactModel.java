package com.example.wzp109761.addressbook.ui.core.model;

import com.example.wzp109761.addressbook.bean.PhoneListBean;
import com.example.wzp109761.addressbook.net.callback.RxObserver;

import java.util.List;

/**
 *
 */

public interface IContactModel {

    void PostContacts(List<PhoneListBean> phoneListBean, RxObserver<String> callback);



}
