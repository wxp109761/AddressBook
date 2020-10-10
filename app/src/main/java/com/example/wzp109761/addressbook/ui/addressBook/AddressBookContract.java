package com.example.wzp109761.addressbook.ui.addressBook;


import android.content.Context;
import com.example.wzp109761.addressbook.bean.PhoneListBean;
import com.example.wzp109761.addressbook.ui.core.view.IListDataView;

import java.util.List;

public interface AddressBookContract {

    interface IAddressBookPresenter {
        List<PhoneListBean> initContacts(Context context);
        void getContacts(Context context);
        void postContacts(List<PhoneListBean> phoneListBean);
    }
    interface IAddressBookView extends IListDataView<PhoneListBean> {
    }
}
