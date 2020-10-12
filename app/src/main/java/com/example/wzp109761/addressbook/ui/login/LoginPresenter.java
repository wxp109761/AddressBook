package com.example.wzp109761.addressbook.ui.login;

import com.example.wzp109761.addressbook.R;
import com.example.wzp109761.addressbook.application.AppContext;
import com.example.wzp109761.addressbook.bean.User;
import com.example.wzp109761.addressbook.interfac.VerifyAccountCallback;
import com.example.wzp109761.addressbook.net.callback.RxObserver;
import com.example.wzp109761.addressbook.ui.core.model.impl.LoginModel;
import com.example.wzp109761.addressbook.ui.core.presenter.BasePresenter;
import com.example.wzp109761.addressbook.ui.core.view.IView;

import java.math.BigInteger;


public class LoginPresenter extends BasePresenter<LoginContract.ILoginRegisterView> implements LoginContract.IUserPresenter {
    private LoginModel loginModel;
    private LoginContract.ILoginRegisterView mLoginView;

    public LoginPresenter() {
        this.loginModel = new LoginModel();
    }

    /**
     * 登录
     */
    @Override
    public void login(BigInteger phone, String password) {
//        if (!verifyAccount()) return;
        IView iView=getView();
        RxObserver<User> mLoginRxObserver = new RxObserver<User>(this) {
            @Override
            protected void onSuccess(User userBean) {
                userBean.setPassword(password);
                loginModel.saveUserInfo(userBean);
                iView.showResult("登录成功!");
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                iView.showResult(errorMsg);
            }
        };
        loginModel.login(phone,password, mLoginRxObserver);
        addDisposable(mLoginRxObserver);

    }

    /**
     * 注册
     */
    @Override
    public void register() {
//        if (!verifyAccount()) return;
        RxObserver<String> mRegisterRxObserver = new RxObserver<String>(this) {
            @Override
            protected void onStart() {
                mLoginView.showLoading(AppContext.getContext().getString(R.string.isRegistering));
            }

            @Override
            protected void onSuccess(String data) {
                mLoginView.showResult(AppContext.getContext().getString(R.string.register_success));
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                mLoginView.showFail(errorMsg);
            }
        };
       // loginModel.register(jobNumber, password, mRegisterRxObserver);
        addDisposable(mRegisterRxObserver);
    }





    private VerifyAccountCallback mVerifyAccountCallback = new VerifyAccountCallback() {
        @Override
        public void onVerifyResult(String msg) {
            mLoginView.showFail(msg);
        }
    };



}
