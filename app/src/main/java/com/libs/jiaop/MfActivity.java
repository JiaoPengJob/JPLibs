package com.libs.jiaop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.jiaop.libs.base.JPBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MfActivity extends JPBaseActivity {

    @BindView(R.id.vpMf)
    ViewPager vpMf;

    private List<Fragment> fs = new ArrayList<>();

    @Override
    protected void initView() {
        for (int i = 0; i < 6; i++) {
            Bundle b = new Bundle();
            b.putString("str", "FFFF" + i);
            MFragment mf = new MFragment();
            mf.setArguments(b);
            fs.add(mf);
        }
        vpMf.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fs.get(position);
            }

            @Override
            public int getCount() {
                return fs.size();
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_mf;
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
        return R.color.beige;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
