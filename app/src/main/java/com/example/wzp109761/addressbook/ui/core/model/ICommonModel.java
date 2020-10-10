package com.example.wzp109761.addressbook.ui.core.model;
import com.example.wzp109761.addressbook.bean.TestBean;
import com.example.wzp109761.addressbook.net.callback.RxObserver;

import java.util.List;

/**
 * 通用数据接口
 */
public interface ICommonModel {
    void GetTest(String test001,RxObserver<TestBean> callback);
}
