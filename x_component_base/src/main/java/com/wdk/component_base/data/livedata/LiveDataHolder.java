package com.wdk.component_base.data.livedata;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

public class LiveDataHolder<T> {
    private Map<Class, MutableLiveData<Resources<T>>> map = new HashMap<>();

    public MutableLiveData<Resources<T>> getLiveData(Class<T> dataType) {
        MutableLiveData<Resources<T>> liveData = map.get(dataType);
        if (liveData==null){
            liveData=new MutableLiveData<>();
            map.put(dataType,liveData);
        }
        return liveData;
    }

}
