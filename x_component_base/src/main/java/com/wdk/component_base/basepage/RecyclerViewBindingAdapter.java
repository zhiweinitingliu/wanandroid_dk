package com.wdk.component_base.basepage;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;



public class RecyclerViewBindingAdapter {

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }


}
