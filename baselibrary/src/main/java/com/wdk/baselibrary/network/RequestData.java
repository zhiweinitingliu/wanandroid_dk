package com.wdk.baselibrary.network;

import com.wdk.baselibrary.data.bean.ResultData;

/**
 * Description : 请求携带的参数类
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/18 10:39 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/18 10:39 AM
 * @LastCheckBy: wdk
 */
public class RequestData<T> extends BaseRequestData<T> {

    private boolean isShowLoading;


//    public void requestStart() {
//        if (getLoadingShowLiveData() != null) {
//            getLoadingShowLiveData().postValue(1);
//        }
//    }
//
//    public void requestComplete() {
//        if (getLoadingShowLiveData() != null) {
//            getLoadingShowLiveData().postValue(2);
//        }
//    }


    private NetWorkObserver<T, ResultData<T>> netWorkObserver;

    public NetWorkObserver<T, ResultData<T>> getNetWorkObserver() {
        return netWorkObserver;
    }

    public void setNetWorkObserver(NetWorkObserver<T, ResultData<T>> netWorkObserver) {
        this.netWorkObserver = netWorkObserver;
        netWorkObserver.setRequestData(this);
    }

    public boolean isShowLoading() {
        return isShowLoading;
    }

    public RequestData<T> setShowLoading(boolean showLoading) {
        isShowLoading = showLoading;
        if (isShowLoading) {
            getNetFilter().baseViewModel.setIsLoading(isShowLoading);
            getLoadingShowLiveData().postValue(1);
        }
        return this;
    }
}
