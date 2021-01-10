package com.wdk.component_base.data.user;

import android.text.TextUtils;

import com.wdk.component_base.data.constances.Constants;
import com.wdk.component_base.utils.SharedPreferencesUtil;

public class UserInfoProvider implements UserInfoGetInterface, UserInfoSaveInterface {

    private static class Inner {
        private static UserInfoProvider userInfoProvider = new UserInfoProvider();
    }

    public static UserInfoProvider getInstance() {
        return Inner.userInfoProvider;
    }

    private UserInfoProvider() {

    }

    /**
     * 保存用户的token
     *
     * @param token
     */
    @Override
    public void saveUserToken(String token) {
        SharedPreferencesUtil.getInstance().putStringValue(Constants.token, token);
    }

    /**
     * 用户是否登录
     */
    @Override
    public boolean isLogin() {
        return !TextUtils.isEmpty(SharedPreferencesUtil.getInstance().getStringValue(Constants.token));
    }

    /**
     * 获取用户的token
     */
    @Override
    public String getUserToken() {
        return SharedPreferencesUtil.getInstance().getStringValue(Constants.token);
    }


}
