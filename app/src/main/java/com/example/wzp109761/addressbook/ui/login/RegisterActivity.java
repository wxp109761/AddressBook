package com.example.wzp109761.addressbook.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wzp109761.addressbook.R;
import com.example.wzp109761.addressbook.ui.base.BasePresenterActivity;
import com.example.wzp109761.addressbook.ui.core.view.IView;
import com.example.wzp109761.addressbook.utils.RegexUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册
 * Created by 康栋普 on 2018/2/1.
 */

public class RegisterActivity extends BasePresenterActivity<LoginPresenter> implements IView {

    @BindView(R.id.btn_get_check_code)
    Button btnGetCheckCode;
    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @BindView(R.id.tv_register_title)
    TextView tvRegisterTitle;
    @BindView(R.id.tv_register_tips)
    TextView tvRegisterTips;
    private boolean isGetCodeBtnClick = false;

    @Override
    protected boolean initToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_right);
        mToolbar.setTitle("");
        mToolbar.setBackgroundColor(getResources().getColor(R.color.white));
        return true;
    }


    @Override
    protected void initViews() {
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String cate = intent.getStringExtra("cate");
        if (cate.equals("register")) {
            tvRegisterTips.setVisibility(View.GONE);
            tvRegisterTitle.setText("用户注册");
        } else {
            tvRegisterTips.setVisibility(View.VISIBLE);
            tvRegisterTitle.setText("欢迎使用小张通讯录");
        }

        etRegisterPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                StringBuilder stringBuilder = new StringBuilder();
                if (charSequence == null || charSequence.length() == 0) {
                    return;
                }

                for (int i = 0; i < charSequence.length(); i++) {
                    if (i != 3 && i != 8 && charSequence.charAt(i) == ' ') {
                        continue;
                    } else {
                        stringBuilder.append(charSequence.charAt(i));
                        if ((stringBuilder.length() == 4 || stringBuilder.length() == 9)
                                && stringBuilder.charAt(stringBuilder.length() - 1) != ' ') {
                            stringBuilder.insert(stringBuilder.length() - 1, ' ');
                        }
                    }
                }
                if (!stringBuilder.toString().equals(charSequence.toString())) {
                    int index = start + 1;
                    if (stringBuilder.charAt(start) == ' ') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    etRegisterPhone.setText(stringBuilder.toString());
                    etRegisterPhone.setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                isGetCodeBtnClick = RegexUtils.checkMobile(etRegisterPhone.getText().toString().replaceAll(" ", ""));
                if (isGetCodeBtnClick) {
                    btnGetCheckCode.setEnabled(true);
                    Log.d("XXX", "click");
                    btnGetCheckCode.setBackground(getResources().getDrawable(R.drawable.btn_get_code));
                } else {
                    btnGetCheckCode.setEnabled(false);
                    Log.d("XXX", "false");
                    btnGetCheckCode.setBackground(getResources().getDrawable(R.drawable.btn_get_code2));
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void showResult(String msg) {
        Log.d("xxx",msg);
        if(msg.equals("发送成功")){

        }
    }


    @OnClick(R.id.btn_get_check_code)
    public void onViewClicked() {
        mPresenter.sendCheckCode("phone=17317861648");
    }

}
