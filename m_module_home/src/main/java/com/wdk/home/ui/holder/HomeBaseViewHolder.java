package com.wdk.home.ui.holder;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.wdk.component_base.basepage.BaseViewHolder;
import com.wdk.component_base.data.bean.BaseBean;
import com.wdk.home.ui.HomeAdapter;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 3:44 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 3:44 PM
 * @LastCheckBy: wdk
 */
public abstract class HomeBaseViewHolder<T extends BaseBean, VDB extends ViewDataBinding> extends BaseViewHolder {

    public Context mContext;
    VDB viewDataBinding;

    public HomeBaseViewHolder(Context context, VDB viewDataBinding) {
        super(viewDataBinding.getRoot());
        mContext = context;
        this.viewDataBinding = viewDataBinding;
    }

    public abstract void setUpView(T t, int position, HomeAdapter homeAdapter);

}
