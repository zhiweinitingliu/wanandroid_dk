package com.wdk.mine.ui.mine;

import android.view.View;

import com.wdk.component_base.basepage.BaseFragment;
import com.wdk.component_base.basepage.DataBindingConfig;
import com.wdk.component_base.data.user.UserInfoProvider;
import com.wdk.component_base.utils.IntentManager;
import com.wdk.mine.BR;
import com.wdk.mine.R;
import com.wdk.mine.databinding.FragmentMineBinding;
import com.wdk.mine.ui.login.LoginActivity;
import com.wdk.mine.ui.setting.SettingActivity;


/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/10/27 9:47 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/10/27 9:47 PM
 * @LastCheckBy: wdk
 */
public class MineFragment extends BaseFragment<FragmentMineBinding> {

    AccountViewModel accountViewModel;

    @Override
    protected void initViewModel() {
        accountViewModel = getFragmentViewModel(AccountViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig dataBindingConfig = new DataBindingConfig(R.layout.fragment_mine);
        dataBindingConfig.addBindingParam(BR.accountViewModel, accountViewModel);
        dataBindingConfig.addBindingParam(BR.myHandlers, new MyHandlers());
        return dataBindingConfig;
    }


    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        accountViewModel.updateUserInfo();
    }

    public class MyHandlers {

        //设置
        public void onSettingClick() {
            IntentManager.startActivity(getActivity(), SettingActivity.class);
        }

        //头像点击
        public void onUserAvatarClick() {
            if (!UserInfoProvider.getInstance().isLogin()) {
                IntentManager.startActivity(getActivity(), LoginActivity.class);
            }
        }
    }


}
