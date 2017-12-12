package com.dumai.xianjindai.http.http;

/**
 * 作者： haoruigang on 2017-11-28 11:12:56.
 * 类描述：网络回调接口类
 */
public interface IHttpCallBack<T> {
    void onError(Throwable throwable);

    void onSuccess(T date);
}
