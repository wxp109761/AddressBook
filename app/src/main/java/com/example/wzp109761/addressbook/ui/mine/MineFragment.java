package com.example.wzp109761.addressbook.ui.mine;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.wzp109761.addressbook.R;
import com.example.wzp109761.addressbook.ui.adapter.FragmentAdapter;
import com.example.wzp109761.addressbook.ui.base.BasePresenterFragment;
import com.example.wzp109761.addressbook.utils.ChangeHeaderUtils;
import com.example.wzp109761.addressbook.widget.CircleImageView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.kekstudio.dachshundtablayout.indicators.DachshundIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends BasePresenterFragment<MinePresenter> implements MineContract.IMineView {


    @BindView(R.id.top_bg)
    ImageView topBg;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.autograph)
    TextView autograph;
    @BindView(R.id.toolbar_userhead)
    CircleImageView toolbarUserhead;
    @BindView(R.id.toolbar_username)
    TextView toolbarUsername;
    @BindView(R.id.user_toolbar)
    Toolbar userToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.user_app_bar)
    AppBarLayout userAppBar;
    @BindView(R.id.user_head)
    CircleImageView userHead;
    @BindView(R.id.tab_layout_user)
    DachshundTabLayout tabLayoutUser;
    @BindView(R.id.view_pager_user)
    ViewPager viewPagerUser;
    private View rootView;


    @Override
    protected void initViews(View view) {
        this.rootView = view;
        ButterKnife.bind(this, rootView);
        initViewPager();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected MinePresenter createPresenter() {


        return new MinePresenter();
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("个人信息");
        titles.add("扩展");


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new UserInfoFragment());
        fragments.add(new UserInfoFragment());

        viewPagerUser.setOffscreenPageLimit(2);
        FragmentAdapter mFragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments, titles);
        tabLayoutUser.setAnimatedIndicator(new DachshundIndicator(tabLayoutUser));
        viewPagerUser.setAdapter(mFragmentAdapter);
        tabLayoutUser.setupWithViewPager(viewPagerUser);
        tabLayoutUser.setTabsFromPagerAdapter(mFragmentAdapter);
    }


//    /**
//     * 加载用户信息
//     */
//    private void setUserDataFromBmob(){
//        User user=UserInfoManager.getUserInfo();
//        toolbarUsername.setText(user.getUsername());
//        nickname.setText(user.getUsername());
//        autograph.setText(CommonUtils.userRole());
//        if (user != null) {
//            MyTask task = new MyTask();
//            task.execute(user.getAvatarUrl());
//        }
//
//    }

    /**
     * 编辑用户信息和更新头像
     *
     * @param view
     */
    @OnClick({R.id.user_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_head:
                changeHeaderUtils.ChandeHeader(getContext(), getActivity());

                break;
        }
    }

    @Override
    public void showResult(String msg) {
        Log.d("XXXX", "---" + msg);
    }

    ChangeHeaderUtils changeHeaderUtils = new ChangeHeaderUtils();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        changeHeaderUtils.onRequestPermissions(requestCode, permissions, grantResults, getContext(), getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        changeHeaderUtils.onAtyResult(requestCode, resultCode, data, getContext(), getActivity(), userHead);
    }

}
