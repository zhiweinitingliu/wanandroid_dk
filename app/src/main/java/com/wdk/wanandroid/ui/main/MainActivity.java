package com.wdk.wanandroid.ui.main;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.wdk.baselibrary.basepage.BaseActivity;
import com.wdk.baselibrary.basepage.DataBindingConfig;
import com.wdk.wanandroid.BR;
import com.wdk.wanandroid.R;
import com.wdk.wanandroid.constances.MessageEvent;
import com.wdk.wanandroid.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private MainViewModel mainViewModel;

    @Override
    protected void initViewModel() {
        mainViewModel = getActivityViewModel(MainViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig dataBindingConfig = new DataBindingConfig(R.layout.activity_main);
        dataBindingConfig.addBindingParam(BR.mainViewModel, mainViewModel);
        return dataBindingConfig;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(mBinding.navView, navController);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe()
    public void onMessageEvent(MessageEvent event) {
        switch (event.getMessage()) {
            //登录成功
            case MessageEvent.login_success:
                setRightTitle("退出");
                break;
        }

    }
}