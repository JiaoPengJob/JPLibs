package com.libs.jiaop;

import com.jiaop.libs.base.JPBaseActivity;
import com.jiaop.libs.utils.JPToastUtil;
import com.orhanobut.logger.Logger;

import butterknife.OnClick;

public class MainActivity extends JPBaseActivity {

    @OnClick(R.id.btLog)
    void logShow() {
        Logger.wtf("Show Log");
        JPToastUtil.normal(this, "Info");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWiFiData() {

    }

    @Override
    protected void initNetData() {

    }

    @Override
    protected void initOfflineData() {

    }

    @Override
    protected int statusBarColor() {
        return R.color.black;
    }
}
