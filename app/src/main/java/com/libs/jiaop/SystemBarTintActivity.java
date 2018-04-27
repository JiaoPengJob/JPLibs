package com.libs.jiaop;

import android.graphics.SurfaceTexture;
import android.os.Build;
import android.util.Log;
import android.view.TextureView;
import android.widget.Toast;

import com.jiaop.libs.base.JPBaseActivity;

import butterknife.BindView;
import cn.simonlee.xcodescanner.core.CameraScanner;
import cn.simonlee.xcodescanner.core.GraphicDecoder;
import cn.simonlee.xcodescanner.core.NewCameraScanner;
import cn.simonlee.xcodescanner.core.OldCameraScanner;
import cn.simonlee.xcodescanner.core.ZBarDecoder;
import cn.simonlee.xcodescanner.view.AdjustTextureView;
import cn.simonlee.xcodescanner.view.ScannerFrameView;

public class SystemBarTintActivity extends JPBaseActivity implements CameraScanner.CameraListener, TextureView.SurfaceTextureListener, GraphicDecoder.DecodeListener {

    @BindView(R.id.textureview)
    AdjustTextureView mTextureView;

    @BindView(R.id.scannerframe)
    ScannerFrameView mScannerFrameView;

    private CameraScanner mCameraScanner;
    private GraphicDecoder mGraphicDecoder;

    @Override
    protected void initView() {
        mTextureView.setSurfaceTextureListener(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mCameraScanner = OldCameraScanner.getInstance();
        } else {
            mCameraScanner = NewCameraScanner.getInstance();
        }
        mCameraScanner.setCameraListener(this);
    }

    @Override
    public void openCameraSuccess(int surfaceWidth, int surfaceHeight, int surfaceDegree) {
        mTextureView.setImageFrameMatrix(surfaceWidth, surfaceHeight, surfaceDegree);
        if (mGraphicDecoder == null) {
            mGraphicDecoder = new ZBarDecoder();//使用带参构造方法可指定条码识别的格式
            mGraphicDecoder.setDecodeListener(this);
        }
        //调用setFrameRect方法会对识别区域进行限制，注意getLeft等获取的是相对于父容器左上角的坐标，实际应传入相对于TextureView左上角的坐标。
        mCameraScanner.setFrameRect(mScannerFrameView.getLeft(), mScannerFrameView.getTop(), mScannerFrameView.getRight(), mScannerFrameView.getBottom());
        mCameraScanner.setGraphicDecoder(mGraphicDecoder);
    }

    @Override
    public void openCameraError() {
        Toast.makeText(this, "出错了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noCameraPermission() {
        Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cameraDisconnected() {
        Toast.makeText(this, "断开了连接", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mCameraScanner.setSurfaceTexture(surface);
        mCameraScanner.setPreviewSize(width, height);
        mCameraScanner.openCamera(this.getApplicationContext());
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        Log.e("二维码", getClass().getName() + ".onSurfaceTextureSizeChanged() width = " + width + " , height = " + height);
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        Log.e("二维码", getClass().getName() + ".onSurfaceTextureDestroyed()");
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void decodeSuccess(int type, int quality, String result) {
        //64二维码，128条形码
        Toast.makeText(this, "类型 == " + type + "/精度 == " + quality + "/result == " + result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_system_bar_tint;
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
    protected void onPause() {
        mCameraScanner.closeCamera();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        //部分机型在后台转前台时会回调onSurfaceTextureAvailable开启相机，因此要做判断防止重复开启
        if (mTextureView.isAvailable()) {
            mCameraScanner.setSurfaceTexture(mTextureView.getSurfaceTexture());
            mCameraScanner.setPreviewSize(mTextureView.getWidth(), mTextureView.getHeight());
            mCameraScanner.openCamera(this.getApplicationContext());
        }
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        mCameraScanner.setGraphicDecoder(null);
        mCameraScanner.detach();
        if (mGraphicDecoder != null) {
            mGraphicDecoder.setDecodeListener(null);
            mGraphicDecoder.detach();
        }
        super.onDestroy();
    }
}
