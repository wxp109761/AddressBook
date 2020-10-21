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


public class LoginPresenter extends BasePresenter<LoginContract.ILoginRegisterView> implements LoginContract.ILoginPresenter {
    private LoginModel loginRegisterModel;
    private LoginContract.ILoginRegisterView mLoginRegisterView;

    public LoginPresenter() {
        this.loginRegisterModel = new LoginModel();
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
                loginRegisterModel.saveUserInfo(userBean);
                iView.showResult("登录成功!");
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                iView.showResult(errorMsg);
            }
        };
        loginRegisterModel.login(phone,password, mLoginRxObserver);
        addDisposable(mLoginRxObserver);

    }

    /**
     * 注册
     */
    @Override
    public void register() {
//        if (!verifyAccount()) return;
        mLoginRegisterView=getView();
        RxObserver<String> mRegisterRxObserver = new RxObserver<String>(this) {
            @Override
            protected void onStart() {
                mLoginRegisterView.showLoading(AppContext.getContext().getString(R.string.isRegistering));
            }

            @Override
            protected void onSuccess(String data) {
                mLoginRegisterView.showResult(AppContext.getContext().getString(R.string.register_success));
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                mLoginRegisterView.showFail(errorMsg);
            }
        };
//        loginModel.register(jobNumber, password, mRegisterRxObserver);
        addDisposable(mRegisterRxObserver);
    }

    @Override
    public void sendCheckCode(String phone) {
        IView iView=getView();
        RxObserver<Object> msendCodeRxObserver = new RxObserver<Object>(this) {
            @Override
            protected void onSuccess(Object data) {
                iView.showResult("发送成功");
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {
                iView.showResult(errorMsg);
            }
        };
        loginRegisterModel.sendCheckCode(phone,msendCodeRxObserver);
        addDisposable(msendCodeRxObserver);
    }


    private VerifyAccountCallback mVerifyAccountCallback = new VerifyAccountCallback() {
        @Override
        public void onVerifyResult(String msg) {
            mLoginRegisterView.showFail(msg);
        }
    };



}
