package com.example.wzp109761.addressbook.ui.mine;
import android.graphics.Bitmap;

import com.example.wzp109761.addressbook.bean.User;
import com.example.wzp109761.addressbook.ui.core.view.IView;

public interface MineContract {

    interface IMinePresenter {
        void getTestData();
        void uploadAvatar(Bitmap bitmap);
        void modifyUserInfo(User user);
    }
    interface IMineView extends IView {
    }



}
