package com.example.wzp109761.addressbook.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.wzp109761.addressbook.R;
import com.example.wzp109761.addressbook.bean.PhoneListBean;
import com.example.wzp109761.addressbook.common.ListDataHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressBookListAdapter extends BaseListAdapter<PhoneListBean> {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_address_book_list;
    }

    @Override
    public void bindDatas(ListDataHolder holder,PhoneListBean bean, int itemType, int position) {
        ButterKnife.bind(this,holder.itemView);
        tvName.setText(bean.getName());
        tvPhone.setText(bean.getPhone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }



}
