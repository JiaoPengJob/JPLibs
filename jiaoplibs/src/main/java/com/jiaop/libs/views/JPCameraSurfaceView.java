package com.jiaop.libs.views;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.jiaop.libs.utils.JPUIUtil;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by jiaop
 * 照相机显示布局
 */

public class JPCameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Camera.AutoFocusCallback {

    private static final String TAG = "JPCameraSurfaceView";
    private Context mContext;
    private SurfaceHolder holder;
    private Camera mCamera;
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;

    public JPCameraSurfaceView(Context context) {
        this(context, null);
    }

    public JPCameraSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JPCameraSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        getScreenMetrix(context);
        initView();
    }

    private void getScreenMetrix(Context context) {
        WindowManager WM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        WM.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
        mScreenHeight = outMetrics.heightPixels;
    }

    private void initView() {
        //获得surfaceHolder引用
        holder = getHolder();
        holder.addCallback(this);
        //设置类型
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mCamera == null) {
            try {
                //开启相机
                mCamera = Camera.open(0);
                //摄像头画面显示在Surface上
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setCameraParams(JPUIUtil.getScreenWidth((Activity) mContext), 360);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCamera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //停止预览
        mCamera.stopPreview();
        //释放相机资源
        mCamera.release();
        mCamera = null;
        holder = null;
    }

    @Override
    public void onAutoFocus(boolean success, Camera Camera) {
        if (success) {
        }
    }

    public void setCameraParams(int width, int height) {
        Camera.Parameters parameters = mCamera.getParameters();
        //获取摄像头支持的PictureSize列表
        List<Camera.Size> pictureSizeList = parameters.getSupportedPictureSizes();
        //从列表中选取合适的分辨率
        Camera.Size picSize = getProperSize(pictureSizeList, ((float) height / width));
        if (picSize == null) {
            picSize = parameters.getPictureSize();
        }
        //根据选出的PictureSize重新设置SurfaceView大小
        float w = picSize.width;
        float h = picSize.height;
        parameters.setPictureSize(picSize.width, picSize.height);
        //this.setLayoutParams(new FrameLayout.LayoutParams((int) (height * (h / w)), height));
        this.setLayoutParams(new FrameLayout.LayoutParams(width, height * 2));
        //获取摄像头支持的PreviewSize列表
        List<Camera.Size> previewSizeList = parameters.getSupportedPreviewSizes();
        Camera.Size preSize = getProperSize(previewSizeList, ((float) height) / width);
        if (null != preSize) {
            parameters.setPreviewSize(preSize.width, preSize.height);
        }
        //设置照片质量
        parameters.setJpegQuality(100);
        if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 连续对焦模式
        }
        //自动对焦
        mCamera.cancelAutoFocus();
        // 设置PreviewDisplay的方向，效果就是将捕获的画面旋转多少度显示
        // 这里直接设置90°不严谨，具体视情况修改
        if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
            setDisplayOrientation(mCamera, 90);
        } else {
            parameters.setRotation(90);
        }
        mCamera.setParameters(parameters);
    }

    /**
     * 实现的图像的正确显示
     *
     * @param camera
     * @param i
     */
    private void setDisplayOrientation(Camera camera, int i) {
        Method downPolymorphic;
        try {
            downPolymorphic = camera.getClass().getMethod(
                    "setDisplayOrientation", new Class[]{int.class});
            if (downPolymorphic != null) {
                downPolymorphic.invoke(camera, new Object[]{i});
            }
        } catch (Exception e) {
            Logger.e("Came_e", "图像出错");
        }
    }

    /**
     * 从列表中选取合适的分辨率
     * 默认w:h = 4:3
     * <p>tip：这里的w对应屏幕的height
     * h对应屏幕的width<p/>
     */
    private Camera.Size getProperSize(List<Camera.Size> pictureSizeList, float screenRatio) {
        Camera.Size result = null;
        for (Camera.Size size : pictureSizeList) {
            float currentRatio = ((float) size.width) / size.height;
            if (currentRatio - screenRatio == 0) {
                result = size;
                break;
            }
        }
        if (null == result) {
            for (Camera.Size size : pictureSizeList) {
                float curRatio = ((float) size.width) / size.height;
                if (curRatio == 4f / 3) {// 默认w:h = 4:3
                    result = size;
                    break;
                }
            }
        }
        return result;
    }

    public Camera getmCamera() {
        return mCamera;
    }
}
