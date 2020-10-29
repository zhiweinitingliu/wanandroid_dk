package com.wdk.wanandroid.ui.home.holder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.wdk.wanandroid.data.bean.home.HomeBaseBean;
import com.wdk.wanandroid.ui.home.HomeAdapter;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 3:44 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 3:44 PM
 * @LastCheckBy: wdk
 */
public abstract class HomeBaseViewHolder<T extends HomeBaseBean, VDB extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public Context mContext;
    VDB viewDataBinding;

    public HomeBaseViewHolder(Context context, VDB viewDataBinding) {
        super(viewDataBinding.getRoot());
        mContext = context;
        this.viewDataBinding = viewDataBinding;
    }

    public abstract void setUpView(T t, int position, HomeAdapter homeAdapter);

}
