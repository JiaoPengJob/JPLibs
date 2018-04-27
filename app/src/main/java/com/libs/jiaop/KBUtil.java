package com.libs.jiaop;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.widget.EditText;

import com.jiaop.libs.utils.JPKeyboardUtil;

/**
 * <pre>
 *     author : jiaop
 *     time   : 2018/04/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class KBUtil {

    private Context mContext;
    private KeyboardView mKeyboardView;
    private Keyboard mKeyboard;

    public KBUtil(Context context, KeyboardView keyboardView) {
        mContext = context;
        mKeyboard = new Keyboard(mContext, R.xml.letters_kb);
        mKeyboardView = keyboardView;
        mKeyboardView.setKeyboard(mKeyboard);
        //设置监听
        mKeyboardView.setOnKeyboardActionListener(mListener);
    }

    /**
     * 键盘监听
     */
    private KeyboardView.OnKeyboardActionListener mListener = new KeyboardView.OnKeyboardActionListener() {

        /**
         * 按下触发
         * @param primaryCode
         */
        @Override
        public void onPress(int primaryCode) {

        }

        /**
         * 松开触发
         * @param primaryCode
         */
        @Override
        public void onRelease(int primaryCode) {

        }

        /**
         * 松开触发，在OnRelease之前触发
         * @param primaryCode
         * @param keyCodes
         */
        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            if (primaryCode == -5) {
                mKeyboardView.setPreviewEnabled(false);
                mOnPClick.getLabel("delete");
            } else if (primaryCode == -3) {
                mKeyboardView.setPreviewEnabled(false);
                hideKeyboard();
            } else {
                //效果图按住出现的预览图
                mKeyboardView.setPreviewEnabled(true);
                mOnPClick.getLabel(mKeyboard.getKeys().get(primaryCode).label.toString());
            }
        }

        /**
         * 自定义xml的keyOutputText的值
         * @param text
         */
        @Override
        public void onText(CharSequence text) {

        }

        /**
         * 左滑动
         */
        @Override
        public void swipeLeft() {

        }

        /**
         * 右滑动
         */
        @Override
        public void swipeRight() {

        }

        /**
         * 下滑动
         */
        @Override
        public void swipeDown() {

        }

        /**
         * 上滑动
         */
        @Override
        public void swipeUp() {

        }
    };

    public void showKeyboard(EditText view) {
        JPKeyboardUtil.hideKeyboard(view);
        mKeyboardView.setVisibility(View.VISIBLE);
    }

    public void hideKeyboard() {
//        JPKeyboardUtil.hideKeyboard(view);
        mKeyboardView.setVisibility(View.GONE);
    }

    private onPClick mOnPClick;

    public void setOnPClick(onPClick onPClick) {
        mOnPClick = onPClick;
    }

    public interface onPClick {
        void getLabel(String label);
    }

}
