package com.jiaop.libs.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by jiaop
 * Fragment基类
 * 懒加载模式
 */
public abstract class JPBaseFragment extends Fragment {

    private boolean isInitView = false;
    private boolean isVisible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(layoutId(), null);
        ButterKnife.bind(this, layout);
        initView();
        isInitView = true;
        return layout;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            isCanLoadData();
        } else {
            isVisible = false;
        }
    }

    private void isCanLoadData() {
        //所以条件是view初始化完成并且对用户可见
        if (isInitView && isVisible) {
            //防止重复加载数据
            isInitView = false;
            isVisible = false;
            onVisible();
        }
    }

    /**
     * 用户可见加载数据
     */
    private void onVisible() {
        switch (((JPBaseActivity) getActivity()).getNetworkChange().getState()) {
            case 0:
                initOfflineData();
                break;
            case 1:
                initWiFiData();
                break;
            case 2:
                initNetData();
                break;
        }
    }

    /**
     * 加载WiFi网络数据
     */
    protected abstract void initWiFiData();

    /**
     * 加载蜂窝网络数据
     */
    protected abstract void initNetData();

    /**
     * 加载离线数据
     * 用于设置没网状态下的数据
     */
    protected abstract void initOfflineData();

    /**
     * 初始化布局数据
     */
    protected abstract void initView();

    /**
     * 获取layoutId
     *
     * @return
     */
    protected abstract int layoutId();

}
