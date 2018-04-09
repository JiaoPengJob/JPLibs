package com.jiaop.libs.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by JiaoP
 * View操作或显示工具类
 */
public class JPViewUtil {

    /**
     * 输入框显示金额，格式为：0.01
     * 需要为该EditText设置：android:inputType="number|numberDecimal"
     *
     * @param editText
     */
    public static void showAmountEditText(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String temp = editable.toString();
                if (temp.length() == 2) {
                    if (temp.indexOf("0") == 0) {
                        if (!temp.substring(1).equals(".")) {
                            editText.setText("0");
                            editText.setSelection(editText.length());
                        }
                    }
                }
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2) {
                    editable.delete(posDot + 3, posDot + 4);
                }
            }
        });
    }



}
