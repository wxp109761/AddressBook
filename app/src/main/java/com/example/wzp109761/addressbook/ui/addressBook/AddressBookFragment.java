package com.example.wzp109761.addressbook.ui.addressBook;

import android.annotation.SuppressLint;
import android.provider.Settings;
import android.util.Log;

import com.example.wzp109761.addressbook.bean.PhoneListBean;
import com.example.wzp109761.addressbook.ui.adapter.AddressBookListAdapter;
import com.example.wzp109761.addressbook.ui.adapter.BaseListAdapter;
import com.example.wzp109761.addressbook.ui.base.BaseAbListFragment;

import java.util.List;

@SuppressLint("ValidFragment")
public class AddressBookFragment extends BaseAbListFragment<AddressBookPresenter, PhoneListBean> implements AddressBookContract.IAddressBookView {
    private String android_id;

    @Override
    protected AddressBookPresenter createPresenter() {
        return new AddressBookPresenter();
    }


    @Override
    protected void loadDatas() {
        mPresenter.postContacts(mPresenter.initContacts(getActivity().getApplicationContext()));
        mPresenter.getContacts(getActivity().getApplicationContext());
         android_id= Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
         Log.d("-----XXXX----","  |  "+android_id);
    }

    @Override
    protected BaseListAdapter<PhoneListBean> getListAdapter() {
        return new AddressBookListAdapter();
    }


    @Override
    public void setData(List<PhoneListBean> data) {
        mListData.clear();
        mListData.addAll(data);
    }

    @Override
    public void showResult(String msg) {
        Log.d("-----XXXXXX------",msg);
    }
}
