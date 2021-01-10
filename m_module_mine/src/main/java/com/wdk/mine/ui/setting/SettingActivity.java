package com.wdk.mine.ui.setting;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.wdk.component_base.basepage.BaseActivity;
import com.wdk.component_base.basepage.DataBindingConfig;
import com.wdk.component_base.data.constances.MessageEvent;
import com.wdk.mine.BR;
import com.wdk.mine.R;
import com.wdk.mine.databinding.ActivitySettingBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SettingActivity extends BaseActivity<ActivitySettingBinding> {


    SettingViewModel settingViewModel;

    @Override
    protected void initViewModel() {
        settingViewModel = getActivityViewModel(SettingViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig dataBindingConfig = new DataBindingConfig(R.layout.activity_setting);
        dataBindingConfig.addBindingParam(BR.settingViewModel, settingViewModel);
        dataBindingConfig.addBindingParam(BR.myHandlers, new MyHandlers());
        return dataBindingConfig;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName("账户设置");
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getMessage()) {
            //登录成功
            case MessageEvent.login_out:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public class MyHandlers {

        public void onLoginOutClick(View view) {
            settingViewModel.loginOut();
        }
    }
}
