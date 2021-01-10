package com.wdk.mine.ui.register;

import androidx.lifecycle.Observer;

import com.wdk.component_base.data.bean.ResultData;
import com.wdk.component_base.data.constances.MessageEvent;
import com.wdk.component_base.network.CustomerCallBackListener;
import com.wdk.component_base.network.NetMutableLiveData;
import com.wdk.component_base.network.RequestData;
import com.wdk.component_base.viewmodel.BaseViewModel;
import com.wdk.mine.data.bean.LoginResponseBean;
import com.wdk.mine.data.repository.LoginRepository;

import org.greenrobot.eventbus.EventBus;

public class RegisterViewModel extends BaseViewModel {

    private LoginRepository loginRepository;

    public RegisterViewModel() {
        loginRepository = new LoginRepository();
    }

    /**
     * 注册
     */
    public void doRegister(String userName, String password, String rePassword) {
        //获取网络请求数据封装的对象
        RequestData<LoginResponseBean> requestData = getRequestData();
        requestData.setShowLoading(true)
                //向网络请求封装的对象中添加请求需要的参数
                .addParams("username", userName)
                .addParams("password", password)
                .addParams("repassword", rePassword)
                .addCallBackListener(new CustomerCallBackListener<LoginResponseBean>() {

                    @Override
                    public void onSuccess(LoginResponseBean loginResponseBean, ResultData<LoginResponseBean> resultData) {
                        EventBus.getDefault().post(new MessageEvent(MessageEvent.login_success));
                    }

                    @Override
                    public void onFailed(ResultData<LoginResponseBean> resultData) {

                    }

                });


        //通过repository发起网络请求
        loginRepository.doRegister(requestData);
    }
}
