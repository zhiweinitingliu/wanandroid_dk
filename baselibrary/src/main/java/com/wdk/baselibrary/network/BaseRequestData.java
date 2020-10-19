package com.wdk.baselibrary.network;

import androidx.lifecycle.MutableLiveData;

import com.wdk.baselibrary.data.bean.ResultData;
import com.wdk.baselibrary.viewmodel.BaseViewModel;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/18 2:25 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/18 2:25 PM
 * @LastCheckBy: wdk
 */
public class BaseRequestData<T> {
    //loading使用到的liveData
    private MutableLiveData<Integer> loadingShowLiveData;
    //网络请求使用到的参数
    private Map<String, Object> mParams = new HashMap<>();

    private NetWorkCallBackImpl<T> netWorkCallBackImpl;

    public MutableLiveData<Integer> getLoadingShowLiveData() {
        return loadingShowLiveData;
    }

    public BaseRequestData<T> setLoadingShowLiveData(MutableLiveData<Integer> loadingShowLiveData) {
        this.loadingShowLiveData = loadingShowLiveData;
        return this;
    }

    public BaseRequestData<T> setAllParams(Map<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public BaseRequestData<T> addParams(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public Map<String, Object> getMParams() {
        return mParams;
    }

    public int getIntParams(String key) {
        if (mParams != null && mParams.containsKey(key)) {
            Object o = mParams.get(key);
            if (o instanceof Integer) {
                return (int) o;
            }
        }
        return -1;
    }

    public String getStrParams(String key) {
        if (mParams != null && mParams.containsKey(key)) {
            Object o = mParams.get(key);
            if (o instanceof String) {
                return (String) o;
            }
        }
        return "";
    }

    //监听返回的数据进行拦截
    private BaseViewModel.NetFilter<T> netFilter;

    public BaseRequestData<T> setNetFilter(BaseViewModel.NetFilter<T> netFilter) {
        this.netFilter = netFilter;
        return this;
    }

    public BaseViewModel.NetFilter<T> getNetFilter() {
        return netFilter;
    }

    public BaseRequestData<T> addCallBackListener(CustomerCallBackListener<T> customerCallBackListener) {
        netFilter.setCustomerCallBackListener(customerCallBackListener);
        //服务器数据返回的回调
        NetWorkCallBackImpl<T> netWorkCallBackImpl = new NetWorkCallBackImpl<>(netFilter);
        setHttpCallBack(netWorkCallBackImpl);
        return this;
    }

    public BaseRequestData<T> setHttpCallBack(NetWorkCallBackImpl<T> netWorkCallBackImpl) {
        this.netWorkCallBackImpl = netWorkCallBackImpl;
        return this;
    }

    public NetWorkCallBackImpl<T> getNetWorkCallBackImpl() {
        return netWorkCallBackImpl;
    }

//    public BaseRequestData<T> setNetWorkCallBackImpl(NetWorkCallBackImpl<T> netWorkCallBackImpl) {
//        this.netWorkCallBackImpl = netWorkCallBackImpl;
//        return this;
//    }

    public abstract static class NetWorkObserver<T, R extends ResultData<?>> implements Observer<R> {

        public RequestData<T> mRequestData;

        public void setRequestData(RequestData<T> requestData) {
            mRequestData = requestData;
        }
////    private NetWorkCallBackListener<T> netWorkCallBackListener;
//
//        public NetWorkObserver(RequestData requestData) {
//            this.requestData = requestData;
////        this.netWorkCallBackListener = netWorkCallBackListener;
//        }

//        @Override
//        public void onSubscribe(@NonNull Disposable d) {
//            if (requestData != null) {
//                requestData.requestStart();
//            }
//
//        }
//
//        @Override
//        public void onNext(@NonNull T t) {
//            requestData.getNetWorkCallBackListener().onSuccess(t);
//        }
//
//        @Override
//        public void onError(@NonNull Throwable e) {
//            requestData.getNetWorkCallBackListener().onFailed(e.toString());
//        }
//
//        @Override
//        public void onComplete() {
//            requestData.requestComplete();
//        }
    }

}
