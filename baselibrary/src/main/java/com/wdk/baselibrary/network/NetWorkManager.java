package com.wdk.baselibrary.network;

import android.os.Handler;
import android.os.Looper;

import com.wdk.baselibrary.data.bean.ResultData;
import com.wdk.baselibrary.network.error.ResponseCodeException;
import com.wdk.baselibrary.network.jsonconverter.NetGsonConverterFactory;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/21 10:04 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/21 10:04 PM
 * @LastCheckBy: wdk
 */
public class NetWorkManager {
    private static final String TAG = "NetWorkManager";

    private NetWorkManager() {

    }

    public static NetWorkManager getInstance() {
        return InnerClass.netWorkManager;
    }

    private static class InnerClass {
        private static NetWorkManager netWorkManager = new NetWorkManager();
    }

    public <T> void getDataFromServer(Observable<ResultData<T>> observable, RequestData<T> requestData) {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, ResultData<T>>() {
                    @Override
                    public ResultData<T> apply(Throwable throwable) throws Throwable {
                        if (throwable instanceof ResponseCodeException) {
                            ResponseCodeException responseCodeException = (ResponseCodeException) throwable;
                            String errorMessage = responseCodeException.getErrorMsg();
                            Handler mainThread = new Handler(Looper.getMainLooper());
                            mainThread.post(new Runnable() {
                                @Override
                                public void run() {
                                    requestData.getNetWorkCallBackImpl().onFailed(new Throwable(errorMessage));
                                }
                            });

                        }
                        return null;
                    }
                })
                .subscribe(requestData.getNetWorkObserver());
    }

    public Retrofit getRetrofit() {
        ServiceInterceptor serviceInterceptor = new ServiceInterceptor();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(serviceInterceptor);

        return new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(NetGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl("https://www.wanandroid.com/")
                .build();

    }

    public <T> T create(Class<T> service) {
        return getRetrofit().create(service);
    }


}
