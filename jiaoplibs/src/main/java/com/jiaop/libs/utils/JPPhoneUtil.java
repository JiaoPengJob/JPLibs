package com.jiaop.libs.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JiaoP
 * 手机帮助类
 */
public class JPPhoneUtil {

    /**
     * 隐藏手机中间4位号码
     * 130****0000
     *
     * @param mobile_phone 手机号码
     * @return 130****0000
     */
    public static String hideMobilePhone4(String mobile_phone) {
        if (mobile_phone.length() != 11) {
            return "手机号码不正确";
        }
        return mobile_phone.substring(0, 3) + "****" + mobile_phone.substring(7, 11);
    }

    /**
     * 判断手机号码格式
     * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
     * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
     * 电信号段: 133,149,153,170,173,177,180,181,189
     *
     * @param mobileNums
     * @return
     */
    public static boolean isMobileNums(String mobileNums) {
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public static void diallPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param context
     * @param phoneNum
     */
    @SuppressLint("MissingPermission")
    public static void callPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机身份证IMEI
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * 获取服务商名称
     *
     * @param context
     * @return
     */
    public static String getSimOperatorName(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimOperatorName();
    }

    /**
     * 获取手机联系人
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>}</p>
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.READ_CONTACTS"/>}</p>
     *
     * @param context 上下文;
     * @return 联系人列表
     */
    @SuppressLint("MissingPermission")
    public static List<HashMap<String, String>> getAllContactInfo(Context context) {
        SystemClock.sleep(3000);
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        ContentResolver resolver = context.getContentResolver();
        Uri raw_uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri date_uri = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = resolver.query(raw_uri, new String[]{"contact_id"},
                null, null, null);
        while (cursor.moveToNext()) {
            String contact_id = cursor.getString(0);
            if (!TextUtils.isEmpty(contact_id)) {
                Cursor c = resolver.query(date_uri, new String[]{"data1",
                                "mimetype"}, "raw_contact_id=?",
                        new String[]{contact_id}, null);
                HashMap<String, String> map = new HashMap<String, String>();
                while (c.moveToNext()) {
                    String data1 = c.getString(0);
                    String mimetype = c.getString(1);
                    if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
                        map.put("phone", data1);
                    } else if (mimetype.equals("vnd.android.cursor.item/name")) {
                        map.put("name", data1);
                    }
                }
                list.add(map);
                c.close();
            }
        }
        cursor.close();
        return list;
    }

    /**
     * 发送短信
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     * @param content     内容
     */
    public static void sendSms(Context context, String phoneNumber, String content) {
        Uri uri = Uri.parse("smsto:" + (TextUtils.isEmpty(phoneNumber) ? "" : phoneNumber));
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", TextUtils.isEmpty(content) ? "" : content);
        context.startActivity(intent);
    }

}
