package com.wdk.baselibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.UUID;

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
