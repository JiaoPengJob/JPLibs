package com.jiaop.libs.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jiaop
 * 向SharedPreferences中读取和写入相关类型数据
 * 使用之前，需要在自己的Application中配置（必须）
 * JPSharedPreUtil.init(this, "test_sp_data");
 */
public class JPSharedPreUtil {

    private static String setting = "sp_data";
    private static JPSharedPreUtil INSTANCE;
    private static Application mApp;

    /**
     * 获取单例
     *
     * @return
     */
    public static JPSharedPreUtil getInstance() {
        if (mApp == null)
            throw new ExceptionInInitializerError("请先在Application中初始化init()");
        if (INSTANCE == null) {
            synchronized (JPSharedPreUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new JPSharedPreUtil();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 设置全局文本对象及文件名称
     *
     * @param app
     */
    public static void init(Application app, String fileName) {
        mApp = app;
        setting = fileName;
    }

    public void putValue(String key, int value) {
        SharedPreferences.Editor sp = mApp.getSharedPreferences(setting, Context.MODE_PRIVATE).edit();
        sp.putInt(key, value);
        sp.commit();
    }

    public void putValue(String key, boolean value) {
        SharedPreferences.Editor sp = mApp.getSharedPreferences(setting, Context.MODE_PRIVATE).edit();
        sp.putBoolean(key, value);
        sp.commit();
    }

    public void putValue(String key, String value) {
        SharedPreferences.Editor sp = mApp.getSharedPreferences(setting, Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.commit();
    }

    public <T> void putValue(String key, T object) {
        String objectStr = JPGsonUtil.object2Json(object);
        putValue(key, objectStr);
    }

    public int getValue(String key, int defValue) {
        SharedPreferences sp = mApp.getSharedPreferences(setting, Context.MODE_PRIVATE);
        int value = sp.getInt(key, defValue);
        return value;
    }

    public boolean getValue(String key, boolean defValue) {
        SharedPreferences sp = mApp.getSharedPreferences(setting, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }

    public String getValue(String key, String defValue) {
        SharedPreferences sp = mApp.getSharedPreferences(setting, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }

    public <T> T getValue(String key, Class<T> clazz) {
        return JPGsonUtil.json2Object(getValue(key, "no data"), clazz);
    }

    /**
     * 清除文件数据
     */
    public void clear() {
        SharedPreferences sp = mApp.getSharedPreferences(setting, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

}
