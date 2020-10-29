package com.wdk.baselibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/11/27 0027
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/11/27 0027
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public class GlideRoundCornerTransform extends BitmapTransformation {

    private float[] radiusArray = {0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};


    private boolean isFitXy = false;
    private boolean isAllConner = false;
    private float radius;

    /**
     * 设置四个角的圆角半径
     */
    public void setRadius(float leftTop, float rightTop, float rightBottom, float leftBottom) {
        radiusArray[0] = Resources.getSystem().getDisplayMetrics().density * leftTop;
        radiusArray[1] = Resources.getSystem().getDisplayMetrics().density * leftTop;
        radiusArray[2] = Resources.getSystem().getDisplayMetrics().density * rightTop;
        radiusArray[3] = Resources.getSystem().getDisplayMetrics().density * rightTop;
        radiusArray[4] = Resources.getSystem().getDisplayMetrics().density * rightBottom;
        radiusArray[5] = Resources.getSystem().getDisplayMetrics().density * rightBottom;
        radiusArray[6] = Resources.getSystem().getDisplayMetrics().density * leftBottom;
        radiusArray[7] = Resources.getSystem().getDisplayMetrics().density * leftBottom;
        isAllConner = (leftTop == rightTop && leftTop == rightBottom && leftTop == leftBottom);
        radius = Resources.getSystem().getDisplayMetrics().density * leftTop;
    }

    public GlideRoundCornerTransform(Context context) {
        super();
    }


    public void setFitXy(boolean fitXy) {
        isFitXy = fitXy;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        if (isFitXy) {
            toTransform = fitXY(pool, toTransform, outWidth, outHeight);
        }
        return isAllConner ? roundCrop(pool, toTransform) : roundCrop(pool, toTransform, outWidth, outHeight);
    }

    private Bitmap fitXY(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {
        if (source == null) return null;
        Bitmap newSource = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        if (newSource == null) {
            newSource = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        }

        Matrix m = new Matrix();
        float x = (float) outWidth / (float) source.getWidth();
        float y = (float) outHeight / (float) source.getHeight();
        m.setScale(x, y);
        Canvas canvasB = new Canvas(newSource);
        Paint paintB = new Paint(Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG);
        canvasB.drawBitmap(source, m, paintB);

        return newSource;
    }

    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }


    private Bitmap roundCrop(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {
        if (source == null) return null;
        Bitmap newSource = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        if (newSource == null) {
            newSource = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        }

        Matrix m = new Matrix();
        float x = outWidth / source.getWidth();
        float y = outHeight / source.getHeight();
        m.setScale(x, y);
        Canvas canvasB = new Canvas(newSource);
        Paint paintB = new Paint(Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG);
        canvasB.drawBitmap(source, m, paintB);


        Bitmap result = pool.get(newSource.getWidth(), newSource.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(newSource.getWidth(), newSource.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(newSource, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, newSource.getWidth(), newSource.getHeight()), radiusArray, Path.Direction.CW);
        canvas.drawPath(path, paint);
        return result;
    }


    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        String s = getClass().getName() + isFitXy + isAllConner + Math.round(radiusArray[0]) + Math.round(radiusArray[1]) + Math.round(radiusArray[2]) + Math.round(radiusArray[3]) + Math.round(radiusArray[4]) + Math.round(radiusArray[5]) + Math.round(radiusArray[6]) + Math.round(radiusArray[7]);
        messageDigest.update(s.getBytes());

    }
}