package com.libs.jiaop;

import android.view.View;
import android.widget.Button;

import com.jiaop.libs.base.JPBaseActivity;
import com.parkingwang.vehiclekeyboard.KeyboardInputController;
import com.parkingwang.vehiclekeyboard.PopupKeyboard;
import com.parkingwang.vehiclekeyboard.core.KeyboardType;
import com.parkingwang.vehiclekeyboard.view.InputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KeyBoardActivity extends JPBaseActivity {

    private InputView mInputView;

    private final List<String> mTestNumber = new ArrayList<>();

    private final Random mRandom = new Random();

    private PopupKeyboard mPopupKeyboard;

    @Override
    protected void initView() {
        mInputView = findViewById(R.id.input_view);
        final Button lockTypeButton = findViewById(R.id.lock_type);

        mTestNumber.add("粤A12345");
        mTestNumber.add("粤BD12345");
        mTestNumber.add("粤C0");
        mTestNumber.add("粤");
        mTestNumber.add("WJ12345");
        mTestNumber.add("WJ粤12345");

        // 创建弹出键盘
        mPopupKeyboard = new PopupKeyboard(this);
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(mInputView, this);
        mPopupKeyboard.getKeyboardView()
                .setKeyboardType(KeyboardType.CIVIL_WJ);

        // KeyboardInputController提供一个默认实现的新能源车牌锁定按钮
        mPopupKeyboard.getController()
                .setDebugEnabled(true)
                .bindLockTypeProxy(new KeyboardInputController.ButtonProxyImpl(lockTypeButton) {
                    @Override
                    public void onNumberTypeChanged(boolean isNewEnergyType) {
                        super.onNumberTypeChanged(isNewEnergyType);
                        if (isNewEnergyType) {
                            lockTypeButton.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                        } else {
                            lockTypeButton.setTextColor(getResources().getColor(android.R.color.black));
                        }
                    }
                });
    }

    public void onClick(View view) {
        int id = view.getId();
        // 切换键盘类型
        switch (id) {
            case R.id.full:
                mPopupKeyboard.getKeyboardView().setKeyboardType(KeyboardType.FULL);
                // 更新车牌号码
                mPopupKeyboard.getController().updateNumber(mInputView.getNumber());
                break;
            case R.id.civil:
                mPopupKeyboard.getKeyboardView().setKeyboardType(KeyboardType.CIVIL);
                // 更新车牌号码
                mPopupKeyboard.getController().updateNumber(mInputView.getNumber());
                break;
            case R.id.civil_wj:
                mPopupKeyboard.getKeyboardView().setKeyboardType(KeyboardType.CIVIL_WJ);
                // 更新车牌号码
                mPopupKeyboard.getController().updateNumber(mInputView.getNumber());
                break;
            case R.id.test_number:
                final int idx = mRandom.nextInt(mTestNumber.size());
                if (idx == 3) {
                    mPopupKeyboard.getController()
                            .updateNumberLockType(mTestNumber.get(idx), true);
                } else {
                    mPopupKeyboard.getController()
                            .updateNumber(mTestNumber.get(idx));
                }
                break;
            case R.id.clear_number:
                mPopupKeyboard.getController()
                        .updateNumber("");
                break;
            case R.id.popup_keyboard:
                if (mPopupKeyboard.isShown()) {
                    mPopupKeyboard.dismiss(KeyBoardActivity.this);
                } else {
                    mPopupKeyboard.show(KeyBoardActivity.this);
                }
                break;
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_key_board;
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
        return R.color.red;
    }
}
