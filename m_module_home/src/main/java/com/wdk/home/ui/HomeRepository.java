package com.wdk.home.ui;

import androidx.lifecycle.MutableLiveData;

import com.wdk.component_base.common.RefreshLoadMoreEnum;
import com.wdk.component_base.data.bean.ResultData;
import com.wdk.component_base.data.repository.BaseRepository;
import com.wdk.component_base.network.CustomerCallBackListener;
import com.wdk.component_base.network.NetMutableLiveData;
import com.wdk.component_base.network.NetWorkManager;
import com.wdk.component_base.network.RequestData;
import com.wdk.home.api.HomeArticleService;
import com.wdk.home.api.HomeService;
import com.wdk.home.bean.home.ArticleBean;
import com.wdk.home.bean.home.ArticleChildBean;
import com.wdk.home.bean.home.BannerChildBean;
import com.wdk.home.bean.home.HomeArticleBean;
import com.wdk.home.bean.home.HomeBannerBean;
import com.wdk.home.bean.home.HomeBaseBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description : 获取数据的仓库，用于给viewmodel提供数据
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/16 10:05 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/16 10:05 PM
 * @LastCheckBy: wdk
 */
public class HomeRepository extends BaseRepository {

    private static final String TAG = "HomeRepository";

    List<HomeBaseBean> homeBeanList;

    /**
     * 获取文章列表
     *
     * @param requestData 请求的参数
     */
    public void getHomeBanner(RequestData<List<BannerChildBean>> requestData, NetMutableLiveData<List<HomeBaseBean>> homeBeanListLiveData, MutableLiveData<Boolean> requestListener) {
        requestData.setShowLoading(true)
                .addCallBackListener(new CustomerCallBackListener<List<BannerChildBean>>() {
                    @Override
                    public void onSuccess(List<BannerChildBean> bannerChildBeanList, ResultData<List<BannerChildBean>> resultData) {
                        if (homeBeanList == null) {
                            homeBeanList = new ArrayList<>();
                        } else {
                            homeBeanList.clear();
                        }

                        HomeBannerBean homeBannerBean = new HomeBannerBean();
                        homeBannerBean.setBannerChildBeanList(bannerChildBeanList);
                        homeBeanList.add(homeBannerBean);
                        homeBeanListLiveData.postValue(homeBeanList);
                        requestListener.postValue(true);


                    }

                    @Override
                    public void onFailed(ResultData<List<BannerChildBean>> resultData) {

                    }
                });

        HomeService service = NetWorkManager.getInstance().create(HomeService.class);
        Observable<ResultData<List<BannerChildBean>>> observable = service.getHomeBanner();
        NetWorkManager.getInstance().getDataFromServer(observable, requestData);
    }

    int current = 1;
    boolean hasMore = true;

    /**
     * 获取文章列表
     *
     * @param requestData 请求的参数
     */
    public void getArticleList(RequestData<ArticleBean> requestData, NetMutableLiveData<List<HomeBaseBean>> homeBeanListLiveData, RefreshLoadMoreEnum refreshLoadMoreEnum) {
        if (refreshLoadMoreEnum == RefreshLoadMoreEnum.FIRST) {
            current = 1;
        }

        requestData.setShowLoading(true)
                .addParams("page", current)
                .addCallBackListener(new CustomerCallBackListener<ArticleBean>() {

                    @Override
                    public void onSuccess(ArticleBean articleBean, ResultData<ArticleBean> resultData) {
                        List<ArticleChildBean> datas = articleBean.getDatas();
                        if (datas.size() > 0) {
                            for (int i = 0; i < datas.size(); i++) {
                                HomeArticleBean homeArticleBean = new HomeArticleBean();
                                homeArticleBean.setArticleChildBean(datas.get(i));
                                homeBeanList.add(homeArticleBean);
                            }
                        }
                        homeBeanListLiveData.postValue(homeBeanList);
                        current++;
                        hasMore = current <= articleBean.getPageCount();
                    }

                    @Override
                    public void onFailed(ResultData<ArticleBean> resultData) {

                    }
                });

        HomeArticleService service = NetWorkManager.getInstance().create(HomeArticleService.class);
        Observable<ResultData<ArticleBean>> observable = service.articleList(requestData.getIntParams("page"));
        NetWorkManager.getInstance().getDataFromServer(observable, requestData);
    }

}
