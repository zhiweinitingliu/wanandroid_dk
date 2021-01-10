package com.wdk.mine.ui.setting;

import androidx.lifecycle.MutableLiveData;

import com.wdk.component_base.data.constances.MessageEvent;
import com.wdk.component_base.viewmodel.BaseViewModel;
import com.wdk.mine.data.bean.LoginResponseBean;
import com.wdk.mine.data.bean.UserBean;
import com.wdk.mine.utils.UserInfoUtil;

import org.greenrobot.eventbus.EventBus;

public class SettingViewModel extends BaseViewModel {

    public MutableLiveData<UserBean> userBeanMutableLiveData;

    public SettingViewModel() {
        userBeanMutableLiveData = new MutableLiveData<>();
    }

    public void updateUserInfo() {
        LoginResponseBean loginBean = UserInfoUtil.getInstance().getLoginBean();
        UserBean userBean = new UserBean();
        if (loginBean != null) {
            userBean.setUserName(loginBean.getNickname());
            userBean.setLogin(true);
        }

        userBeanMutableLiveData.postValue(userBean);
    }


    /**
     * 退出登录
     */
    public void loginOut() {
        UserInfoUtil.getInstance().logOut();
        EventBus.getDefault().post(new MessageEvent(MessageEvent.login_out));
    }


}
