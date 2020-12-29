package com.wdk.home.ui;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wdk.home.bean.home.HomeBaseBean;
import com.wdk.home.ui.holder.HomeBaseViewHolder;

import java.util.List;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 3:51 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 3:51 PM
 * @LastCheckBy: wdk
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeBaseViewHolder> {

    private Context context;
    private HomeTypeFactoryImpl homeTypeFactory;
    List<HomeBaseBean> homeBeanList;

    public HomeAdapter(Context context) {
        this.context = context;
        homeTypeFactory = new HomeTypeFactoryImpl();
    }

    public void setHomeData(List<HomeBaseBean> homeBeanList) {
        this.homeBeanList = homeBeanList;
    }


    @Override
    public int getItemViewType(int position) {
        return homeBeanList.get(position).type(homeTypeFactory);
    }

    @Override
    public int getItemCount() {
        return homeBeanList == null ? 0 : homeBeanList.size();
    }


    @NonNull
    @Override
    public HomeBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return homeTypeFactory.createBaseViewHolder(context, parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeBaseViewHolder holder, int position) {
        holder.setUpView(homeBeanList.get(position), position, this);
    }
}
