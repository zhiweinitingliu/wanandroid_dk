package com.wdk.wanandroid.ui.main;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wdk.baselibrary.basepage.BaseActivity;
import com.wdk.baselibrary.basepage.DataBindingConfig;
import com.wdk.wanandroid.BR;
import com.wdk.wanandroid.R;
import com.wdk.wanandroid.constances.MessageEvent;
import com.wdk.wanandroid.utils.UserInfoUtil;
import com.wdk.wanandroid.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private MainViewModel mainViewModel;
//    private RecyclerView recyclerView;
//    private ArticleListAdapter articleListAdapter;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(mBinding.navView, navController);


//        setTitleName("首页");
//        hideCallBack();
//        if (UserInfoUtil.getInstance().getUserId() <= 0) {
//            setRightTitle("登录");
//        } else {
//            setRightTitle("退出");
//        }

//        recyclerView = getMBinding().recyclerView;
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        articleListAdapter = new ArticleListAdapter(this);
//        recyclerView.setAdapter(articleListAdapter);
//
//        initListener();
//        mainViewModel.getArticleList();
    }

    private static final String TAG = "MainActivity";

    private void initListener() {
//        mainViewModel.getmArticleList().observe(this, new Observer<List<ArticleBean.ArticleChildBean>>() {
//            @Override
//            public void onChanged(List<ArticleBean.ArticleChildBean> articleBeans) {
//                Log.e(TAG, "article_onChanged: " + articleBeans.size());
//                articleListAdapter.getItems().addAll(articleBeans);
//                articleListAdapter.notifyDataSetChanged();
//            }
//        });
//
//        if (getTitleView() != null) {
//            getTitleView().findViewById(R.id.rlTitle).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (UserInfoUtil.getInstance().getUserId() <= 0) {
//                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                    } else {
//                        SharedPreferencesUtil.getInstance().putStringValue(Constants.user_info_json, "");
//                        setRightTitle("登录");
//                    }
//                }
//            });
//        }


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