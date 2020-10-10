package com.example.wzp109761.addressbook.net.callback;



import android.util.Log;

import com.example.wzp109761.addressbook.bean.BaseBean;
import com.example.wzp109761.addressbook.net.NetConfig;
import com.example.wzp109761.addressbook.ui.core.presenter.BasePresenter;

/**
 * RxRetrofit通用接口回调类
 */

public abstract class RxObserver<T> extends RxBaseObserver<T> {
    public RxObserver(BasePresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public void onNext(BaseBean<T> mBaseBean) {
        Log.d("--xxx--",""+mBaseBean.message+"---");
        //请求成功
        if (mBaseBean.code == NetConfig.REQUEST_SUCCESS) {
            onSuccess(mBaseBean.data);
        } else {
         //失败
            onFail(mBaseBean.code, mBaseBean.message);
        }
    }

    protected abstract void onSuccess(T data);

    protected abstract void onFail(int errorCode, String errorMsg);

}
