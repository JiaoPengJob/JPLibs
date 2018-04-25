package com.libs.jiaop;

import android.widget.TextView;

import com.jiaop.libs.base.JPBaseFragment;

import butterknife.BindView;

/**
 * <pre>
 *     author : jiaop
 *     time   : 2018/04/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MFragment extends JPBaseFragment {

    @BindView(R.id.tvMfText)
    TextView tvMfText;

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
    protected void initView() {
        String text = getArguments().getString("str");
        double num = Math.random() * 100;
        tvMfText.setText(text + "===" + (int) num);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_mf;
    }
}
