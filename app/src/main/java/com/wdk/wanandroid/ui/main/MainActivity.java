package com.wdk.wanandroid.ui.main;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.gyf.immersionbar.ImmersionBar;
import com.wdk.component_base.basepage.BaseActivity;
import com.wdk.component_base.basepage.DataBindingConfig;
import com.wdk.wanandroid.R;
import com.wdk.wanandroid.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    protected void initViewModel() {

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig dataBindingConfig = new DataBindingConfig(R.layout.activity_main);
        return dataBindingConfig;
    }

    @Override
    protected void initData() {
        ImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).init();
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(mBinding.navView, navController);
    }

}