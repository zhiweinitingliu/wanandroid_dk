package com.wdk.component_base.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

/**
 * @Description: 网络图片加载帮助类
 * @Author: wdk
 * @CreatTime: 2017-08-04 15:11
 * @LastModify: wdk
 * @LastModifyTime: 2017-08-04 15:11
 * @LastCheckedBy: wdk
 */
public class LoadImage {

    /**
     * 加载网络图片  默认方形 “订单通占位图”
     *
     * @param context   上下文
     * @param imageView imageview
     * @param imgUrl    图片的网络地址
     */
    public static void loadRemoteImg(Context context, ImageView imageView, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }


    /**
     * 加载圆形图片 订货通占位图
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void loadRoundImg(Context context, ImageView imageView, String url, int round) {
        Glide.with(context)
                .load(url)
                .transform(new CenterCrop(), new GlideRoundTransform(context, round))
                .into(imageView);
    }

}
