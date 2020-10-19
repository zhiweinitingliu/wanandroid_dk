package com.wdk.baselibrary.viewmodel;

import androidx.annotation.RequiresApi;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wdk.baselibrary.data.bean.ResultData;
import com.wdk.baselibrary.network.BaseRequestData;
import com.wdk.baselibrary.network.CustomerCallBackListener;
import com.wdk.baselibrary.network.NetWorkCallBackImpl;
import com.wdk.baselibrary.network.NetWorkCallBackListener;
import com.wdk.baselibrary.network.NetWorkObserver;
import com.wdk.baselibrary.network.RequestData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/17 2:21 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/17 2:21 PM
 * @LastCheckBy: wdk
 */
public class BaseViewModel extends ViewModel {

    //loading方法1，回调到具体控件的loading
    ObservableBoolean mIsLoading = new ObservableBoolean();

    //loading方法2：1开始网络请求 2网络请求成功  3网络请求失败
    public MutableLiveData<Integer> loadingShowLiveData;

    public MutableLiveData<Integer> getLoadingShowLiveData() {
        return loadingShowLiveData;
    }

    public void setLoadingShowLiveData(MutableLiveData<Integer> loadingShowLiveData) {
        this.loadingShowLiveData = loadingShowLiveData;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }


    /**
     * 获取到网络请求使用到的封装类
     *
     * @return requestData
     */
    public <T> RequestData<T> getRequestData() {
        //获取requestData对象
        RequestData<T> requestData = new RequestData<>();

        requestData.setLoadingShowLiveData(loadingShowLiveData);

        //网络请求用到的Observer
        NetWorkObserver<T> netWorkObserver = new NetWorkObserver<>();
        requestData.setNetWorkObserver(netWorkObserver);

        //监听返回的数据进行拦截
        requestData.setNetFilter(new NetFilter<T>(this, requestData));
        return requestData;
    }

    public static class NetFilter<T> {
        public BaseViewModel baseViewModel;
        public RequestData<T> requestData;
        public CustomerCallBackListener<T> customerCallBackListener;

        public NetFilter(BaseViewModel baseViewModel, RequestData<T> requestData) {
            this.baseViewModel = baseViewModel;
            this.requestData = requestData;
        }

        public void setCustomerCallBackListener(CustomerCallBackListener<T> customerCallBackListener) {
            this.customerCallBackListener = customerCallBackListener;
        }
    }


}
