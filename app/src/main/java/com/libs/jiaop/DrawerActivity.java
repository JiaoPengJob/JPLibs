package com.libs.jiaop;

import com.jiaop.libs.base.JPBaseActivity;

import butterknife.BindView;

public class DrawerActivity extends JPBaseActivity {

    @BindView(R.id.sliView)
    MultiSlidingDrawer sliView;

    @Override
    protected void initView() {
        sliView.open();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_drawer;
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
        return R.color.bg_color;
    }
}
