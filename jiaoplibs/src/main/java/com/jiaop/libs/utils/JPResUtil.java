package com.jiaop.libs.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by JiaoP
 * 获取资源文件帮助类
 */
public class JPResUtil {

    /**
     * 获取drawable的路径
     *
     * @param context 文本对象
     * @param id      资源id
     * @return
     */
    public static String getDrawablePath(Context context, int id) {
        return ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + context.getResources().getResourcePackageName(id) + "/"
                + context.getResources().getResourceTypeName(id) + "/"
                + context.getResources().getResourceEntryName(id);
    }

    /**
     * 获取drawable的uri
     *
     * @param context 文本对象
     * @param id      资源id
     * @return
     */
    public static Uri getDrawableUri(Context context, int id) {
        return Uri.parse(getDrawablePath(context, id));
    }

    /**
     * 读取TXT文件内容
     *
     * @param context  文本对象
     * @param fileName 文件名（包含后缀名）
     * @return
     */
    public static String readAssetsFile(Context context, String fileName) {
        InputStream is = null;
        String msg = null;
        try {
            is = context.getResources().getAssets().open(fileName);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            msg = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }

    /**
     * 获取Raw文件夹绝对路径
     *
     * @param context 文本对象
     * @param id      资源id
     * @return
     */
    public static String getRawPath(Context context, int id) {
        return "android.resource://" + context.getPackageName() + "/" + id;
    }

    /**
     * 获取Raw文件夹Uri
     *
     * @param context 文本对象
     * @param id      资源id
     * @return
     */
    public static Uri getRawUri(Context context, int id) {
        String path = getRawPath(context, id);
        return Uri.parse(path);
    }

}
