package com.example.wzp109761.addressbook.ui.addressBook;

import android.content.Context;
import android.os.Build;
import android.provider.Contacts;
import android.util.Log;

import com.example.wzp109761.addressbook.bean.PhoneListBean;
import com.example.wzp109761.addressbook.net.callback.RxObserver;
import com.example.wzp109761.addressbook.ui.core.model.impl.ContactModel;
import com.example.wzp109761.addressbook.ui.core.presenter.BasePresenter;
import com.example.wzp109761.addressbook.utils.ReadContastUtils;

import java.util.List;


public class AddressBookPresenter extends BasePresenter<AddressBookContract.IAddressBookView> implements AddressBookContract.IAddressBookPresenter  {
    private AddressBookContract.IAddressBookView addressBookView;
    private ContactModel contactModel;
    public AddressBookPresenter() {
        this.contactModel = new ContactModel();
    }

    @Override
    public List<PhoneListBean> initContacts(Context context) {
//        return ReadContastUtils.getPhoneContant(context);
        return ReadContastUtils.getSimContant(context);
    }

    @Override
    public void getContacts(Context context) {
        addressBookView=getView();
        List<PhoneListBean> contacts=initContacts(context);
        addressBookView.setData(contacts);
        if (addressBookView.getData().size() == 0){
            addressBookView.showEmpty();
        } else
            addressBookView.showContent();
    }


    @Override
    public void postContacts(List<PhoneListBean> phoneListBean) {
        addressBookView=getView();
        RxObserver<String> mPostContactsRxObserver=new RxObserver<String>(this) {
            @Override
            protected void onSuccess(String data) {
                Log.d("-----XXXXXX------","成功");
                addressBookView.showResult(data);
            }
            @Override
            protected void onFail(int errorCode, String errorMsg) {
                Log.d("-----XXXXXX------","失败");
                addressBookView.showResult(errorMsg+"---"+errorCode);
            }
        };
        contactModel.PostContacts(phoneListBean,mPostContactsRxObserver);
        addDisposable(mPostContactsRxObserver);
    }

}
