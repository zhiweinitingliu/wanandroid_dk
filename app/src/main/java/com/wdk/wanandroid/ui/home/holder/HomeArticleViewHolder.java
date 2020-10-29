package com.wdk.wanandroid.ui.home.holder;

import android.content.Context;

import androidx.annotation.NonNull;

import com.wdk.wanandroid.data.bean.home.HomeArticleBean;
import com.wdk.wanandroid.databinding.ItemHomeTypeArticleBinding;
import com.wdk.wanandroid.ui.home.HomeAdapter;

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
            viewDataBinding.setArticleBean(homeArticleBean.getArticleChildBean());

    }
}
