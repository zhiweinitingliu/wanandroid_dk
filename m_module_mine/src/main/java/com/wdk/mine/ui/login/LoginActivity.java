package com.wdk.mine.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.wdk.component_base.basepage.BaseActivity;
import com.wdk.component_base.basepage.DataBindingConfig;
import com.wdk.component_base.data.constances.MessageEvent;
import com.wdk.mine.BR;
import com.wdk.mine.R;
import com.wdk.mine.databinding.ActivityLoginBinding;
import com.wdk.mine.ui.register.RegisterActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 10:24 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 10:24 AM
 * @LastCheckBy: wdk
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    private static final String TAG = "LoginActivity";

    private LoginViewModel loginViewModel;

    @Override
    protected void initViewModel() {
        loginViewModel = getActivityViewModel(LoginViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig dataBindingConfig = new DataBindingConfig(R.layout.activity_login);
        dataBindingConfig.addBindingParam(BR.accountViewModel, loginViewModel);
        return dataBindingConfig;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName("登录");
    }

    /**
     * 登录
     */
    public void doLogin(View view) {
        String userName = getMBinding().etUserName.getText().toString();
        String password = getMBinding().etPassword.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "请输入登录名", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入登录密码", Toast.LENGTH_SHORT).show();
            return;
        }

        loginViewModel.doLogin(userName, password);
    }

    /**
     * 去注册页面
     */
    public void goRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getMessage()) {
            //登录成功
            case MessageEvent.login_success:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
