package com.wdk.wanandroid.ui.home;

import com.wdk.baselibrary.data.bean.ResultData;
import com.wdk.baselibrary.network.CustomerCallBackListener;
import com.wdk.baselibrary.network.NetMutableLiveData;
import com.wdk.baselibrary.network.RequestData;
import com.wdk.baselibrary.viewmodel.BaseViewModel;
import com.wdk.wanandroid.data.bean.ArticleBean;

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

    private NetMutableLiveData<List<ArticleBean.ArticleChildBean>> mArticleList;

    public HomeViewModel(){
        homeRepository = new HomeRepository();
        //文章列表的
        mArticleList = new NetMutableLiveData<>();
    }

    public NetMutableLiveData<List<ArticleBean.ArticleChildBean>> getmArticleList() {
        return mArticleList;
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
}
