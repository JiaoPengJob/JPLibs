package com.libs.jiaop;

import android.widget.EditText;

import com.jiaop.libs.base.JPBaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class SecActivity extends JPBaseActivity {

    @BindView(R.id.etEventSendMsg)
    EditText mEtEventSendMsg;

    @OnClick(R.id.btnSendMsg)
    void btnSendMsg() {
        //发送事件
        EventBus.getDefault().post(new UserEvent(mEtEventSendMsg.getText().toString().trim(), 24));
    }

    @OnClick(R.id.btnGetMsg)
    void btnGetMsg() {
        EventBus.getDefault().register(SecActivity.this);
    }

    //定义处理接收的方法
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userSecEventBus(UserEvent userEvent) {
        mEtEventSendMsg.setText(userEvent.getName() + "_" + userEvent.getAge());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_sec;
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
        return R.color.brown;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
