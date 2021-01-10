package com.wdk.home.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wdk.component_base.basepage.BaseFragment;
import com.wdk.component_base.basepage.DataBindingConfig;
import com.wdk.component_base.common.RefreshLoadMoreEnum;
import com.wdk.home.BR;
import com.wdk.home.R;
import com.wdk.home.bean.home.HomeBaseBean;
import com.wdk.home.databinding.FragmentHomeBinding;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.List;


public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private HomeViewModel homeViewModel;
    private HomeAdapter homeAdapter;

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
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter(getActivity());
        mBinding.recyclerView.useDefaultLoadMore(); // 使用默认的加载更多的View
        mBinding.recyclerView.setAdapter(homeAdapter);

        mBinding.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                homeViewModel.getHomeBanner();
            }
        });

        mBinding.recyclerView.setLoadMoreListener(new SwipeRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                homeViewModel.getArticleList(RefreshLoadMoreEnum.LOAD_MORE);
            }
        });

        homeViewModel.getHomeBeanListLiveData().observe(getViewLifecycleOwner(), new Observer<List<HomeBaseBean>>() {
            @Override
            public void onChanged(List<HomeBaseBean> homeBaseBeans) {
                homeAdapter.setData(homeBaseBeans);
                homeAdapter.notifyDataSetChanged();

                mBinding.smartRefreshLayout.finishRefresh();
                mBinding.recyclerView.loadMoreFinish(homeBaseBeans.size() <= 0, homeViewModel.hasMore());
            }
        });
        homeViewModel.getHomeBanner();
    }
}