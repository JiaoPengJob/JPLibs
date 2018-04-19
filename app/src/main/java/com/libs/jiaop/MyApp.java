package com.libs.jiaop;

import com.jiaop.libs.base.JPApplication;
import com.jiaop.libs.utils.JPSharedPreUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class MyApp extends JPApplication {

    @Override
    public void onCreate() {
        super.onCreate();

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

        JPSharedPreUtil.init(this, "test_sp_data");

    }
}
