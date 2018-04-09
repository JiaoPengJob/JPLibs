package com.jiaop.libs.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by JiaoP
 * 软键盘帮助类
 */
public class JPKeyboardUtil {

    /**
     * 获取软键盘系统服务
     *
     * @param view
     * @return
     */
    public static InputMethodManager getInputManager(View view) {
        return (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * 显示软键盘
     *
     * @param view
     */
    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 切换软键盘的弹出或隐藏
     *
     * @param view
     */
    public void toggleKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0, 0);
        }
    }

}
