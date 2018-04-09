package com.jiaop.libs.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JiaoP
 * 集合操作帮助类
 */
public class JPListUtil {

    /**
     * 获取集合长度
     *
     * @param mList
     * @param <V>
     * @return
     */
    public static <V> int getSize(List<V> mList) {
        return mList == null ? 0 : mList.size();
    }

    /**
     * 判断List是否为空或长度为0
     *
     * @param sourceList
     * @param <V>
     * @return
     */
    public static <V> boolean isEmpty(List<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }

    /**
     * List转换为字符串，并以固定分隔符分割
     *
     * @param list
     * @param separator
     * @return
     */
    public static String join(List<String> list, char separator) {
        return join(list, new String(new char[]{separator}));
    }

    public static String join(List<String> list, String separator) {
        return list == null ? "" : TextUtils.join(separator, list);
    }

    /**
     * 去除集合中重复的字符串
     *
     * @param list
     * @return
     */
    public static ArrayList getSingleStrings(ArrayList list) {
        ArrayList newList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (!newList.contains(obj)) {
                newList.add(obj);
            }
        }
        return newList;
    }

    /**
     * 去除重复元素
     *
     * @param list
     * @return
     */
    public static ArrayList getSingleElements(ArrayList list) {
        ArrayList newList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (!newList.contains(obj)) {
                newList.add(obj);
            }
        }
        return newList;
    }

}
