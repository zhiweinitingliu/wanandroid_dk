package com.wdk.home.bean.home;


import com.wdk.home.ui.HomeTypeFactory;

import java.util.List;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 3:18 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 3:18 PM
 * @LastCheckBy: wdk
 */
public class HomeBannerBean extends HomeBaseBean {
    private int currentPage;

    private List<BannerChildBean> bannerChildBeanList;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<BannerChildBean> getBannerChildBeanList() {
        return bannerChildBeanList;
    }

    public void setBannerChildBeanList(List<BannerChildBean> bannerChildBeanList) {
        this.bannerChildBeanList = bannerChildBeanList;
    }

    @Override
    public int type(HomeTypeFactory homeTypeFactory) {
        return homeTypeFactory.type(this);
    }
}
