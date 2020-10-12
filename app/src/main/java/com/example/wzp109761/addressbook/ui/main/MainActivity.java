package com.example.wzp109761.addressbook.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.wzp109761.addressbook.R;
import com.example.wzp109761.addressbook.application.AppContext;
import com.example.wzp109761.addressbook.common.Const;
import com.example.wzp109761.addressbook.ui.addressBook.AddressBookFragment;
import com.example.wzp109761.addressbook.ui.base.BaseActivity;
import com.example.wzp109761.addressbook.ui.mine.MineFragment;

import com.example.wzp109761.addressbook.utils.ToastUtils;

import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Button[] btns;
    private Fragment[] fragments;
    private int currentPosition;
    private int index;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }




    @Override
    protected void initViews() {

        btns = new Button[2];
        btns[0] = findViewById(R.id.btn_main);
        btns[1] = findViewById(R.id.btn_system);
        btns[0].setSelected(true);
        for (int i = 0; i < btns.length; i++) {
            btns[i].setOnClickListener(this);
            if (i != currentPosition) {
                btns[i].setScaleX(0.9f);
                btns[i].setScaleY(0.9f);
            }
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main:
                index = 0;
                break;
            case R.id.btn_system:
                index = 1;
                break;

            default:
        }
        showCurrentFragment(index);
    }


    private View.OnClickListener onScrollTopListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String action="";
            switch (currentPosition) {
                case 0:
                    action = Const.EVENT_ACTION.ADDRESSBOOK;
                    break;
                case 1:
                    action = Const.EVENT_ACTION.MINE;
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle bundle) {
        setStatusBar();
        super.onCreate(bundle);
        initFragments();
    }


    private void initFragments() {
        fragments = new Fragment[]{new AddressBookFragment(), new MineFragment()};
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, fragments[0]).show(fragments[0]).commitAllowingStateLoss();
    }


    /**
     * 切换显示当前Fragment
     *
     * @param index
     */
    private void showCurrentFragment(int index) {
        if (currentPosition != index) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(fragments[currentPosition]);
            if (!fragments[index].isAdded()) {
                ft.add(R.id.container, fragments[index]);
            }
            ft.show(fragments[index]).commit();
            btns[currentPosition].setSelected(false);
            btns[index].setSelected(true);
            scaleView();
            currentPosition = index;
        }
    }
    /**
     * view放大缩小
     */
    private void scaleView() {
        btns[currentPosition].animate().scaleX(0.9f).scaleY(0.9f)
                .setDuration(150).start();
        btns[index].animate().scaleX(1.0f).scaleY(1.0f)
                .setDuration(150).start();
    }
    private long mExitTime;
    @SuppressLint("WrongConstant")
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime < 2000) {
                finish();
            } else {
                mExitTime = System.currentTimeMillis();
                ToastUtils.showToast(AppContext.getContext(), "请再按一次退出程序");
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected String registerEvent() {
        return Const.EVENT_ACTION.MAIN;
    }
    /**
     * 设置状态栏透明
     */
    private void setStatusBar(){
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getSupportFragmentManager().getFragments();
        if (getSupportFragmentManager().getFragments().size() > 0) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment mFragment : fragments) {
                mFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
    }