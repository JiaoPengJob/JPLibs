package com.jiaop.libs.pays.alipay;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

/**
 * Created by JiaoP
 * 支付宝支付
 */
public class JPAlipay {

    private String mParams;
    private PayTask mPayTask;
    private AlipayResultCallBack mCallback;

    public static final int ERROR_RESULT = 1;//支付结果解析错误
    public static final int ERROR_PAY = 2;//支付失败
    public static final int ERROR_NETWORK = 3;//网络连接错误
    private static final int SDK_PAY_FLAG = 1;//Handler进程状态码

    public interface AlipayResultCallBack {

        //支付成功
        void onSuccess();

        //正在处理中 小概率事件 此时以验证服务端异步通知结果为准
        void onDealing();

        //支付失败
        void onError(int error_code);

        //支付取消
        void onCancel();
    }

    public JPAlipay(Context context, String params, AlipayResultCallBack callback) {
        mParams = params;
        mCallback = callback;
        mPayTask = new PayTask((Activity) context);
    }

    private Handler mHandler = new Handler() {

        @SuppressWarnings("unused")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    @SuppressWarnings("unchecked")
                    JPAlipayResult payResult = new JPAlipayResult((Map<String, String>) msg.obj);

                    if (mCallback == null) {
                        return;
                    }

                    if (payResult == null) {
                        mCallback.onError(ERROR_RESULT);
                        return;
                    }

                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        //支付成功
                        mCallback.onSuccess();
                    } else if (TextUtils.equals(resultStatus, "8000")) {
                        //正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
                        mCallback.onDealing();
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        //用户中途取消
                        mCallback.onCancel();
                    } else if (TextUtils.equals(resultStatus, "6002")) {
                        //网络连接出错
                        mCallback.onError(ERROR_NETWORK);
                    } else if (TextUtils.equals(resultStatus, "4000")) {
                        //订单支付失败
                        mCallback.onError(ERROR_PAY);
                    }
                    break;
            }
        }
    };

    /**
     * 支付宝支付
     */
    public void goPay() {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                Map<String, String> result = mPayTask.payV2(mParams, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    //支付
    public void doPay() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Map<String, String> pay_result = mPayTask.payV2(mParams, true);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCallback == null) {
                            return;
                        }

                        if (pay_result == null) {
                            mCallback.onError(ERROR_RESULT);
                            return;
                        }

                        String resultStatus = pay_result.get("resultStatus");
                        if (TextUtils.equals(resultStatus, "9000")) {    //支付成功
                            mCallback.onSuccess();
                        } else if (TextUtils.equals(resultStatus, "8000")) { //支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            mCallback.onDealing();
                        } else if (TextUtils.equals(resultStatus, "6001")) {        //支付取消
                            mCallback.onCancel();
                        } else if (TextUtils.equals(resultStatus, "6002")) {     //网络连接出错
                            mCallback.onError(ERROR_NETWORK);
                        } else if (TextUtils.equals(resultStatus, "4000")) {        //支付错误
                            mCallback.onError(ERROR_PAY);
                        }
                    }
                });
            }
        }).start();
    }

}
