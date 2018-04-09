package com.jiaop.libs.base;

import android.app.Application;

import com.luliang.shapeutils.DevShapeUtils;

/**
 * Created by jiaop
 * Application基类
 */

public abstract class JPApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //设置圆角样式，用来替代shape和selector
        DevShapeUtils.init(this);
    }

}
