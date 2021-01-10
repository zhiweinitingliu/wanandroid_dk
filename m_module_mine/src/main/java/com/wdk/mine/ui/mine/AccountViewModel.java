package com.wdk.mine.ui.mine;

import androidx.lifecycle.MutableLiveData;

import com.wdk.component_base.viewmodel.BaseViewModel;
import com.wdk.mine.data.bean.LoginResponseBean;
import com.wdk.mine.data.bean.UserBean;
import com.wdk.mine.utils.UserInfoUtil;


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

    public MutableLiveData<UserBean> userBeanMutableLiveData;

    public AccountViewModel() {
        userBeanMutableLiveData = new MutableLiveData<>();
    }

    /**
     * 更新我的页面用户信息
     */
    public void updateUserInfo() {
        LoginResponseBean loginBean = UserInfoUtil.getInstance().getLoginBean();
        UserBean userBean = new UserBean();
        if (loginBean != null) {
            userBean.setUserName(loginBean.getNickname());
        }

        userBeanMutableLiveData.postValue(userBean);
    }

}
