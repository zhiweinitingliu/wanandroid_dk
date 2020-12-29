package com.wdk.home.bean.home;

import com.wdk.component_base.data.bean.BaseBean;
import com.wdk.home.ui.HomeTypeFactory;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 2:38 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 2:38 PM
 * @LastCheckBy: wdk
 */
public abstract class HomeBaseBean extends BaseBean {

    public abstract int type(HomeTypeFactory homeTypeFactory);
}
