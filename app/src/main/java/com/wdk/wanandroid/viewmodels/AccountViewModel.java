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
//        loginRequestLiveData.postValue(1);
        setIsLoading(true);
        RequestData<LoginResponseBean> requestData = getRequestData(new CustomerCallBackListener<LoginResponseBean>() {

            @Override
            public void onSuccess(LoginResponseBean loginResponseBean, ResultData<LoginResponseBean> resultData) {
                loginResponseLiveData.setValue(loginResponseBean);
            }

            @Override
            public void onFailed(ResultData<LoginResponseBean> resultData) {
                setIsLoading(false);
            }

        });
        requestData.addParams("username", username)
                .addParams("password", password);
        loginRepository.doLogin(requestData);
    }

    /**
     * 注册
     */
    public void doRegister(String userName, String password, String rePassword) {
//        registerRequestLiveData.postValue(1);
        setIsLoading(true);
        //获取网络请求数据封装的对象
        RequestData<RegisterResponseBean> requestData = getRequestData(new CustomerCallBackListener<RegisterResponseBean>() {

            @Override
            public void onSuccess(RegisterResponseBean registerResponseBean, ResultData<RegisterResponseBean> resultData) {

            }

            @Override
            public void onFailed(ResultData<RegisterResponseBean> resultData) {
                setIsLoading(false);
            }

        });
        //向网络请求封装的对象中添加请求需要的参数
        requestData.addParams("username", userName)
                .addParams("password", password)
                .addParams("repassword", rePassword);
        //设置网络请求


//        requestData.setHttpCallBack(new NetWorkCallBackListener<RegisterResponseBean>() {
//            @Override
//            public void onSuccess(RegisterResponseBean registerResponseBean) {
//                try {
//                    SharedPreferencesUtil.getInstance().putStringValue(Constants.user_info_json, new Gson().toJson(registerResponseBean));
////                    netMutableLiveData.postValue(registerResponseBean);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailed(String error) {
//                Log.e(TAG, "onFailed: " + error);
//                registerRequestLiveData.postValue(2);
//
//            }
//        } );
        //通过repository发起网络请求
        loginRepository.doRegister(requestData);
    }

    private MutableLiveData<Integer> loginRequestLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> registerRequestLiveData = new MutableLiveData<>();

    public MutableLiveData<Integer> getLoginRequestLiveData() {
        return loginRequestLiveData;
    }

    public MutableLiveData<Integer> getRegisterRequestLiveData() {
        return registerRequestLiveData;
    }

//    /**
//     * 接口失败的回调
//     *
//     * @param what  接口请求的标记 自己定义
//     * @param error 失败信息
//     */
//    @Override
//    public void onFailed(int what, String error) {
//
//        switch (what) {
//            //登录失败
//            case 101:
//                loginRequestLiveData.postValue(2);
//                break;
//            //注册
//            case 102:
//                registerRequestLiveData.postValue(2);
//                break;
//        }
//        CustomerToast.showToast(error);
//    }


}
