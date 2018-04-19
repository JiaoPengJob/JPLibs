package com.jiaop.libs.utils;

import android.text.Html;
import android.text.Spanned;

import java.util.regex.Pattern;

/**
 * Created by JiaoP
 * Web帮助类
 */
public class JPWebUtil {

    /**
     * 判断是否为有效的网站格式
     *
     * @param uri
     * @return
     */
    public static boolean isUri(String uri) {
        Pattern pattern = Pattern
                .compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
        return pattern.matcher(uri).matches();
    }

    /**
     * 转换HTML为可显示的字符串
     *
     * @param html
     * @return
     */
    public static Spanned html2Spanned(String html) {
        return Html.fromHtml(html);
    }

}
