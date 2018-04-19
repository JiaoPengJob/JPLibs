package com.libs.jiaop.mvp.models;


import com.libs.jiaop.mvp.base.OnResultListener;
import com.libs.jiaop.mvp.beans.User;
import com.libs.jiaop.mvp.interfaces.IUserLogin;

/**
 * MVP
 * 业务的具体实现
 */
public class MUserLogin implements IUserLogin {

    @Override
    public void login(String phone, String pwd, OnResultListener<User> listener) {
        //调用网络请求，获取接口数据
        if (true) {
            User u = new User("xiaoming", "men");
            listener.success(u);
        } else {
            listener.error("发生错误");
        }
    }
}
