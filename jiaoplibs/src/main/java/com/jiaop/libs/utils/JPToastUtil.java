package com.jiaop.libs.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * Created by jiaop
 * Toast帮助类
 * 在Application中进行相关配置：
 * Toasty.Config.getInstance()
 * .setErrorColor(@ColorInt int errorColor)
 * .setInfoColor(@ColorInt int infoColor)
 * .setSuccessColor(@ColorInt int successColor)
 * .setWarningColor(@ColorInt int warningColor)
 * .setTextColor(@ColorInt int textColor)
 * .tintIcon(boolean tintIcon)
 * .setToastTypeface(@NonNull Typeface typeface)
 * .setTextSize(int sizeInSp)
 * .apply();
 */

public class JPToastUtil {

    /**
     * error
     * 红色
     *
     * @param context
     * @param info
     */
    public static void error(Context context, String info) {
        Toasty.error(context, info, Toast.LENGTH_SHORT, false).show();
    }

    /**
     * success
     * 绿色
     *
     * @param context
     * @param info
     */
    public static void success(Context context, String info) {
        Toasty.success(context, info, Toast.LENGTH_SHORT, false).show();
    }

    /**
     * info
     * 蓝色
     *
     * @param context
     * @param info
     */
    public static void info(Context context, String info) {
        Toasty.info(context, info, Toast.LENGTH_SHORT, false).show();
    }

    /**
     * warning
     * 黄色
     *
     * @param context
     * @param info
     */
    public static void warning(Context context, String info) {
        Toasty.warning(context, info, Toast.LENGTH_SHORT, false).show();
    }

    /**
     * 默认
     * 黑底白字
     *
     * @param context
     * @param info
     */
    public static void normal(Context context, String info) {
        Toasty.normal(context, info).show();
    }

    /**
     * 自定义黑底白字和icon
     *
     * @param context
     * @param info
     * @param icon
     */
    public static void iconNormal(Context context, String info, Drawable icon) {
        Toasty.normal(context, info, icon).show();
    }

    /**
     * 自定义
     *
     * @param context
     * @param info
     * @param icon
     * @param tintColor
     * @param duration
     * @param withIcon
     * @param shouldTint
     */
    public static void custom(Context context, String info, Drawable icon, int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        Toasty.custom(context, info, icon, tintColor, duration, withIcon, shouldTint).show();
    }

}
