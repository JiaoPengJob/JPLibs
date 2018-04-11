package com.libs.jiaop;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.SettingService;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AndPermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_and_permission);
        ButterKnife.bind(this);
    }

    /**
     * 请求单个权限
     */
    @OnClick(R.id.btPermissionOne)
    void btPermission() {
        AndPermission
                .with(this)
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //接受
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //被拒绝
                    }
                })
                .start();
    }

    /**
     * 请求单个权限组
     */
    @OnClick(R.id.btPermissionOneGroup)
    void btPermissionOneGroup() {
        AndPermission
                .with(this)
                .permission(Permission.Group.STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //接受
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //被拒绝
                    }
                })
                .start();
    }

    /**
     * 请求多个权限
     */
    @OnClick(R.id.btPermissionMany)
    void btPermissionMany() {
        AndPermission
                .with(this)
                .permission(
                        Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.CAMERA
                )
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //接受
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //被拒绝
                    }
                })
                .start();
    }

    /**
     * 请求多个权限组
     */
    @OnClick(R.id.btPermissionManyGroup)
    void btPermissionManyGroup() {
        AndPermission
                .with(this)
                .permission(
                        Permission.Group.STORAGE,
                        Permission.Group.CAMERA
                )
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //接受
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //被拒绝
                    }
                })
                .start();
    }

    /**
     * 用户拒绝权限
     */
    private Rationale mRationale = new Rationale() {
        @Override
        public void showRationale(Context context, List<String> permissions,
                                  RequestExecutor executor) {
            // 这里使用一个Dialog询问用户是否继续授权。
            // 如果用户继续：
            executor.execute();
            // 如果用户中断：
            executor.cancel();
        }
    };

    private void rationale() {
        AndPermission
                .with(this)
                .rationale(mRationale)
                .permission(
                        Permission.Group.STORAGE,
                        Permission.Group.CAMERA
                )
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //接受
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //被拒绝
                    }
                })
                .start();
    }

    /**
     * 总是拒绝
     */
    private void alwaysRationale() {
        AndPermission
                .with(this)
                .rationale(mRationale)
                .permission(
                        Permission.Group.STORAGE,
                        Permission.Group.CAMERA
                )
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //接受
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //被拒绝
                        if (AndPermission.hasAlwaysDeniedPermission(AndPermissionActivity.this, permissions)) {
                            // 这些权限被用户总是拒绝。
                            // 这里使用一个Dialog展示没有这些权限应用程序无法继续运行，询问用户是否去设置中授权。

                            SettingService settingService = AndPermission.permissionSetting(AndPermissionActivity.this);
                            // 如果用户同意去设置：
                            settingService.execute();
                            // 如果用户不同意去设置：
                            settingService.cancel();
                        }
                    }
                })
                .start();
    }
}
