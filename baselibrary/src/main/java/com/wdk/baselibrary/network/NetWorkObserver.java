package com.wdk.baselibrary.network;

import com.wdk.baselibrary.data.bean.ResultData;
import com.wdk.baselibrary.data.bean.ServiceDataBean;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/21 10:16 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/21 10:16 PM
 * @LastCheckBy: wdk
 */
public class NetWorkObserver<T> extends BaseRequestData.NetWorkObserver<T, ResultData<T>> {

//    private RequestData requestData;
////    private NetWorkCallBackListener<T> netWorkCallBackListener;
//
//    public NetWorkObserver(RequestData requestData) {
//        this.requestData = requestData;
////        this.netWorkCallBackListener = netWorkCallBackListener;
//    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (mRequestData != null) {
            mRequestData.requestStart();
        }
    }

    @Override
    public void onNext(@NonNull ResultData<T> resultData) {
        mRequestData.getNetWorkCallBackImpl().onSuccess(resultData);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        mRequestData.getNetWorkCallBackImpl().onFailed( e);
    }

    @Override
    public void onComplete() {
        mRequestData.requestComplete();
    }
}
