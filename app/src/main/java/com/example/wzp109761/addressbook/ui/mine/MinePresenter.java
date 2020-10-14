package com.example.wzp109761.addressbook.ui.mine;
import android.graphics.Bitmap;

import com.example.wzp109761.addressbook.bean.User;
import com.example.wzp109761.addressbook.manager.UserInfoManager;
import com.example.wzp109761.addressbook.net.callback.RxObserver;
import com.example.wzp109761.addressbook.ui.core.model.impl.CommonModel;
import com.example.wzp109761.addressbook.ui.core.model.impl.UserModel;
import com.example.wzp109761.addressbook.ui.core.presenter.BasePresenter;

public class MinePresenter extends BasePresenter<MineContract.IMineView> implements MineContract.IMinePresenter  {
    private MineContract.IMineView iMineViewView;
    private UserModel iMineModel;
    public MinePresenter() {
        this.iMineModel = new UserModel();
    }

    @Override
    public void getTestData() {
        iMineViewView=getView();
    }

    @Override
    public void uploadAvatar(Bitmap bitmap) {
        iMineViewView=getView();
        RxObserver<String> observer=new RxObserver<String>(this) {
            @Override
            protected void onSuccess(String data) {
                User user=UserInfoManager.getUserInfo();
                if(user!=null){
                    user.setUrl(data);
                    modifyUserInfo(user);
                }
            }

            @Override
            protected void onFail(int errorCode, String errorMsg) {

            }
        };
        iMineModel.uploadAvatar(bitmap,observer);
        addDisposable(observer);

    }

    @Override
    public void modifyUserInfo(User user) {
        iMineViewView=getView();
        RxObserver<Object> observer=new RxObserver<Object>(this) {
            @Override
            protected void onSuccess(Object data) {
            }
            @Override
            protected void onFail(int errorCode, String errorMsg) {
            }
        };
        iMineModel.modifyUserInfo(user,observer);
        addDisposable(observer);
    }
}
