package com.libs.jiaop;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jiaop.libs.base.JPBaseActivity;
import com.jiaop.libs.utils.JPSharedPreUtil;
import com.jiaop.libs.utils.JPToastUtil;
import com.orhanobut.logger.Logger;

import butterknife.OnClick;

public class MainActivity extends JPBaseActivity {

    private static String TAG = "MainActivity";

    @OnClick(R.id.btLog)
    void logShow() {
        Logger.wtf("Show Log");
        JPToastUtil.normal(this, "Info");
    }

    @OnClick(R.id.btP)
    void btP() {
        User u = new User("小明", "男");
        JPSharedPreUtil.getInstance().putValue("user", u);
    }

    @OnClick(R.id.btR)
    void btR() {
        User u = JPSharedPreUtil.getInstance().getValue("user", User.class);
        Log.e(TAG, "user name == " + u.getName() + " / user sex == " + u.getSex());
    }

    @OnClick(R.id.btU)
    void btU() {
        User u = new User("测试", "修改");
        JPSharedPreUtil.getInstance().putValue("user", u);
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
        return R.color.colorPrimary;
    }
}
