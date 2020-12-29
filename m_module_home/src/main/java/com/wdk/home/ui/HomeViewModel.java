package com.wdk.home.ui;

import com.wdk.component_base.common.RefreshLoadMoreEnum;
import com.wdk.component_base.data.bean.ResultData;
import com.wdk.component_base.network.CustomerCallBackListener;
import com.wdk.component_base.network.NetMutableLiveData;
import com.wdk.component_base.network.RequestData;
import com.wdk.component_base.viewmodel.BaseViewModel;
import com.wdk.home.bean.home.ArticleBean;
import com.wdk.home.bean.home.BannerChildBean;

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

    private NetMutableLiveData<List<BannerChildBean>> bannerLiveData;

    //首页文章列表
    private NetMutableLiveData<ArticleBean> articleListLiveData;

    public HomeViewModel() {
        homeRepository = new HomeRepository();

        //首页轮播图
        bannerLiveData = new NetMutableLiveData<>();
        //文章列表的
        articleListLiveData = new NetMutableLiveData<>();
    }

    public NetMutableLiveData<ArticleBean> getArticleListLiveData() {
        return articleListLiveData;
    }

    public NetMutableLiveData<List<BannerChildBean>> getBannerLiveData() {
        return bannerLiveData;
    }

    int current;

    public void getArticleList(RefreshLoadMoreEnum refreshLoadMoreEnum) {
        if (refreshLoadMoreEnum == RefreshLoadMoreEnum.FIRST) {
            current = 1;
        }

        RequestData<ArticleBean> requestData = getRequestData();
        requestData.setShowLoading(true)
                .addParams("page", current)
                .addCallBackListener(new CustomerCallBackListener<ArticleBean>() {

                    @Override
                    public void onSuccess(ArticleBean articleBean, ResultData<ArticleBean> resultData) {
                        articleListLiveData.postValue(articleBean);
                    }

                    @Override
                    public void onFailed(ResultData<ArticleBean> resultData) {

                    }
                });

        homeRepository.getArticleList(requestData);
        current++;
    }

    public void getHomeBanner() {
        RequestData<List<BannerChildBean>> requestData = getRequestData();
        requestData.setShowLoading(true)
                .addCallBackListener(new CustomerCallBackListener<List<BannerChildBean>>() {
                    @Override
                    public void onSuccess(List<BannerChildBean> bannerChildBeanList, ResultData<List<BannerChildBean>> resultData) {
                        bannerLiveData.postValue(bannerChildBeanList);
                    }

                    @Override
                    public void onFailed(ResultData<List<BannerChildBean>> resultData) {

                    }
                });

        homeRepository.getHomeBanner(requestData);
        current++;
    }
}
