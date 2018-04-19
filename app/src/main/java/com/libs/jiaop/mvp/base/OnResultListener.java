package com.libs.jiaop.mvp.base;

/**
 * MVP
 * 网络请求回调基类
 *
 * @param <T> 成功返回的数据
 */
public interface OnResultListener<T> {

    void success(T result);

    void error(String error);

}
