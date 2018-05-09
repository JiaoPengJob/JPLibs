package com.libs.jiaop.zxing;

import android.graphics.Bitmap;

import com.jiaop.libs.base.JPBaseActivity;
import com.libs.jiaop.R;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.OnClick;

public class QRActivity extends JPBaseActivity {

    @Override
    protected void initView() {
        /**
         * 执行扫面Fragment的初始化操作
         */
        CaptureFragment captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);

        captureFragment.setAnalyzeCallback(analyzeCallback);
        /**
         * 替换我们的扫描控件
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Logger.wtf("QR == " + result);
//            Intent resultIntent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
//            bundle.putString(CodeUtils.RESULT_STRING, result);
//            resultIntent.putExtras(bundle);
//            QRActivity.this.setResult(RESULT_OK, resultIntent);
//            QRActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Logger.wtf("QR == Error");
//            Intent resultIntent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
//            bundle.putString(CodeUtils.RESULT_STRING, "");
//            resultIntent.putExtras(bundle);
//            QRActivity.this.setResult(RESULT_OK, resultIntent);
//            QRActivity.this.finish();
        }
    };

    @OnClick(R.id.btnOpen)
    void btnOpen(){
        CodeUtils.isLightEnable(true);
    }

    @OnClick(R.id.btnClose)
    void btnClose(){
        CodeUtils.isLightEnable(false);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_qr;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
