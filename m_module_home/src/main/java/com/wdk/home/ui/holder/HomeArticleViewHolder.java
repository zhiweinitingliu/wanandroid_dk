package com.wdk.home.ui.holder;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;

import com.wdk.component_base.utils.StringUtils;
import com.wdk.component_base.utils.TimeUtil;
import com.wdk.home.bean.home.HomeArticleBean;
import com.wdk.home.databinding.ItemHomeTypeArticleBinding;
import com.wdk.home.ui.HomeAdapter;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 3:56 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 3:56 PM
 * @LastCheckBy: wdk
 */
public class HomeArticleViewHolder extends HomeBaseViewHolder<HomeArticleBean, ItemHomeTypeArticleBinding> {

    public HomeArticleViewHolder(Context context, @NonNull ItemHomeTypeArticleBinding itemHomeTypeArticleBinding) {
        super(context, itemHomeTypeArticleBinding);
    }

    @Override
    public void setUpView(HomeArticleBean homeArticleBean, int position, HomeAdapter homeAdapter) {
        if (!TextUtils.isEmpty(homeArticleBean.getArticleChildBean().getAuthor())) {
            homeArticleBean.getArticleChildBean().setMyShareUser(getUser("作者：", homeArticleBean.getArticleChildBean().getAuthor()));
        } else if (!TextUtils.isEmpty(homeArticleBean.getArticleChildBean().getShareUser())) {
            homeArticleBean.getArticleChildBean().setMyShareUser(getUser("分享人：", homeArticleBean.getArticleChildBean().getShareUser()));
        } else {
            homeArticleBean.getArticleChildBean().setMyShareUser("");
            homeArticleBean.getArticleChildBean().setMyShareUser("");
        }

        homeArticleBean.getArticleChildBean().setPublishTimeStr(getPublishTime(homeArticleBean.getArticleChildBean().getPublishTime()));
//        viewDataBinding.setArticleBean(homeArticleBean.getArticleChildBean());
        viewDataBinding.setVariable(BR.articleBean, homeArticleBean.getArticleChildBean());
        viewDataBinding.executePendingBindings();
    }

    public static String getUser(String title, String user) {
//        "<font color=\"#666666\">购买既返 </font><font color=\"#FF3300\">200</font><font color=\"#666666\">丁豆 </font>"

        return StringUtils.appendStr("<font color=\"#999999\">", title, "</font><font color=\"#999999\">", user, "</font>");

    }

    public static String getPublishTime(long time) {
//        "<font color=\"#666666\">购买既返 </font><font color=\"#FF3300\">200</font><font color=\"#666666\">丁豆 </font>"

        return StringUtils.appendStr("<font color=\"#999999\">发布时间：</font><font color=\"#999999\">", TimeUtil.getDateTimeByMillisecond(time, "yy-MM-dd  HH:mm"), "</font>");

    }

}
