package com.jiaop.libs.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.luliang.shapeutils.DevShapeUtils;
import com.zhouyou.http.EasyHttp;

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
        //初始化RxEasyHttp
        EasyHttp.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
