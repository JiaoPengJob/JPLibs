package com.jiaop.libs.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jiaop.libs.interfaces.JPNetworkChangeInterface;
import com.jiaop.libs.utils.JPNetWorkUtil;

/**
 * Created by jiaop
 * 网络监听广播接收器
 */

public class JPNetworkChangeBroadcast extends BroadcastReceiver {

    private JPNetworkChangeInterface mJPNetworkChangeInterface;

    public JPNetworkChangeBroadcast(JPNetworkChangeInterface JPNetworkChangeInterface) {
        this.mJPNetworkChangeInterface = JPNetworkChangeInterface;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //有网络链接
        if (JPNetWorkUtil.isNetworkConnected(context)) {
            mJPNetworkChangeInterface.networkChange(JPNetWorkUtil.getNetType(context));
        }
        //无网络链接
        else {
            mJPNetworkChangeInterface.networkChange(0);
        }
    }
}
