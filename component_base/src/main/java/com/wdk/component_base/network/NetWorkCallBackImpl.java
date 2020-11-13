package com.wdk.component_base.network;

import android.text.TextUtils;

import com.wdk.component_base.common.CodeEnum;
import com.wdk.component_base.data.bean.ResultData;
import com.wdk.component_base.utils.CustomerToast;
import com.wdk.component_base.viewmodel.BaseViewModel;

import static com.wdk.component_base.common.CodeEnum.code_0;
import static com.wdk.component_base.common.CodeEnum.code_cut_1;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/13 10:25 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/13 10:25 PM
 * @LastCheckBy: wdk
 */
public class NetWorkCallBackImpl<T> implements NetWorkCallBackListener<ResultData<T>> {

    BaseViewModel.NetFilter<T> netFilter;

    public NetWorkCallBackImpl(BaseViewModel.NetFilter<T> netFilter) {
        this.netFilter = netFilter;
    }

    @Override
    public void onSuccess(ResultData<T> resultData) {
        if (netFilter == null || netFilter.customerCallBackListener == null) {
            System.out.println("监听对象nullPointException success");
            return;
        }
        if (netFilter.requestData.isShowLoading()) {
            netFilter.baseViewModel.setIsLoading(false);
            netFilter.baseViewModel.getLoadingShowLiveData().postValue(2);
        }

        switch (resultData.getErrorCode()) {
            //成功
            case code_0:
                netFilter.customerCallBackListener.onSuccess(resultData.getData(), resultData);
                break;

            //失败
            case code_cut_1:
                CustomerToast.showToast(resultData.getErrorMsg());
                netFilter.customerCallBackListener.onFailed(resultData);
                break;

            //非已知验证码的失败
            default:
                netFilter.customerCallBackListener.onFailed(resultData);
                break;

        }
    }

    @Override
    public void onFailed(Throwable e) {
        if (netFilter == null || netFilter.customerCallBackListener == null) {
            System.out.println("监听对象nullPointException Failed");
            return;
        }

        //需要loading回调的时候才回调
        if (netFilter.requestData.isShowLoading()) {
            netFilter.baseViewModel.getLoadingShowLiveData().postValue(3);
            netFilter.baseViewModel.setIsLoading(false);
        }

        ResultData<T> resultData = new ResultData<>();
        if (e != null && TextUtils.isEmpty(e.getMessage())) {
            resultData.setErrorMsg(e.getMessage());
        } else {
            resultData.setErrorMsg("");
        }
        resultData.setErrorCode(CodeEnum.code_9901);
        netFilter.customerCallBackListener.onFailed(resultData);
    }

}
