package com.example.wzp109761.addressbook.net.callback;




import android.util.Log;

import com.example.wzp109761.addressbook.application.AppContext;
import com.example.wzp109761.addressbook.bean.BaseBean;
import com.example.wzp109761.addressbook.net.NetExceptionHandle;
import com.example.wzp109761.addressbook.ui.core.presenter.BasePresenter;
import com.example.wzp109761.addressbook.ui.core.view.IView;

import io.reactivex.observers.DisposableObserver;

/**
 * RxRetrofit请求回调基类
 */

public abstract class RxBaseObserver<T> extends DisposableObserver<BaseBean<T>> {

    protected IView view;

    RxBaseObserver(BasePresenter mPresenter) {
        this.view = mPresenter.getView();
    }


    @Override
    protected void onStart() {
        super.onStart();
        //显示loading
        showLoading();
    }

    public void showLoading() {
        view.showLoading("");
    }

    @Override
    public void onError(Throwable e) {
        Log.d("--xxx--",""+e.getMessage()+"  ===");
        //隐藏loading
        hideLoading();
        //处理异常
        NetExceptionHandle.dealException(AppContext.getContext(),e);
    }

    @Override
    public void onComplete() {
        hideLoading();
    }

    private void hideLoading() {
        if (null != view)
            this.view.hideLoading();
    }

}
