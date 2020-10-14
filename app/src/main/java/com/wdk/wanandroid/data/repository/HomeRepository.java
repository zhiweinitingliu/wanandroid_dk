package com.wdk.wanandroid.data.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.wdk.baselibrary.data.bean.ResultData;
import com.wdk.baselibrary.utils.SharedPreferencesUtil;
import com.wdk.wanandroid.api.HomeArticleService;
import com.wdk.wanandroid.constances.Constants;
import com.wdk.wanandroid.data.bean.ArticleBean;
import com.wdk.baselibrary.network.NetMutableLiveData;
import com.wdk.baselibrary.network.NetWorkCallBackListener;
import com.wdk.baselibrary.network.NetWorkManager;
import com.wdk.baselibrary.network.RequestData;
import com.wdk.baselibrary.data.repository.BaseRepository;
import com.wdk.wanandroid.data.bean.RegisterResponseBean;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;

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
     * @param requestData        请求的参数
     */
    public void getArticleList(RequestData<ArticleBean> requestData) {
        HomeArticleService service = NetWorkManager.getInstance().create(HomeArticleService.class);
        Observable<ResultData<ArticleBean>> observable = service.articleList(requestData.getIntParams("page"));
        NetWorkManager.getInstance().getDataFromServer(observable, requestData);
    }

}
