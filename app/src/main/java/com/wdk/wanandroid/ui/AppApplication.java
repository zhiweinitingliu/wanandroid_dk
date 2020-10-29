package com.wdk.wanandroid.ui;

import android.app.Application;

import com.wdk.baselibrary.basepage.AppInitUtil;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 1:07 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 1:07 PM
 * @LastCheckBy: wdk
 */
public class AppApplication extends Application {

    private static AppApplication application;

    public static AppApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        AppInitUtil.getInstance().init(this);
    }
}
