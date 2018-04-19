package com.libs.jiaop.mvp.views;

import com.libs.jiaop.mvp.beans.User;

/**
 * MVP
 * 只用来和用户交互，应该是一个页面对应一个View
 */
public interface IUserLoginView {

    String getName();

    String getPwd();

    void showSuccessData(User user);

    void showErrorData(String error);

}
