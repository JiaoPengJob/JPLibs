# JPLibs

**开发了这么多项目，每一次都是复制粘贴，代码复用性根本没有得到提升，所以整合了经常使用的Github上各路大神的框架，进行了简单的封装，对于一般的功能和效果都可以得到实现，只是希望自己在开发新项目时，不用再一个一个找了......**

## 依赖
```Java
allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
    }
}

dependencies {
	...
	api 'com.github.JiaoPengJob:JPLibs:1.0.0'
}
```
## 使用

###1.因为使用了ButterKnife注解，所以要在开发项目中添加：
```Java
dependencies {
	...
	api 'com.jakewharton:butterknife:8.8.1'
    	annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
}
```
###2.继承JPApplication，并初始化：
- **初始化Logger输出日志**
```Java
FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true) 	//是否显示线程信息.
                .methodCount(0)        	//要显示多少种方法行.
                .methodOffset(3)       	//跳过堆栈跟踪中的一些方法调用.
                .tag("Kotlin:")        	//每个日志的自定义标签.
                .build();
Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
	@Override
        public boolean isLoggable(int priority, String tag) {
		//是否打印日志信息
                return BuildConfig.DEBUG;
        }
});
```
- **初始化Toasty提示信息**
```Java
Toasty.Config.getInstance()
    .setErrorColor() 		//Error 颜色
    .setInfoColor() 		//Info 颜色
    .setSuccessColor() 		//Success 颜色
    .setWarningColor() 		//Warning 颜色
    .setTextColor() 		//文本颜色
    .tintIcon() 		//是否显示图标 
    .setToastTypeface() 	//文本字体
    .setTextSize() 		//文本大小
    .apply();
```
- **初始化RxEasyHttp网络请求**
<br>详细配置请移步：[https://github.com/zhou-you/RxEasyHttp](https://github.com/zhou-you/RxEasyHttp)
<br>这些配置需要根据自身需求，设置在你的Application中
###3.继承JPBaseActivity，并重写：
```Java
//初始化页面布局<br>
@Override
protected void initView() {}

@Override
protected int layoutId() {
	return 0;
}

@Override
protected void initWiFiData() {}

@Override
protected void initNetData() {}

@Override
protected void initOfflineData() {}

@Override
protected int statusBarColor() {
	return 0;
}
```

