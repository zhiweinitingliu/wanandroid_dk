package com.wdk.wanandroid.ui.home;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wdk.baselibrary.basepage.BaseFragment;
import com.wdk.baselibrary.basepage.DataBindingConfig;
import com.wdk.wanandroid.BR;
import com.wdk.wanandroid.R;
import com.wdk.wanandroid.data.bean.home.ArticleChildBean;
import com.wdk.wanandroid.data.bean.home.BannerBean;
import com.wdk.wanandroid.data.bean.home.BannerChildBean;
import com.wdk.wanandroid.data.bean.home.HomeArticleBean;
import com.wdk.wanandroid.data.bean.home.HomeBannerBean;
import com.wdk.wanandroid.data.bean.home.HomeBaseBean;
import com.wdk.wanandroid.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private static final String TAG = "HomeFragment";

    private HomeViewModel homeViewModel;

    //    private ArticleListAdapter articleListAdapter;
    private HomeAdapter homeAdapter;
    List<HomeBaseBean> homeBeanList;

    @Override
    protected void initViewModel() {
        homeViewModel = getFragmentViewModel(HomeViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig dataBindingConfig = new DataBindingConfig(R.layout.fragment_home);
        dataBindingConfig.addBindingParam(BR.homeViewModel, homeViewModel);
        return dataBindingConfig;
    }

    @Override
    public void initData() {
        homeBeanList = new ArrayList<>();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter(getActivity());
        mBinding.recyclerView.setAdapter(homeAdapter);

        homeViewModel.getBannerLiveData().observe(getViewLifecycleOwner(), new Observer<List<BannerChildBean>>() {
            @Override
            public void onChanged(List<BannerChildBean> bannerChildBeanList) {
                HomeBannerBean homeBannerBean = new HomeBannerBean();
                homeBannerBean.setBannerChildBeanList(bannerChildBeanList);
                homeBeanList.add(homeBannerBean);
                homeAdapter.setHomeData(homeBeanList);
                homeAdapter.notifyDataSetChanged();
            }
        });
        homeViewModel.getHomeBanner();

        homeViewModel.getArticleListLiveData().observe(getViewLifecycleOwner(), new Observer<List<ArticleChildBean>>() {
            @Override
            public void onChanged(List<ArticleChildBean> articleBeans) {
                Log.e(TAG, "article_onChanged: " + articleBeans.size());
                if (articleBeans.size() > 0) {
                    for (int i = 0; i < articleBeans.size(); i++) {
                        HomeArticleBean homeArticleBean = new HomeArticleBean();
                        homeArticleBean.setArticleChildBean(articleBeans.get(i));
                        homeBeanList.add(homeArticleBean);
                    }
                    homeAdapter.setHomeData(homeBeanList);
                    homeAdapter.notifyDataSetChanged();
                }

            }
        });
        //获取首页数据
        homeViewModel.getArticleList();
    }
}