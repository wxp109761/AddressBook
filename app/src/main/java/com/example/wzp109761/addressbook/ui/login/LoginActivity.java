package com.example.wzp109761.addressbook.ui.login;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wzp109761.addressbook.R;
import com.example.wzp109761.addressbook.ui.base.BasePresenterActivity;
import com.example.wzp109761.addressbook.ui.core.view.IView;
import com.example.wzp109761.addressbook.ui.main.MainActivity;
import com.example.wzp109761.addressbook.utils.CommonUtils;
import com.example.wzp109761.addressbook.utils.NetWorkUtils;
import com.example.wzp109761.addressbook.widget.Interpolator;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

import java.math.BigInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import top.wefor.circularanim.CircularAnim;

/**
 * 登录、注册
 * Created by 康栋普 on 2018/2/1.
 */

public class LoginActivity extends BasePresenterActivity<LoginPresenter> implements IView {
    @BindView(R.id.input_login_name)
    EditText inputLoginName;
    @BindView(R.id.input_layout_name)
    LinearLayout inputLayoutName;
    @BindView(R.id.input_login_pwd)
    EditText inputLoginPwd;
    @BindView(R.id.input_layout_psw)
    LinearLayout inputLayoutPsw;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar2;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_forget_pass)
    Button btnForgetPass;
    @BindView(R.id.btn_registry)
    Button btnRegistry;
    private EditText et_job_number, et_password;
    private SharedPreferences.Editor editor;
    private SharedPreferences login_sp;

    private View mInputLayout;
    private View progress;

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        CommonUtils.getPer(this, LoginActivity.this);
        mInputLayout = findViewById(R.id.login_input_layout);
        progress = findViewById(R.id.login_layout_progress);
        login_sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemenber = login_sp.getBoolean("remember_password", true);
        if (isRemenber) {
            //将账号和密码都设置到文本中
            String account = login_sp.getString("account", "");
            String password = login_sp.getString("password", "");
            inputLoginName.setText(account);
            inputLoginPwd.setText(password);

        }
        mBtnLogin.setOnClickListener(new animationOnClickListener(this, mBtnLogin));

    }


    @Override
    public void showResult(String msg) {
        final String username = inputLoginName.getText().toString();
        final String password = inputLoginPwd.getText().toString();
        Log.d("------ccc---", msg);
        if (msg.equals("登录成功!")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            //记住密码
            editor = login_sp.edit();
//            if (loginRemember.isChecked()) {
            editor.putBoolean("remember_password", true);
            editor.putString("account", username);
            editor.putString("password", password);
//            } else {
//                editor.clear();
//            }
            editor.apply();
            finish();
        } else {
            recovery();
            Toasty.error(LoginActivity.this, "账号或密码不正确", Toast.LENGTH_SHORT, true).show();
        }
    }


    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        CommonUtils.setStatusBar(this);
        super.onCreate(bundle);
    }



    @OnClick({R.id.btn_forget_pass, R.id.btn_registry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_forget_pass:
                forgetPassWord();
                break;
            case R.id.btn_registry:
                UserRegistry();
                break;
        }
    }



    private void forgetPassWord() {
        String stringItems[] = {"个人信息验证", "手机验证码登录"};
        final ActionSheetDialog dialog = new ActionSheetDialog(this, stringItems, null);
        dialog.isTitleShow(false).show();
        dialog.itemTextColor(Color.parseColor("#0091ea"));
        dialog.cancelText(Color.parseColor("#0091ea"));
        // dialog.itemPressColor(Color.parseColor("#e9857d"));
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    case 1:
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        intent.putExtra("cate","login");
                        startActivity(intent);
                        break;
                }
                dialog.dismiss();
            }
        });
    }
    private void UserRegistry() {
        CircularAnim.fullActivity(this,btnRegistry)
                .go(new CircularAnim.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd() {
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        intent.putExtra("cate","register");
                        startActivity(intent);
                    }
                });
    }


    class animationOnClickListener implements View.OnClickListener {
        private Context context;
        private TextView btnLogin;


        public animationOnClickListener(Context context, Button btnLogin) {
            this.btnLogin = btnLogin;
            this.context = context;
        }

        private float mWidth, mHeight;

        private void inputAnimator(final View view, float w, float h) {

            AnimatorSet set = new AnimatorSet();

            ValueAnimator animator = ValueAnimator.ofFloat(0, w);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (Float) animation.getAnimatedValue();
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                            .getLayoutParams();
                    params.leftMargin = (int) value;
                    params.rightMargin = (int) value;
                    view.setLayoutParams(params);
                }
            });

            ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                    "scaleX", 1f, 0.5f);
            set.setDuration(1000);
            set.setInterpolator(new AccelerateDecelerateInterpolator());
            set.playTogether(animator, animator2);
            set.start();
            set.addListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                    progress.setVisibility(View.VISIBLE);
                    progressAnimator(progress);
                    mInputLayout.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    // TODO Auto-generated method stub

                }
            });

        }

        private void progressAnimator(final View view) {
            PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                    0.5f, 1f);
            PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                    0.5f, 1f);
            ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                    animator, animator2);
            animator3.setDuration(1000);
            animator3.setInterpolator(new Interpolator());
            animator3.start();

        }

        @Override
        public void onClick(View view) {

            if (btnLogin.getVisibility() == View.VISIBLE) {

                btnLogin.setVisibility(View.GONE);

                // 计算出控件的高与宽
                mWidth = btnLogin.getMeasuredWidth();
                mHeight = btnLogin.getMeasuredHeight();
                // 隐藏输入框
                inputLayoutName.setVisibility(View.INVISIBLE);
                inputLayoutPsw.setVisibility(View.INVISIBLE);

                inputAnimator(mInputLayout, mWidth, mHeight);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final String username = inputLoginName.getText().toString();
                        final String password = inputLoginPwd.getText().toString();

                        if (NetWorkUtils.isNetworkConnected(getApplication())) {

                            if (TextUtils.isEmpty(username.toString()) || TextUtils.isEmpty(password)) {
//                                Toast.makeText(LoginActivity.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
                                Toasty.info(LoginActivity.this, "请输入账号或密码", Toast.LENGTH_SHORT, true).show();
                                recovery();
                                return;
                            }

                            mPresenter.login(BigInteger.valueOf(Long.parseLong(username)), password);
                        } else {
                            recovery();
//                            Toast.makeText(LoginActivity.this, "无网络连接！", Toast.LENGTH_SHORT).show();
                            Toasty.error(LoginActivity.this, "无网络连接", Toast.LENGTH_SHORT, true).show();
                        }
                    }
                }, 2000);

            }

        }
    }

    /**
     * 恢复初始状态
     */
    private void recovery() {

        progress.setVisibility(View.GONE);
        mBtnLogin.setVisibility(View.VISIBLE);
        mInputLayout.setVisibility(View.VISIBLE);
        inputLayoutName.setVisibility(View.VISIBLE);
        inputLayoutPsw.setVisibility(View.VISIBLE);

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mInputLayout.getLayoutParams();
        params.leftMargin = 0;
        params.rightMargin = 0;
        mInputLayout.setLayoutParams(params);


        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 0.5f, 1f);
        animator2.setDuration(500);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.start();
    }


}
