package com.libs.jiaop.mvp.interfaces;


import com.libs.jiaop.mvp.base.OnResultListener;
import com.libs.jiaop.mvp.beans.User;

/**
 * MVP
 * 定义业务接口
 * 可以根据App功能模块编写，例如：用户信息为一个接口，商城为一个接口
 */
public interface IUserLogin {

    void login(String phone, String pwd, OnResultListener<User> listener);

}
