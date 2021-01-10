package com.wdk.mine.ui.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.wdk.component_base.basepage.BaseActivity;
import com.wdk.component_base.basepage.DataBindingConfig;
import com.wdk.component_base.data.constances.MessageEvent;
import com.wdk.component_base.utils.CustomerToast;
import com.wdk.mine.BR;
import com.wdk.mine.R;
import com.wdk.mine.databinding.ActivityRegisterBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 11:25 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 11:25 AM
 * @LastCheckBy: wdk
 */
public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {

    private RegisterViewModel registerViewModel;

    @Override
    protected void initViewModel() {
        registerViewModel = getActivityViewModel(RegisterViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig dataBindingConfig = new DataBindingConfig(R.layout.activity_register);
        dataBindingConfig.addBindingParam(BR.accountViewModel, registerViewModel);
        return dataBindingConfig;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName("注册");
    }

    /**
     * 去注册
     */
    public void toRegister(View view) {
        String userName = getMBinding().etUserName.getText().toString();
        String password = getMBinding().etPassword.getText().toString();
        String rePassword = getMBinding().etRePassword.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            CustomerToast.showToast("请输入注册用户名");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            CustomerToast.showToast("请输入注册密码");
            return;
        }

        if (TextUtils.isEmpty(rePassword)) {
            CustomerToast.showToast("请输入确认密码");
            return;
        }

        registerViewModel.doRegister(userName, password, rePassword);
        showLoading();

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
