package com.jiaop.libs.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.jiaop.libs.loaders.JPGlideAlbumLoader;
import com.luliang.shapeutils.DevShapeUtils;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.zhouyou.http.EasyHttp;

import java.util.Locale;

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
        //初始化Album相册
        Album.initialize(
                AlbumConfig.newBuilder(this)
                        .setAlbumLoader(new JPGlideAlbumLoader())
                        .setLocale(Locale.CHINA)
                        .build()
        );
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
