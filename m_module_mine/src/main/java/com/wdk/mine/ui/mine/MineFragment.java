package com.wdk.mine.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.wdk.mine.R;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/27 9:47 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/27 9:47 PM
 * @LastCheckBy: wdk
 */
public class MineFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mine, container, false);

        return root;
    }
}
