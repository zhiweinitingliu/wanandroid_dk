package com.wdk.component_base.basepage;

import android.content.Context;

import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.wdk.component_base.data.bean.BaseBean;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<M extends BaseBean, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    public Context context;

    private List<M> items;

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
        items = new ObservableArrayList<>();
    }

    public void setData(List<M> list) {
        this.items = list;
    }

    public List<M> getData() {
        return items;
    }
}
