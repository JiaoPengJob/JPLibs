package com.jiaop.libs.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by JiaoP
 * Activity工具类
 */
public class JPActivityUtil {

    /**
     * 简单的打开一个Activity
     *
     * @param context
     * @param cls
     */
    public static void startNewActivity(Context context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    /**
     * 打开一个新的Activity并传递参数
     *
     * @param context
     * @param bundle
     * @param cls
     */
    public static void startNewActivityWithExtra(Context context, Bundle bundle, Class<?> cls) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    /**
     * 打开一个新的Activity并且移除之前所有的Activity
     *
     * @param context
     * @param cls
     */
    public static void startActivityClearTask(Context context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 打开一个新的Activity传递参数，并且移除之前所有的Activity
     *
     * @param context
     * @param bundle
     * @param cls
     */
    public static void startActivityClearTaskWithExtra(Context context, Bundle bundle, Class<?> cls) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private static Stack<Activity> activityStack;
    private static JPActivityUtil instance;

    /**
     * 获取单例
     *
     * @return
     */
    public static JPActivityUtil getInstance() {
        if (instance == null) {
            synchronized (JPActivityUtil.class) {
                if (instance == null) {
                    instance = new JPActivityUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 获取当前所有Activity
     *
     * @return
     */
    public List<Activity> getAllActivity() {
        List<Activity> list = new ArrayList<Activity>();
        for (Activity activity : activityStack) {
            list.add(activity);
        }
        return list;
    }

    /**
     * 添加Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除指定Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.remove(activity);
    }

    /**
     * 获取当前活动的Activity
     *
     * @return
     */
    public Activity getCurrentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前活动的Activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定Activity的类
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (activityStack != null && activityStack.size() > 0) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }

    /**
     * 退出到系统
     *
     * @param context
     */
    public void exitActivity(Context context) {
        try {
            finishAllActivity();
            if (context != null) {
                ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                activityMgr.restartPackage(context.getPackageName());
            }
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
