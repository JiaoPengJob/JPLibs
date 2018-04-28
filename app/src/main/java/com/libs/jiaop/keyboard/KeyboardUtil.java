package com.libs.jiaop.keyboard;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;

import com.jiaop.libs.utils.JPKeyboardUtil;
import com.libs.jiaop.R;

/**
 * <pre>
 *     author : jiaop
 *     time   : 2018/04/27
 *     desc   : 自定义键盘帮助类
 *     version: 1.0
 * </pre>
 */
public class KeyboardUtil {

    private static KeyboardUtil INSTANCE;
    private static final String TAG = "KeyboardUtil";
    private static final int TYPE_EDIT = 0;
    private static final int TYPE_Text = 1;

    private Activity mActivity;
    private EditText mEditText;
    private TextView mTextView;
    private KeyboardView mKeyboardView;
    private Keyboard mKeyboard;
    private int inputType = 0;

    public KeyboardUtil() {

    }

    /**
     * 获取单例
     *
     * @return
     */
    public static KeyboardUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (KeyboardUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new KeyboardUtil();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 启用输入框的功能
     *
     * @param activity
     * @param editText
     * @param keyboardView
     */
    public void initEditViewFeatures(Activity activity, EditText editText, KeyboardView keyboardView) {
        this.mActivity = activity;
        this.mEditText = editText;
        this.mKeyboardView = keyboardView;
        if (mEditText != null) {
            inputType = TYPE_EDIT;
            initLetterKeyboard();
        } else {
            Log.e(TAG, "EditText can not is null!");
        }
    }

    /**
     * 启用文本的功能
     *
     * @param activity
     * @param textView
     * @param keyboardView
     */
    public void initTextViewFeatures(Activity activity, TextView textView, KeyboardView keyboardView) {
        this.mActivity = activity;
        this.mTextView = textView;
        this.mKeyboardView = keyboardView;
        if (mTextView != null) {
            inputType = TYPE_Text;
            initProvincesKeyboard();
        } else {
            Log.e(TAG, "TextView can not is null!");
        }
    }

    /**
     * 初始化字母键盘
     */
    private void initLetterKeyboard() {
        mKeyboard = null;
        mKeyboard = new Keyboard(mActivity, R.xml.letters_kb);
        mKeyboardView.setKeyboard(mKeyboard);
        mKeyboardView.setOnKeyboardActionListener(mListener);
    }

    /**
     * 初始化省份键盘
     */
    private void initProvincesKeyboard() {
        mKeyboard = null;
        mKeyboard = new Keyboard(mActivity, R.xml.car_provinces);
        mKeyboardView.setKeyboard(mKeyboard);
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
            switch (inputType) {
                case TYPE_EDIT:
                    String editStr = mEditText.getText().toString();
                    if (primaryCode == -5) {
                        mKeyboardView.setPreviewEnabled(false);
                        if (editStr.length() != 0) {
                            mEditText.setText(editStr.substring(0, editStr.length() - 1));
                        }
                    } else if (primaryCode == -3) {
                        mKeyboardView.setPreviewEnabled(false);
                        hideKeyboard();
                    } else {
                        mKeyboardView.setPreviewEnabled(true);
                        mEditText.setText(editStr + mKeyboard.getKeys().get(primaryCode).label.toString());
                    }
                    break;
                case TYPE_Text:
                    if (primaryCode == -3) {
                        mKeyboardView.setPreviewEnabled(false);
                        hideKeyboard();
                    } else {
                        mKeyboardView.setPreviewEnabled(true);
                        mTextView.setText(mKeyboard.getKeys().get(primaryCode).label.toString());
                    }
                    break;
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

    /**
     * 显示TextView的键盘
     */
    public void showTextKeyboard() {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        animation.setDuration(500);
        mKeyboardView.startAnimation(animation);
        mKeyboardView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示EditText的键盘
     *
     * @param view
     */
    public void showEditKeyboard(EditText view) {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        animation.setDuration(500);
        mKeyboardView.startAnimation(animation);
        JPKeyboardUtil.hideKeyboard(view);
        mKeyboardView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        animation.setDuration(500);
        mKeyboardView.startAnimation(animation);
        mKeyboardView.setVisibility(View.GONE);
    }

}
