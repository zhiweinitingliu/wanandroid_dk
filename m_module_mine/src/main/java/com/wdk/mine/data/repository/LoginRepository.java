package com.wdk.mine.data.repository;

import com.wdk.component_base.data.bean.ResultData;
import com.wdk.component_base.data.repository.BaseRepository;
import com.wdk.component_base.network.NetWorkManager;
import com.wdk.component_base.network.RequestData;
import com.wdk.mine.api.AccountService;
import com.wdk.mine.data.bean.LoginResponseBean;
import com.wdk.mine.data.bean.RegisterResponseBean;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 10:07 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 10:07 AM
 * @LastCheckBy: wdk
 */
public class LoginRepository extends BaseRepository {
    private static final String TAG = "LoginRepository";

    //登录
    public void doLogin(RequestData<LoginResponseBean> requestData) {
        AccountService accountService = NetWorkManager.getInstance().create(AccountService.class);
        Observable<ResultData<LoginResponseBean>> login = accountService.login(requestData.getStrParams("username"), requestData.getStrParams("password"));
        NetWorkManager.getInstance().getDataFromServer(login, requestData);
    }

    public void doRegister(RequestData<RegisterResponseBean> requestData) {
        AccountService accountService = NetWorkManager.getInstance().create(AccountService.class);
        Observable<ResultData<RegisterResponseBean>> register = accountService.register(requestData.getStrParams("username"), requestData.getStrParams("password"), requestData.getStrParams("repassword"));
        NetWorkManager.getInstance().getDataFromServer(register, requestData);
    }
}
