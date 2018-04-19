package com.libs.jiaop.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.libs.jiaop.R;
import com.libs.jiaop.mvp.beans.User;
import com.libs.jiaop.mvp.presenters.LoginPresenter;
import com.libs.jiaop.mvp.views.IUserLoginView;

public class MvpActivity extends AppCompatActivity implements IUserLoginView {

    TextView tvResult;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        tvResult = findViewById(R.id.tvResult);

        loginPresenter = new LoginPresenter(this);

        findViewById(R.id.btLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public String getName() {
        return "123";
    }

    @Override
    public String getPwd() {
        return "123";
    }

    @Override
    public void showSuccessData(User user) {
        tvResult.setText(user.getName() + " -- " + user.getSex());
    }

    @Override
    public void showErrorData(String error) {
        tvResult.setText("error -- " + error);
    }
}
