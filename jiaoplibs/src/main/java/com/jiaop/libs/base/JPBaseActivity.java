package com.jiaop.libs.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.jiaop.libs.broadcast.JPNetworkChangeBroadcast;
import com.jiaop.libs.interfaces.JPNetworkChangeInterface;
import com.jiaop.libs.utils.JPActivityUtil;

import butterknife.ButterKnife;

/**
 * Created by jiaop
 * Activity基类
 */

public abstract class JPBaseActivity extends AppCompatActivity implements JPNetworkChangeInterface {

    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局文件
        setContentView(layoutId());
        //隐藏标题栏
        getSupportActionBar().hide();
        //加载ButterKnife--View注解
        ButterKnife.bind(this);
        // 注册Broadcast Receiver
        JPNetworkChangeBroadcast netBroadcastReceiver = new JPNetworkChangeBroadcast(JPBaseActivity.this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netBroadcastReceiver, filter);
        //沉浸式状态栏
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar
                .statusBarColor(statusBarColor())
                .fitsSystemWindows(true)
                .init();
        //Activity栈管理
        JPActivityUtil.getInstance().addActivity(this);
        //初始化页面
        initView();
    }

    /**
     * 加载页面
     */
    protected abstract void initView();

    /**
     * 获取layoutId
     *
     * @return
     */
    protected abstract int layoutId();

    @Override
    public void networkChange(int status) {
        //0：没有网络 1：WIFI网络 2：net网络
        switch (status) {
            case 0:
//                Toasty.info(JPBaseActivity.this, "当前处于无网络状态，请联网重试！", Toast.LENGTH_SHORT, true).show();
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
     * 设置状态栏颜色
     *
     * @return
     */
    protected abstract int statusBarColor();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

}
