package com.wdk.component_base.basepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.wdk.component_base.data.bean.BaseBean;

import java.util.List;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/19 3:46 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/19 3:46 PM
 * @LastCheckBy: wdk
 */
public abstract class BaseBindingAdapter<VB extends ViewDataBinding, M extends BaseBean, VH extends BaseViewHolder> extends BaseRecyclerViewAdapter<M, VH> {


    public BaseBindingAdapter(Context context) {
        super(context);
    }

    public abstract int getLayoutId();

    @Override
    public int getItemCount() {
        return getData() == null ? 0 : getData().size();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VB binding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutId(), parent, false);
        return createViewHolder(binding.getRoot());
    }

    public abstract VH createViewHolder(View view);

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        M bean = getData().get(position);
        VB binding = DataBindingUtil.getBinding(holder.itemView);
        onBindItem(binding, bean, position);
    }

    public abstract void onBindItem(VB binding, M bean, int position);

}
