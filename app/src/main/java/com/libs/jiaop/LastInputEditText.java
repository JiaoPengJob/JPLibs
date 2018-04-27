package com.libs.jiaop;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * <pre>
 *     author : jiaop
 *     time   : 2018/04/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class LastInputEditText extends AppCompatEditText {

    public LastInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LastInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LastInputEditText(Context context) {
        super(context);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //保证光标始终在最后面
        if (selStart == selEnd) {//防止不能多选
            setSelection(getText().length());
        }

    }

}
