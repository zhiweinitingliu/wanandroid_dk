package com.wdk.wanandroid.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.wdk.baselibrary.data.bean.ResultData;
import com.wdk.baselibrary.network.CustomerCallBackListener;
import com.wdk.baselibrary.network.NetMutableLiveData;
import com.wdk.baselibrary.network.RequestData;
import com.wdk.baselibrary.viewmodel.BaseViewModel;
import com.wdk.wanandroid.constances.MessageEvent;
import com.wdk.wanandroid.data.bean.LoginResponseBean;
import com.wdk.wanandroid.data.bean.RegisterResponseBean;
import com.wdk.wanandroid.data.repository.LoginRepository;

import org.greenrobot.eventbus.EventBus;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 10:05 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 10:05 AM
 * @LastCheckBy: wdk
 */
public class AccountViewModel extends BaseViewModel {
    private static final String TAG = "LoginViewModel";

    private LoginRepository loginRepository;
    private NetMutableLiveData<LoginResponseBean> loginResponseLiveData;
    private NetMutableLiveData<RegisterResponseBean> registerResponseLiveData;
    private MutableLiveData<Boolean> loginResultLiveData;
    private MutableLiveData<Boolean> registerResultLiveData;

    public MutableLiveData<Boolean> getLoginResultLiveData() {
        return loginResultLiveData;
    }

    public MutableLiveData<Boolean> getRegisterResultLiveData() {
        return registerResultLiveData;
    }

    public AccountViewModel() {
        loginRepository = new LoginRepository();
        loginResultLiveData = new MutableLiveData<>();
        loginResponseLiveData = new NetMutableLiveData<>();
        loginResponseLiveData.observeForever(new Observer<LoginResponseBean>() {
            @Override
            public void onChanged(LoginResponseBean loginResponseBean) {
                EventBus.getDefault().post(new MessageEvent(MessageEvent.login_success));
//                loginResultLiveData.postValue(true);
            }
        });

        registerResultLiveData = new MutableLiveData<>();
        registerResponseLiveData = new NetMutableLiveData<>();
        registerResponseLiveData.observeForever(new Observer<RegisterResponseBean>() {
            @Override
            public void onChanged(RegisterResponseBean registerResponseBean) {
                EventBus.getDefault().post(new MessageEvent(MessageEvent.login_success));
//                registerResultLiveData.postValue(true);
            }
        });
    }

    public void doLogin(String username, String password) {
        RequestData<LoginResponseBean> requestData = getRequestData();
        requestData.setShowLoading(true)
                .addParams("username", username)
                .addParams("password", password)
                .addCallBackListener(new CustomerCallBackListener<LoginResponseBean>() {

                    @Override
                    public void onSuccess(LoginResponseBean loginResponseBean, ResultData<LoginResponseBean> resultData) {
                        loginResponseLiveData.setValue(loginResponseBean);
                    }

                    @Override
                    public void onFailed(ResultData<LoginResponseBean> resultData) {
//                setIsLoading(false);
                    }

                });
        loginRepository.doLogin(requestData);
    }

    /**
     * 注册
     */
    public void doRegister(String userName, String password, String rePassword) {
        //获取网络请求数据封装的对象
        RequestData<RegisterResponseBean> requestData = getRequestData();
        requestData.setShowLoading(true)
                //向网络请求封装的对象中添加请求需要的参数
                .addParams("username", userName)
                .addParams("password", password)
                .addParams("repassword", rePassword)
                .addCallBackListener(new CustomerCallBackListener<RegisterResponseBean>() {

                    @Override
                    public void onSuccess(RegisterResponseBean registerResponseBean, ResultData<RegisterResponseBean> resultData) {
                        registerResponseLiveData.setValue(registerResponseBean);
                    }

                    @Override
                    public void onFailed(ResultData<RegisterResponseBean> resultData) {

                    }

                });


        //通过repository发起网络请求
        loginRepository.doRegister(requestData);
    }

}
