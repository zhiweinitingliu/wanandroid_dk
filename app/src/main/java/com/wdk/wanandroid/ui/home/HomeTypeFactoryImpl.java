package com.wdk.wanandroid.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.wdk.wanandroid.R;
import com.wdk.wanandroid.data.bean.home.HomeArticleBean;
import com.wdk.wanandroid.data.bean.home.HomeBannerBean;
import com.wdk.wanandroid.databinding.ItemHomeTypeArticleBinding;
import com.wdk.wanandroid.databinding.ItemHomeTypeBannerBinding;
import com.wdk.wanandroid.ui.home.holder.HomeArticleViewHolder;
import com.wdk.wanandroid.ui.home.holder.HomeBannerViewHolder;
import com.wdk.wanandroid.ui.home.holder.HomeBaseViewHolder;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 3:38 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 3:38 PM
 * @LastCheckBy: wdk
 */
public class HomeTypeFactoryImpl implements HomeTypeFactory {

    public static final int item_home_type_banner = R.layout.item_home_type_banner;
    public static final int item_home_type_article = R.layout.item_home_type_article;


    @Override
    public int type(HomeBannerBean homeBannerBean) {
        return item_home_type_banner;
    }

    @Override
    public int type(HomeArticleBean homeArticleBean) {
        return item_home_type_article;
    }

    @Override
    public HomeBaseViewHolder createBaseViewHolder(Context context, ViewGroup parent, int viewType) {
        switch (viewType) {
            //轮播图
            case item_home_type_banner:
                ItemHomeTypeBannerBinding itemHomeTypeBannerBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_home_type_banner, parent, false);
                return new HomeBannerViewHolder(context, itemHomeTypeBannerBinding);

            //文章列表
            case item_home_type_article:
                ItemHomeTypeArticleBinding itemHomeTypeArticleBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_home_type_article, parent, false);
                return new HomeArticleViewHolder(context, itemHomeTypeArticleBinding);
        }
        return null;
    }


}
