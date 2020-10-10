package com.example.wzp109761.addressbook.ui.base;

import android.content.Intent;
import android.os.Bundle;

import com.example.wzp109761.addressbook.ui.core.presenter.BasePresenter;
import com.example.wzp109761.addressbook.ui.core.view.IView;
import com.example.wzp109761.addressbook.utils.ToastUtils;


/**
 * 管理Presenter的Activity基类
 * Created by 康栋普 on 2018/2/1.
 */

public abstract class BasePresenterActivity<P extends BasePresenter> extends BaseActivity implements IView {

   protected P mPresenter;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mPresenter = createPresenter();
        attachView();

    }

    @Override
    protected void onNavigationClick() {
        finish();
    }

    @Override
    protected void getIntent(Intent intent) {
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        //接触presenter与View关联
        detachView();
        super.onDestroy();
    }

    //关联View
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


    @Override
    protected boolean initToolbar() {
        return false;
    }

    @Override
    protected void initViews() {
    }

    @Override
    public void showLoading(String msg) {
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void showFail(String msg) {
        ToastUtils.showToast(this, msg);
    }
    @Override
    public void showError() {
    }
    @Override
    public void showEmpty() {
    }


    @Override
    protected void receiveEvent(Object object) {
    }

    @Override
    protected String registerEvent() {
        return null;
    }
}
