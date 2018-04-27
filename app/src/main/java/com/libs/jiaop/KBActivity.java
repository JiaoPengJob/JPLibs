package com.libs.jiaop;

import android.inputmethodservice.KeyboardView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiaop.libs.base.JPBaseActivity;
import com.libs.jiaop.keyboard.KeyboardUtil;
import com.luliang.shapeutils.DevShapeUtils;
import com.luliang.shapeutils.shape.DevShape;

import butterknife.BindView;
import butterknife.OnClick;

public class KBActivity extends JPBaseActivity implements KBUtil.onPClick {

    @BindView(R.id.kvShow)
    KeyboardView kvShow;
    @BindView(R.id.etInput)
    LastInputEditText etInput;
    @BindView(R.id.parentView)
    RelativeLayout parentView;
    @BindView(R.id.tvProvince)
    TextView tvProvince;

    private KBUtil kbUtil;
    private KeyboardUtil keyboardUtil;

    @Override
    protected void initView() {
//        kbUtil = new KBUtil(this, kvShow);
//        kbUtil.setOnPClick(this);
//        etInput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                kbUtil.showKeyboard(etInput);
//            }
//        });
        DevShapeUtils.shape(DevShape.RECTANGLE).solid(R.color.white).radius(4).into(etInput);
        keyboardUtil = KeyboardUtil.getInstance();
    }

    @OnClick(R.id.tvProvince)
    void tvProvince() {
        keyboardUtil.initTextViewFeatures(this, tvProvince, kvShow);
        keyboardUtil.showTextKeyboard();
    }

    @OnClick(R.id.etInput)
    void etInput() {
        keyboardUtil.initEditViewFeatures(this, etInput, kvShow);
        keyboardUtil.showEditKeyboard(etInput);
    }

    @Override
    public void getLabel(String label) {
        if (label.equals("delete")) {
            etInput.setText(etInput.getText().toString().substring(0, etInput.getText().toString().length() - 1));
        } else if (label.equals("done")) {

        } else {
            etInput.setText(etInput.getText().toString() + label);
        }
    }

    @OnClick(R.id.btnOpen)
    void btnOpen() {
        kbUtil.showKeyboard(etInput);
    }

    @OnClick(R.id.btnClose)
    void btnClose() {
        kbUtil.hideKeyboard();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_kb;
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
        return R.color.red;
    }
}
