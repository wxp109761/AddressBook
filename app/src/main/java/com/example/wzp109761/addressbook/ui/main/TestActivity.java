package com.example.wzp109761.addressbook.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wzp109761.addressbook.R;
import com.example.wzp109761.addressbook.application.AppContext;
import com.example.wzp109761.addressbook.utils.ChangeHeaderUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.wzp109761.addressbook.application.AppContext.getContext;

public class TestActivity extends Activity {
    @BindView(R.id.avatar_img)
    ImageView avatarImg;
    @BindView(R.id.change_avatar)
    Button changeAvatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.change_avatar)
    public void onViewClicked() {

    }



}
