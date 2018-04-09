package com.jiaop.libs.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by jiaop
 * UI界面工具类
 */
public class JPUIUtil {

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Activity context) {
        if (context == null)
            return 0;
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Activity context) {
        if (context == null)
            return 0;
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * dp转换为px
     *
     * @param context
     * @param dp
     * @return
     */
    public static float dp2Px(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }

    /**
     * px转换为dp
     *
     * @param context
     * @param px
     * @return
     */
    public static float px2Dp(Context context, float px) {
        if (context == null) {
            return -1;
        }
        return px / context.getResources().getDisplayMetrics().density;
    }

    /**
     * dp转换为px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2PxInt(Context context, float dp) {
        return (int) (dp2Px(context, dp) + 0.5f);
    }

    /**
     * px转换为dp
     *
     * @param context
     * @param px
     * @return
     */
    public static int px2DpCeilInt(Context context, float px) {
        return (int) (px2Dp(context, px) + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context
     * @param spValue
     * @return int
     */
    public static int sp2pxInt(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context
     * @param spValue
     * @return float
     */
    public static float sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return spValue * fontScale + 0.5f;
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context
     * @param pxValue
     * @return int
     */
    public static int px2spInt(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context
     * @param pxValue
     * @return float
     */
    public static float px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return pxValue / fontScale + 0.5f;
    }

}
