package com.wdk.home.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.wdk.home.R;
import com.wdk.home.bean.home.HomeArticleBean;
import com.wdk.home.bean.home.HomeBannerBean;
import com.wdk.home.databinding.ItemHomeTypeArticleBinding;
import com.wdk.home.databinding.ItemHomeTypeBannerBinding;
import com.wdk.home.ui.holder.HomeArticleViewHolder;
import com.wdk.home.ui.holder.HomeBannerViewHolder;
import com.wdk.home.ui.holder.HomeBaseViewHolder;

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

//    public static final int item_home_type_banner = R.layout.item_home_type_banner;
//    public static final int item_home_type_article = R.layout.item_home_type_article;
    public static final int item_home_type_banner = 111001;
    public static final int item_home_type_article = 111002;


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
