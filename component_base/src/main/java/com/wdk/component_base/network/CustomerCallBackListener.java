package com.wdk.component_base.network;

import com.wdk.component_base.data.bean.ResultData;

/**
 * Description :网络请求回调给业务端的监听
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/13 10:10 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/13 10:10 PM
 * @LastCheckBy: wdk
 */
public interface CustomerCallBackListener<T> {
    void onSuccess(T t, ResultData<T> resultData);

    void onFailed(ResultData<T> resultData);

}
