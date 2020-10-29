package com.wdk.baselibrary.basepage;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.wdk.baselibrary.viewmodel.BaseViewModel;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/29 10:50 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/29 10:50 AM
 * @LastCheckBy: wdk
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    private ViewModelProvider mViewModelProvider;
    public T mBinding;

    /**
     * 1、 开始加载loading
     * 2、加载完成 loading
     */
    public MutableLiveData<Integer> loadingShowLiveData = new MutableLiveData<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initViewModel();
        initDataBinding(inflater, container, savedInstanceState);
        return mBinding.getRoot();
    }

    protected abstract void initViewModel();

    private void initDataBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DataBindingConfig dataBindingConfig = getDataBindingConfig();
//        View root = inflater.inflate(dataBindingConfig.getLayout(), container, false);
        mBinding = DataBindingUtil.inflate(inflater,dataBindingConfig.getLayout(),container,false);
        mBinding.setLifecycleOwner(this);
        SparseArray<Object> bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            mBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
    }

    protected abstract DataBindingConfig getDataBindingConfig();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    public abstract void initData();

    /**
     * 此方法给子类提供获取viewmodel的方法
     * 一个activity或者是fragment中可能会有多个viewmodel，所以将创建方法抛出，在view中进行创建
     *
     * @param clazz viewmodel的 .class
     * @param <T>   创建的viewmodel
     * @return
     */
    public <T extends BaseViewModel> T getFragmentViewModel(@NonNull Class<T> clazz) {
        if (mViewModelProvider == null) {
            mViewModelProvider = new ViewModelProvider(this);
        }
        T t = mViewModelProvider.get(clazz);
        t.setLoadingShowLiveData(loadingShowLiveData);
        return t;
    }
}
