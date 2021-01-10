package com.wdk.mine.data.bean;

import com.wdk.component_base.data.bean.BaseBean;

public class UserBean extends BaseBean {

    //用户名
    private String userName;
    //是否登录
    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
