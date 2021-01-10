package com.wdk.mine.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.wdk.component_base.data.bean.ResultData;
import com.wdk.component_base.data.constances.MessageEvent;
import com.wdk.component_base.network.CustomerCallBackListener;
import com.wdk.component_base.network.NetMutableLiveData;
import com.wdk.component_base.network.RequestData;
import com.wdk.component_base.viewmodel.BaseViewModel;
import com.wdk.mine.data.bean.LoginResponseBean;
import com.wdk.mine.data.repository.LoginRepository;
import com.wdk.mine.utils.UserInfoUtil;

import org.greenrobot.eventbus.EventBus;

public class LoginViewModel extends BaseViewModel {

    private LoginRepository loginRepository;

    public LoginViewModel() {
        loginRepository = new LoginRepository();
    }

    public void doLogin(String username, String password) {
        RequestData<LoginResponseBean> requestData = getRequestData();
        requestData.setShowLoading(true)
                .addParams("username", username)
                .addParams("password", password)
                .addCallBackListener(new CustomerCallBackListener<LoginResponseBean>() {

                    @Override
                    public void onSuccess(LoginResponseBean loginResponseBean, ResultData<LoginResponseBean> resultData) {
                        UserInfoUtil.getInstance().saveLoginInfo(loginResponseBean);
                        EventBus.getDefault().post(new MessageEvent(MessageEvent.login_success));
                    }

                    @Override
                    public void onFailed(ResultData<LoginResponseBean> resultData) {
//                setIsLoading(false);
                    }

                });
        loginRepository.doLogin(requestData);
    }
}
