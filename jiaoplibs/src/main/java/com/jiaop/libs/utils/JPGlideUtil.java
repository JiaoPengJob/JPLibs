package com.jiaop.libs.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.MultiTransformation;
import com.jiaop.libs.R;
import com.jiaop.libs.module.GlideApp;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by JiaoP
 * 图片加载帮助类
 * 加载Bitmap类型图片时，只能在UI中直接调用GlideApp进行获取，详见示例代码
 */

public class JPGlideUtil {
    /**
     * 简单的加载显示到ImageView中
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImg(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .into(imageView);
    }

    /**
     * 简单的加载显示到ImageView中
     *
     * @param context
     * @param imgId
     * @param imageView
     */
    public static void loadImg(Context context, int imgId, ImageView imageView) {
        GlideApp.with(context)
                .load(imgId)
                .into(imageView);
    }

    /**
     * 简单的加载显示到ImageView中
     *
     * @param context
     * @param file
     * @param imageView
     */
    public static void loadImg(Context context, File file, ImageView imageView) {
        GlideApp.with(context)
                .load(file)
                .into(imageView);
    }

    /**
     * 加载带有占位图的图片到ImageView
     *
     * @param context
     * @param url
     * @param imageView
     * @param placeholderId
     */
    public static void loadImgByPlaceholder(Context context, String url, ImageView imageView, int placeholderId) {
        GlideApp.with(context)
                .load(url)
                .placeholder(placeholderId)
                .into(imageView);
    }

    /**
     * 加载带有占位图的图片到ImageView
     *
     * @param context
     * @param imgId
     * @param imageView
     * @param placeholderId
     */
    public static void loadImgByPlaceholder(Context context, int imgId, ImageView imageView, int placeholderId) {
        GlideApp.with(context)
                .load(imgId)
                .placeholder(placeholderId)
                .into(imageView);
    }

    /**
     * 加载带有占位图的图片到ImageView
     *
     * @param context
     * @param file
     * @param imageView
     * @param placeholderId
     */
    public static void loadImgByPlaceholder(Context context, File file, ImageView imageView, int placeholderId) {
        GlideApp.with(context)
                .load(file)
                .placeholder(placeholderId)
                .into(imageView);
    }

    /**
     * 加载带有错误符的图片到ImageView
     *
     * @param context
     * @param url
     * @param imageView
     * @param errorId
     */
    public static void loadImgByError(Context context, String url, ImageView imageView, int errorId) {
        GlideApp.with(context)
                .load(url)
                .error(errorId)
                .into(imageView);
    }

    /**
     * 加载带有错误符的图片到ImageView
     *
     * @param context
     * @param imgId
     * @param imageView
     * @param errorId
     */
    public static void loadImgByError(Context context, int imgId, ImageView imageView, int errorId) {
        GlideApp.with(context)
                .load(imgId)
                .error(errorId)
                .into(imageView);
    }

    /**
     * 加载带有错误符的图片到ImageView
     *
     * @param context
     * @param file
     * @param imageView
     * @param errorId
     */
    public static void loadImgByError(Context context, File file, ImageView imageView, int errorId) {
        GlideApp.with(context)
                .load(file)
                .error(errorId)
                .into(imageView);
    }

    /**
     * 加载带有占位图和错误符的图片到ImageView
     *
     * @param context
     * @param url
     * @param imageView
     * @param placeholderId
     * @param errorId
     */
    public static void loadImgByPlaceholderAndError(Context context, String url, ImageView imageView, int placeholderId, int errorId) {
        GlideApp.with(context)
                .load(url)
                .placeholder(placeholderId)
                .error(errorId)
                .into(imageView);
    }

    /**
     * 加载带有占位图和错误符的图片到ImageView
     *
     * @param context
     * @param imgId
     * @param imageView
     * @param placeholderId
     * @param errorId
     */
    public static void loadImgByPlaceholderAndError(Context context, int imgId, ImageView imageView, int placeholderId, int errorId) {
        GlideApp.with(context)
                .load(imgId)
                .placeholder(placeholderId)
                .error(errorId)
                .into(imageView);
    }

    /**
     * 加载带有占位图和错误符的图片到ImageView
     *
     * @param context
     * @param file
     * @param imageView
     * @param placeholderId
     * @param errorId
     */
    public static void loadImgByPlaceholderAndError(Context context, File file, ImageView imageView, int placeholderId, int errorId) {
        GlideApp.with(context)
                .load(file)
                .placeholder(placeholderId)
                .error(errorId)
                .into(imageView);
    }

    /**
     * 加载GIF格式图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadGif(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .asGif()
                .load(url)
                .error(R.mipmap.placeholder)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImg(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .apply(bitmapTransform(new CropCircleTransformation()))
                .into(imageView);
    }

    /**
     * 加载毛玻璃，高斯模糊效果图
     *
     * @param context
     * @param url
     * @param imageView
     * @param radius
     */
    public static void loadBlurImg(Context context, String url, ImageView imageView, int radius) {
        GlideApp.with(context)
                .load(url)
                .apply(bitmapTransform(new BlurTransformation(radius)))
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param radius
     * @param margin
     * @param cornerType
     */
    public static void loadCornersImg(Context context, String url, ImageView imageView, int radius, int margin, RoundedCornersTransformation.CornerType cornerType) {
        GlideApp.with(context)
                .load(url)
                .apply(bitmapTransform(
                        new RoundedCornersTransformation(radius, margin, cornerType)))
                .into(imageView);
    }

    /**
     * 加载圆形高斯模糊图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param blurRadius
     */
    public static void loadCircleAndBlurImg(Context context, String url, ImageView imageView, int blurRadius) {
        MultiTransformation multi = new MultiTransformation(
                new BlurTransformation(blurRadius),
                new CropCircleTransformation());
        GlideApp.with(context)
                .load(url)
                .apply(bitmapTransform(multi))
                .into(imageView);
    }

    /**
     * 加载圆角高斯模糊图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param blurRadius
     * @param cornersRadius
     * @param margin
     */
    public static void loadCornersAndBlurImg(Context context, String url, ImageView imageView, int blurRadius, int cornersRadius, int margin) {
        MultiTransformation multi = new MultiTransformation(
                new BlurTransformation(blurRadius),
                new RoundedCornersTransformation(cornersRadius, margin, RoundedCornersTransformation.CornerType.ALL));
        GlideApp.with(context)
                .load(url)
                .apply(bitmapTransform(multi))
                .into(imageView);
    }

    /******************************示例代码*******************************/
    /**
     * 获取Bitmap
     */
    /* GlideApp.with(context)
              .asBitmap()
              .load(url)
              .into(new SimpleTarget<Bitmap>() {
                  @Override
                  public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                  }
              });
     */

}
