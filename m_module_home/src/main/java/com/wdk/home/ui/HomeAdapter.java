package com.wdk.home.ui;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.wdk.component_base.basepage.BaseRecyclerViewAdapter;
import com.wdk.home.bean.home.HomeBaseBean;
import com.wdk.home.ui.holder.HomeBaseViewHolder;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 3:51 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 3:51 PM
 * @LastCheckBy: wdk
 */
public class HomeAdapter extends BaseRecyclerViewAdapter<HomeBaseBean, HomeBaseViewHolder> {

    private HomeTypeFactoryImpl homeTypeFactory;

    public HomeAdapter(Context context) {
        super(context);
        homeTypeFactory = new HomeTypeFactoryImpl();
    }

    @Override
    public int getItemViewType(int position) {
        return getData().get(position).type(homeTypeFactory);
    }

    @Override
    public int getItemCount() {
        return getData() == null ? 0 : getData().size();
    }

    @NonNull
    @Override
    public HomeBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return homeTypeFactory.createBaseViewHolder(context, parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeBaseViewHolder holder, int position) {
        holder.setUpView(getData().get(position), position, this);
    }
}
