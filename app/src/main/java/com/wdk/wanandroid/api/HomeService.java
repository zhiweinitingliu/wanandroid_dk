package com.wdk.wanandroid.api;

import com.wdk.baselibrary.data.bean.ResultData;
import com.wdk.wanandroid.data.bean.home.ArticleBean;
import com.wdk.wanandroid.data.bean.home.BannerBean;
import com.wdk.wanandroid.data.bean.home.BannerChildBean;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/16 8:49 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/16 8:49 AM
 * @LastCheckBy: wdk
 */
public interface HomeService {

//    https://www.wanandroid.com/article/list/1/json

    @GET("banner/json")
    Observable<ResultData<List<BannerChildBean>>> getHomeBanner();


}
