package com.wdk.wanandroid.ui.home;

import com.wdk.baselibrary.data.bean.ResultData;
import com.wdk.baselibrary.network.CustomerCallBackListener;
import com.wdk.baselibrary.network.NetMutableLiveData;
import com.wdk.baselibrary.network.RequestData;
import com.wdk.baselibrary.viewmodel.BaseViewModel;
import com.wdk.wanandroid.data.bean.home.ArticleBean;
import com.wdk.wanandroid.data.bean.home.ArticleChildBean;
import com.wdk.wanandroid.data.bean.home.BannerBean;
import com.wdk.wanandroid.data.bean.home.BannerChildBean;

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
    private NetMutableLiveData<List<ArticleChildBean>> articleListLiveData;

    public HomeViewModel() {
        homeRepository = new HomeRepository();

        //首页轮播图
        bannerLiveData = new NetMutableLiveData<>();
        //文章列表的
        articleListLiveData = new NetMutableLiveData<>();
    }

    public NetMutableLiveData<List<ArticleChildBean>> getArticleListLiveData() {
        return articleListLiveData;
    }

    public NetMutableLiveData<List<BannerChildBean>> getBannerLiveData() {
        return bannerLiveData;
    }

    int current;

    public void getArticleList() {
        RequestData<ArticleBean> requestData = getRequestData();
        requestData.setShowLoading(true)
                .addParams("page", current)
                .addCallBackListener(new CustomerCallBackListener<ArticleBean>() {

                    @Override
                    public void onSuccess(ArticleBean articleBean, ResultData<ArticleBean> resultData) {
                        articleListLiveData.postValue(articleBean.getDatas());
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
