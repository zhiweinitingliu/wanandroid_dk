package com.wdk.wanandroid.ui.home;

import com.wdk.component_base.data.bean.ResultData;
import com.wdk.wanandroid.api.HomeArticleService;
import com.wdk.wanandroid.api.HomeService;
import com.wdk.wanandroid.data.bean.home.ArticleBean;
import com.wdk.component_base.network.NetWorkManager;
import com.wdk.component_base.network.RequestData;
import com.wdk.component_base.data.repository.BaseRepository;
import com.wdk.wanandroid.data.bean.home.BannerChildBean;

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

    /**
     * 获取文章列表
     *
     * @param requestData 请求的参数
     */
    public void getArticleList(RequestData<ArticleBean> requestData) {
        HomeArticleService service = NetWorkManager.getInstance().create(HomeArticleService.class);
        Observable<ResultData<ArticleBean>> observable = service.articleList(requestData.getIntParams("page"));
        NetWorkManager.getInstance().getDataFromServer(observable, requestData);
    }

    /**
     * 获取文章列表
     *
     * @param requestData 请求的参数
     */
    public void getHomeBanner(RequestData<List<BannerChildBean>> requestData) {
        HomeService service = NetWorkManager.getInstance().create(HomeService.class);
        Observable<ResultData<List<BannerChildBean>>> observable = service.getHomeBanner();
        NetWorkManager.getInstance().getDataFromServer(observable, requestData);
    }

}
