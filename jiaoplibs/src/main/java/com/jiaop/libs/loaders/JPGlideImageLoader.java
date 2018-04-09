package com.jiaop.libs.loaders;

import android.content.Context;
import android.widget.ImageView;

import com.jiaop.libs.utils.JPGlideUtil;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by jiaop
 * Banner的图片加载器
 */
public class JPGlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        JPGlideUtil.loadImg(context, (String) path, imageView);
    }

}
