package com.wdk.home.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.wdk.component_base.common.RefreshLoadMoreEnum;
import com.wdk.component_base.network.NetMutableLiveData;
import com.wdk.component_base.network.RequestData;
import com.wdk.component_base.viewmodel.BaseViewModel;
import com.wdk.home.bean.home.ArticleBean;
import com.wdk.home.bean.home.BannerChildBean;
import com.wdk.home.bean.home.HomeBaseBean;

import java.util.List;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/28 9:25 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/28 9:25 PM
 * @LastCheckBy: wdk
 */
public class HomeViewModel extends BaseViewModel {

    private HomeRepository homeRepository;
    private NetMutableLiveData<List<HomeBaseBean>> homeBeanListLiveData;

    public NetMutableLiveData<List<HomeBaseBean>> getHomeBeanListLiveData() {
        return homeBeanListLiveData;
    }

    public HomeViewModel() {
        homeRepository = new HomeRepository();
        homeBeanListLiveData = new NetMutableLiveData<>();
    }

    public void getArticleList(RefreshLoadMoreEnum refreshLoadMoreEnum) {
        RequestData<ArticleBean> requestData = getRequestData();
        homeRepository.getArticleList(requestData, homeBeanListLiveData, refreshLoadMoreEnum);
    }

    public void getHomeBanner() {
        MutableLiveData<Boolean> requestListener = new MutableLiveData<>();
        requestListener.observeForever(new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    getArticleList(RefreshLoadMoreEnum.FIRST);
                }
            }
        });

        RequestData<List<BannerChildBean>> requestData = getRequestData();
        homeRepository.getHomeBanner(requestData, homeBeanListLiveData, requestListener);
        homeRepository.hasMore = true;
    }

    /**
     * 是否还有下一页
     *
     * @return
     */
    public boolean hasMore() {
        return homeRepository.hasMore;
    }
}
