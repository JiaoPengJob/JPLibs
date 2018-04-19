package com.libs.jiaop.mvp.presenters;

import com.libs.jiaop.mvp.base.OnResultListener;
import com.libs.jiaop.mvp.beans.User;
import com.libs.jiaop.mvp.interfaces.IUserLogin;
import com.libs.jiaop.mvp.models.MUserLogin;
import com.libs.jiaop.mvp.views.IUserLoginView;

/**
 * MVP
 * 从View层获取用户交互的数据，传递到Model层进行处理，再回调给用户展示
 */
public class LoginPresenter {

    private IUserLogin iUserLogin;
    private IUserLoginView iUserLoginView;

    public LoginPresenter(IUserLoginView iUserLoginView) {
        this.iUserLoginView = iUserLoginView;
        iUserLogin = new MUserLogin();
    }

    public void login() {
        iUserLogin.login(iUserLoginView.getName(), iUserLoginView.getPwd(), new OnResultListener<User>() {
            @Override
            public void success(User result) {
                iUserLoginView.showSuccessData(result);
            }

            @Override
            public void error(String error) {
                iUserLoginView.showErrorData(error);
            }
        });
    }
}
