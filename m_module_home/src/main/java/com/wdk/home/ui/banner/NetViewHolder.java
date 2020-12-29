package com.wdk.home.ui.banner;

import android.view.View;

import androidx.annotation.NonNull;

import com.wdk.component_base.basepage.BaseApplication;
import com.wdk.component_base.utils.LoadImage;
import com.wdk.home.R;
import com.wdk.home.bean.home.BannerChildBean;
import com.zhpan.bannerview.BaseViewHolder;
import com.zhpan.bannerview.utils.BannerUtils;


/**
 * <pre>
 *   Created by zhangpan on 2019-08-14.
 *   Description:
 * </pre>
 */
public class NetViewHolder extends BaseViewHolder<BannerChildBean> {

    public NetViewHolder(@NonNull View itemView) {
        super(itemView);
        CornerImageView imageView = findView(R.id.banner_image);
        imageView.setRoundCorner(BannerUtils.dp2px(0));
    }

    @Override
    public void bindData(BannerChildBean data, int position, int pageSize) {
        CornerImageView imageView = findView(R.id.banner_image);
//        LoadImage.loadRemoteImgNoDefault(MainApplication.getInstance(), imageView, data.getImg());

        LoadImage.loadRoundImg(BaseApplication.getInstance(), imageView, data.getImagePath(),5);
        BannerUtils.log("NetViewHolder", "position:" + position);
    }
}
