package com.example.wzp109761.addressbook.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.wzp109761.addressbook.R;
import com.example.wzp109761.addressbook.bean.User;
import com.example.wzp109761.addressbook.manager.UserInfoManager;
import com.example.wzp109761.addressbook.utils.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserInfoFragment extends Fragment {

    @BindView(R.id.user_sex)
    TextView userSex;
    @BindView(R.id.user_birthday)
    TextView userBirthday;
    @BindView(R.id.register_data)
    TextView registerData;
    @BindView(R.id.update_date)
    TextView updateDate;
    @BindView(R.id.telphone)
    TextView telphone;

    public UserInfoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo, container, false);
        ButterKnife.bind(this, view);
        User user = UserInfoManager.getUserInfo();
        if (user != null) {
            userSex.setText((user.getSex() == 0) ? "男" : "女");
            userBirthday.setText(user.getBirthday());

            telphone.setText(user.getPhone()+"");
            registerData.setText(DateUtils.parseTime(user.getCreateTime()));
            updateDate.setText(DateUtils.parseTime(user.getModifyTime()));
        }

        return view;
    }


}
