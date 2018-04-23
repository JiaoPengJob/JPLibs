package com.libs.jiaop;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.jiaop.libs.base.JPBaseActivity;
import com.jiaop.libs.utils.JPAppUtil;
import com.jiaop.libs.utils.JPSharedPreUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends JPBaseActivity {

    private static String TAG = "MainActivity";

    private Timer timer;
    private TimerTask task;

    @BindView(R.id.tvEventMsg)
    TextView tvEventMsg;

    @OnClick(R.id.btLog)
    void logShow() {
//        Logger.wtf("Show Root == " + JPPhoneUtil.isRoot());
//        JPToastUtil.error(this, "Info");
        String sha1 = JPAppUtil.getSHA1(this);
        Logger.wtf("SHA1 == " + sha1);//SHA! == B9:DA:98:1E:B5:8E:FC:CC:3E:EB:FF:B4:64:36:91:4C:66:7F:F8:59
    }

    @OnClick(R.id.btP)
    void btP() {
        User u = new User("小明", "男");
        JPSharedPreUtil.getInstance().putValue("user", u);
    }

    @OnClick(R.id.btR)
    void btR() {
        User u = JPSharedPreUtil.getInstance().getValue("user", User.class);
        Log.e(TAG, "user name == " + u.getName() + " / user sex == " + u.getSex());
    }

    @OnClick(R.id.btU)
    void btU() {
        User u = new User("测试", "修改");
        JPSharedPreUtil.getInstance().putValue("user", u);
    }

    int time = 10;

    @OnClick(R.id.btnSend)
    void btnSend() {
        EventBus.getDefault().postSticky(new UserEvent("时间", time++));
        startActivity(new Intent(MainActivity.this, SecActivity.class));
//        timer = new Timer();
//        task = new TimerTask() {
//            @Override
//            public void run() {
//                //发送事件
//                EventBus.getDefault().post(new UserEvent("时间", time++));
//            }
//        };
//        timer.schedule(task, 0, 1000);
    }

    @Override
    protected void initView() {
        //注册
//        EventBus.getDefault().register(MainActivity.this);

        MyBuilder myBuilder = new MyBuilder.Builder(this)
                .name("")
                .age(24)
                .build();
    }

    //定义处理接收的方法
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void userEventBus(UserEvent userEvent) {
//        tvEventMsg.setText(userEvent.getName() + " -- " + userEvent.getAge());
//    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWiFiData() {

    }

    @Override
    protected void initNetData() {

    }

    @Override
    protected void initOfflineData() {

    }

    @Override
    protected int statusBarColor() {
        return R.color.colorPrimary;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销注册
//        EventBus.getDefault().unregister(this);
    }
}
