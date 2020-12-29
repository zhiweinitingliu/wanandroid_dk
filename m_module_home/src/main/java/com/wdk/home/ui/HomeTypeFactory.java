package com.wdk.home.ui;

import android.content.Context;
import android.view.ViewGroup;

import com.wdk.home.bean.home.HomeArticleBean;
import com.wdk.home.bean.home.HomeBannerBean;
import com.wdk.home.ui.holder.HomeBaseViewHolder;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 3:29 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 3:29 PM
 * @LastCheckBy: wdk
 */
public interface HomeTypeFactory {

    //轮播图
    int type(HomeBannerBean homeBannerBean);

    //文章
    int type(HomeArticleBean homeArticleBean);

     HomeBaseViewHolder createBaseViewHolder(Context context, ViewGroup parent, int viewType);
}
