package com.wdk.wanandroid.ui.main;

import com.wdk.component_base.data.bean.ResultData;
import com.wdk.component_base.network.CustomerCallBackListener;
import com.wdk.component_base.viewmodel.BaseViewModel;
import com.wdk.wanandroid.data.bean.home.ArticleBean;
import com.wdk.component_base.network.NetMutableLiveData;
import com.wdk.component_base.network.RequestData;
import com.wdk.wanandroid.data.bean.home.ArticleChildBean;
import com.wdk.wanandroid.ui.home.HomeRepository;

import java.util.List;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/16 8:53 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/16 8:53 AM
 * @LastCheckBy: wdk
 */
public class MainViewModel extends BaseViewModel {

    private static final String TAG = "MainViewModel";

    private HomeRepository homeRepository;

    private NetMutableLiveData<List<ArticleChildBean>> mArticleList;

    public MainViewModel() {
        homeRepository = new HomeRepository();
        //文章列表的
        mArticleList = new NetMutableLiveData<>();
    }

    int current;

    public void getArticleList() {
        RequestData<ArticleBean> requestData = getRequestData();
        requestData.setShowLoading(true)
                .addParams("page", current)
                .addCallBackListener(new CustomerCallBackListener<ArticleBean>() {

                    @Override
                    public void onSuccess(ArticleBean articleBean, ResultData<ArticleBean> resultData) {
                        mArticleList.postValue(articleBean.getDatas());
                    }

                    @Override
                    public void onFailed(ResultData<ArticleBean> resultData) {

                    }
                });

        homeRepository.getArticleList(requestData);
        current++;
    }

    public NetMutableLiveData<List<ArticleChildBean>> getmArticleList() {
        return mArticleList;
    }
}
