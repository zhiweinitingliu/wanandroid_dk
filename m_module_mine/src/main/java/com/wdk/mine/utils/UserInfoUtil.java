package com.wdk.mine.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.wdk.component_base.data.user.UserInfoProvider;
import com.wdk.component_base.utils.JsonUtil;
import com.wdk.component_base.utils.SharedPreferencesUtil;
import com.wdk.mine.data.bean.LoginResponseBean;
import com.wdk.mine.data.constances.ConstantsUser;


/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/24 8:29 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/24 8:29 AM
 * @LastCheckBy: wdk
 */
public class UserInfoUtil {

    private UserInfoUtil() {

    }

    public static UserInfoUtil getInstance() {
        return InnerClass.userInfoUtil;
    }

    private static class InnerClass {
        public static UserInfoUtil userInfoUtil = new UserInfoUtil();
    }

    /**
     * 将登录返回的数据已json格式存储起来
     *
     * @param loginResponseBean 登录返回的数据
     */
    public void saveLoginInfo(LoginResponseBean loginResponseBean) {
        if (loginResponseBean != null) {
            SharedPreferencesUtil.getInstance().putStringValue(ConstantsUser.user_info_json, JsonUtil.beanToJson(loginResponseBean));
            UserInfoProvider.getInstance().saveUserToken(loginResponseBean.getNickname());

        } else {
            SharedPreferencesUtil.getInstance().putStringValue(ConstantsUser.user_info_json, "");
            UserInfoProvider.getInstance().saveUserToken("");
        }

    }

    public LoginResponseBean getLoginBean() {
        String loginJson = SharedPreferencesUtil.getInstance().getStringValue(ConstantsUser.user_info_json);
        if (TextUtils.isEmpty(loginJson)) {
            return null;
        }
        return new Gson().fromJson(loginJson, LoginResponseBean.class);
    }


    public void logOut() {
        SharedPreferencesUtil.getInstance().putStringValue(ConstantsUser.user_info_json, "");
        UserInfoProvider.getInstance().saveUserToken("");
    }


}
