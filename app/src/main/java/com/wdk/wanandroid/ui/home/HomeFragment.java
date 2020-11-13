package com.wdk.wanandroid.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wdk.component_base.basepage.BaseFragment;
import com.wdk.component_base.basepage.DataBindingConfig;
import com.wdk.component_base.common.RefreshLoadMoreEnum;
import com.wdk.wanandroid.BR;
import com.wdk.wanandroid.R;
import com.wdk.wanandroid.data.bean.home.ArticleBean;
import com.wdk.wanandroid.data.bean.home.ArticleChildBean;
import com.wdk.wanandroid.data.bean.home.BannerChildBean;
import com.wdk.wanandroid.data.bean.home.HomeArticleBean;
import com.wdk.wanandroid.data.bean.home.HomeBannerBean;
import com.wdk.wanandroid.data.bean.home.HomeBaseBean;
import com.wdk.wanandroid.databinding.FragmentHomeBinding;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

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
        mBinding.recyclerView.useDefaultLoadMore(); // 使用默认的加载更多的View
        mBinding.recyclerView.setAdapter(homeAdapter);

        mBinding.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                homeBeanList.clear();
                homeViewModel.getHomeBanner();
            }
        });

        mBinding.recyclerView.setLoadMoreListener(new SwipeRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                homeViewModel.getArticleList(RefreshLoadMoreEnum.LOAD_MORE);
            }
        });

        homeViewModel.getBannerLiveData().observe(getViewLifecycleOwner(), new Observer<List<BannerChildBean>>() {
            @Override
            public void onChanged(List<BannerChildBean> bannerChildBeanList) {
                HomeBannerBean homeBannerBean = new HomeBannerBean();
                homeBannerBean.setBannerChildBeanList(bannerChildBeanList);
                homeBeanList.add(homeBannerBean);
                homeAdapter.setHomeData(homeBeanList);
                homeAdapter.notifyDataSetChanged();
                //获取首页数据
                homeViewModel.getArticleList(RefreshLoadMoreEnum.FIRST);
                mBinding.smartRefreshLayout.finishRefresh();

            }
        });
        homeViewModel.getHomeBanner();

        homeViewModel.getArticleListLiveData().observe(getViewLifecycleOwner(), new Observer<ArticleBean>() {
            @Override
            public void onChanged(ArticleBean articleBeans) {
                List<ArticleChildBean> datas = articleBeans.getDatas();

                int startPosition = homeBeanList.size();

                if (datas.size() > 0) {
                    for (int i = 0; i < datas.size(); i++) {
                        HomeArticleBean homeArticleBean = new HomeArticleBean();
                        homeArticleBean.setArticleChildBean(datas.get(i));
                        homeBeanList.add(homeArticleBean);
                    }
                    homeAdapter.setHomeData(homeBeanList);
                    homeAdapter.notifyItemRangeChanged(startPosition, articleBeans.getDatas().size());
                    mBinding.smartRefreshLayout.finishRefresh();
                    mBinding.recyclerView.loadMoreFinish(homeBeanList.size() <= 0, homeViewModel.current <= articleBeans.getPageCount());
                }

            }
        });
    }
}