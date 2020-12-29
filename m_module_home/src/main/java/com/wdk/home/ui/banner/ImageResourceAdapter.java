package com.wdk.home.ui.banner;

import android.view.View;
import android.view.ViewGroup;


import com.wdk.home.R;
import com.wdk.home.bean.home.BannerChildBean;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

import org.jetbrains.annotations.NotNull;


/**
 * <pre>
 *   Created by zhpan on 2020/4/5.
 *   Description:
 * </pre>
 */
public class ImageResourceAdapter extends BaseBannerAdapter<BannerChildBean, BaseViewHolder<BannerChildBean>> {

    private int roundCorner;

    public ImageResourceAdapter(int roundCorner) {
        this.roundCorner = roundCorner;
    }


    @Override
    protected void onBind(BaseViewHolder<BannerChildBean> holder, BannerChildBean data, int position, int pageSize) {
        holder.bindData(data, position, pageSize);
    }

    @Override
    public NetViewHolder createViewHolder(@NotNull ViewGroup parent, View itemView, int viewType) {
        return new NetViewHolder(itemView);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_slide_mode;
    }
}
