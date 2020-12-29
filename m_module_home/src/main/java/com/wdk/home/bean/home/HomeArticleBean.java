package com.wdk.home.bean.home;


import com.wdk.home.ui.HomeTypeFactory;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 3:02 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 3:02 PM
 * @LastCheckBy: wdk
 */
public class HomeArticleBean extends HomeBaseBean {

    private ArticleChildBean articleChildBean;

    public ArticleChildBean getArticleChildBean() {
        return articleChildBean;
    }

    public void setArticleChildBean(ArticleChildBean articleChildBean) {
        this.articleChildBean = articleChildBean;
    }

    @Override
    public int type(HomeTypeFactory homeTypeFactory) {
        return homeTypeFactory.type(this);
    }
}
