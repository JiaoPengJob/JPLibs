1.Log
orhanobut/logger
https://github.com/orhanobut/logger
配置：
FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
        .showThreadInfo(true)    //是否显示线程信息.
        .methodCount(0)            //要显示多少种方法行.
        .methodOffset(3)        //跳过堆栈跟踪中的一些方法调用.
        .tag("MyApp:")            //每个日志的自定义标签.
        .build();
Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
    @Override
    public boolean isLoggable(int priority, String tag) {
        //是否打印日志信息
        return BuildConfig.DEBUG;
    }
});

2.Toast
GrenderG/Toasty
https://github.com/GrenderG/Toasty
配置：
Toasty.Config.getInstance()
    .setErrorColor(@ColorInt int errorColor) // optional
    .setInfoColor(@ColorInt int infoColor) // optional
    .setSuccessColor(@ColorInt int successColor) // optional
    .setWarningColor(@ColorInt int warningColor) // optional
    .setTextColor(@ColorInt int textColor) // optional
    .tintIcon(boolean tintIcon) // optional (apply textColor also to the icon)
    .setToastTypeface(@NonNull Typeface typeface) // optional
    .setTextSize(int sizeInSp) // optional
    .apply();

3.权限
yanzhenjie/AndPermission
http://www.yanzhenjie.com/AndPermission/cn/

4.沉浸式状态栏和沉浸式导航栏管理
gyf-dev/ImmersionBar
https://github.com/gyf-dev/ImmersionBar

5.设置Shape样式、Selector触摸反馈效果
LiangLuDev/DevShapeUtils
https://github.com/LiangLuDev/DevShapeUtils

6.Adapter
hongyangAndroid/baseAdapter
https://github.com/hongyangAndroid/baseAdapter

7.蓝牙快速开发框架
Jasonchenlijian/FastBle
https://github.com/Jasonchenlijian/FastBle

8.图片轮播控件
youth5201314/banner
https://github.com/youth5201314/banner

9.Json解析
google/gson
https://github.com/google/gson

10.WebView
Justson/AgentWeb
https://github.com/Justson/AgentWeb

11.滑动菜单
TUBB/SwipeMenu
https://github.com/TUBB/SwipeMenu

12.PopupWindow
pinguo-zhouwei/CustomPopwindow
https://github.com/pinguo-zhouwei/CustomPopwindow

13.相册
yanzhenjie/Album
https://github.com/yanzhenjie/Album/blob/master/README-CN.md

14.网络请求
zhou-you/RxEasyHttp
https://github.com/zhou-you/RxEasyHttp

15.底部菜单栏
peng8350/JPTabBar
https://github.com/peng8350/JPTabBar

16.图片压缩
Curzibn/Luban
https://github.com/Curzibn/Luban

17.数据库
realm/realm-java
https://github.com/realm/realm-java
https://www.jianshu.com/p/37af717761cc

18.MVP
https://blog.csdn.net/c10WTiybQ1Ye3/article/details/79787116

