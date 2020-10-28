package com.wdk.wanandroid.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wdk.wanandroid.R;
import com.wdk.wanandroid.data.bean.ArticleBean;
import com.wdk.wanandroid.databinding.FragmentHomeBinding;

import java.util.List;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private HomeViewModel homeViewModel;
    FragmentHomeBinding mBinding;
    /**
     * 1、 开始加载loading
     * 2、加载完成 loading
     */
    public MutableLiveData<Integer> loadingShowLiveData = new MutableLiveData<>();


    private ArticleListAdapter articleListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.setLoadingShowLiveData(loadingShowLiveData);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mBinding = FragmentHomeBinding.bind(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        articleListAdapter = new ArticleListAdapter(getActivity());
        mBinding.recyclerView.setAdapter(articleListAdapter);
        //获取首页数据
        homeViewModel.getArticleList();

        homeViewModel.getmArticleList().observe(getViewLifecycleOwner(), new Observer<List<ArticleBean.ArticleChildBean>>() {
            @Override
            public void onChanged(List<ArticleBean.ArticleChildBean> articleBeans) {
                Log.e(TAG, "article_onChanged: " + articleBeans.size());
                articleListAdapter.getItems().addAll(articleBeans);
                articleListAdapter.notifyDataSetChanged();
            }
        });
    }
}