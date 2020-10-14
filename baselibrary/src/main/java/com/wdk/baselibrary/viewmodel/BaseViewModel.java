package com.wdk.baselibrary.viewmodel;

import androidx.annotation.RequiresApi;
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

    public MutableLiveData<Integer> loadingShowLiveData;

    public MutableLiveData<Integer> getLoadingShowLiveData() {
        return loadingShowLiveData;
    }

    public void setLoadingShowLiveData(MutableLiveData<Integer> loadingShowLiveData) {
        this.loadingShowLiveData = loadingShowLiveData;
    }


    /**
     * 获取到网络请求使用到的封装类
     *
     * @return requestData
     */
    public <T> RequestData<T> getRequestData(CustomerCallBackListener<T> customerCallBackListener) {
        //获取requestData对象
        RequestData<T> requestData = new RequestData<>();

        requestData.setLoadingShowLiveData(loadingShowLiveData);

        //网络请求用到的Observer
        NetWorkObserver<T> netWorkObserver = new NetWorkObserver<>();
        requestData.setNetWorkObserver(netWorkObserver);

        //监听返回的数据进行拦截
        NetFilter<T> netFilter = new NetFilter<>(this, requestData, customerCallBackListener);

        //服务器数据返回的回调
        NetWorkCallBackImpl<T> netWorkCallBackImpl = new NetWorkCallBackImpl<>(netFilter);
        requestData.setHttpCallBack(netWorkCallBackImpl);

        return requestData;
    }

    public static class NetFilter<T> {
        public BaseViewModel baseViewModel;
        public RequestData<T> requestData;
        public CustomerCallBackListener<T> customerCallBackListener;

        public NetFilter(BaseViewModel baseViewModel, RequestData<T> requestData, CustomerCallBackListener<T> customerCallBackListener) {
            this.baseViewModel = baseViewModel;
            this.requestData = requestData;
            this.customerCallBackListener = customerCallBackListener;
        }

    }


}
