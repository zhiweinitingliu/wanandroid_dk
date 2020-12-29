package com.wdk.home.ui.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;

import androidx.viewpager2.widget.ViewPager2;

import com.wdk.component_base.utils.ScreenUtils;
import com.wdk.home.R;
import com.wdk.home.bean.home.BannerChildBean;
import com.wdk.home.bean.home.HomeBannerBean;
import com.wdk.home.databinding.ItemHomeTypeBannerBinding;
import com.wdk.home.ui.HomeAdapter;
import com.wdk.home.ui.banner.ImageResourceAdapter;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.BaseViewHolder;
import com.zhpan.bannerview.constants.IndicatorGravity;
import com.zhpan.bannerview.utils.BannerUtils;
import com.zhpan.indicator.enums.IndicatorSlideMode;
import com.zhpan.indicator.enums.IndicatorStyle;

import java.util.List;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 3:53 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 3:53 PM
 * @LastCheckBy: wdk
 */
public class HomeBannerViewHolder extends HomeBaseViewHolder<HomeBannerBean, ItemHomeTypeBannerBinding> {

    BannerViewPager<BannerChildBean, BaseViewHolder<BannerChildBean>> bannerViewPager;
    ImageResourceAdapter imageResourceAdapter;
    List<BannerChildBean> bannerList;

    public HomeBannerViewHolder(Context context, ItemHomeTypeBannerBinding viewDataBinding) {
        super(context, viewDataBinding);
        imageResourceAdapter = new ImageResourceAdapter(0);
        bannerViewPager = viewDataBinding.bannerViewPager;

        //动态设置轮播图的宽和高
        int bannerHeight = (ScreenUtils.getScreenWidth(context) - ScreenUtils.dp2px(context, 20)) * 717 / 1148;
        ViewGroup.LayoutParams layoutParams = bannerViewPager.getLayoutParams();
        layoutParams.height = bannerHeight;
        bannerViewPager.setLayoutParams(layoutParams);

        bannerViewPager.setAutoPlay(true)
                .setScrollDuration(600)
                .setPageMargin(ScreenUtils.dp2px(context, 10))
                .setRevealWidth(BannerUtils.dp2px(0))
                .setOffScreenPageLimit(2)
                .setIndicatorStyle(IndicatorStyle.CIRCLE)
                .setIndicatorSlideMode(IndicatorSlideMode.WORM)
                .setInterval(3000)
                .setIndicatorGravity(IndicatorGravity.CENTER)
                .setIndicatorSliderRadius(5)
                .disallowInterceptTouchEvent(true)
                .setIndicatorSliderColor(Color.GRAY, mContext.getResources().getColor(R.color.color99FFFFFF))
                .setAdapter(imageResourceAdapter)
                .registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        homeBannerBean.setCurrentPage(position);
                    }
                })
                .setOnPageClickListener(new BannerViewPager.OnPageClickListener() {
                    @Override
                    public void onPageClick(int position) {
                        if (bannerList != null && position < bannerList.size()) {
//                            doOnClick(bannerList.get(position).getUrl());
                        }
                    }
                });
        bannerViewPager.removeDefaultPageTransformer();


    }

    HomeBannerBean homeBannerBean;

    @Override
    public void setUpView(HomeBannerBean homeBannerBean, int position, HomeAdapter homeAdapter) {
        viewDataBinding.setHomeBannerBean(homeBannerBean);
        this.homeBannerBean = homeBannerBean;
        bannerList = homeBannerBean.getBannerChildBeanList();

        int currentPage = homeBannerBean.getCurrentPage();
        bannerViewPager.create(bannerList);
        bannerViewPager.setCurrentItem(currentPage, false);
    }


}
