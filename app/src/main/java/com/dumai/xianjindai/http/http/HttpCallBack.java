package com.dumai.xianjindai.http.http;

import android.app.Activity;

import com.android.volley.Request;
import com.dumai.xianjindai.view.pickers.util.LogUtils;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * 作者： haoruigang on 2017-11-28 11:11:51
 * 类描述：网络回调类
 */
public abstract class HttpCallBack<T> implements IHttpCallBack<T>, MyProgressDialog.OnDialogCancel {
    private MyProgressDialog dialog;
    private Request req;

    public HttpCallBack() {
    }

    // 是否可取消请求，默认可取消  haoruigang  2017-11-28 11:12:09
    public HttpCallBack(Activity activity, boolean isDismiss) {
        if (isDismiss)
            dialog = new MyProgressDialog(activity, isDismiss);
        else
            dialog = new MyProgressDialog(activity, this);
        dialog.show();
    }

    public HttpCallBack(Activity activity) {
        dialog = new MyProgressDialog(activity, this);
        dialog.show();
    }

    public void onResponse(String date) {
        try {
            dismiss();

            Type gsonType = getTType(this.getClass());
            if ("class java.lang.String".equals(gsonType.toString())) {
                onSuccess((T) date);
            } else {
                T o = new Gson().fromJson(date, gsonType);
                onSuccess(o);
            }
        } catch (Exception e) {
            LogUtils.error("Exception", e);
            onError(e);

        }
    }

    public void error(Throwable throwable) {
        dismiss();
        onError(throwable);
    }

    @Override
    public void setOnDialogCancel() {
        dismiss();
        if (null != req)
            req.cancel();
    }

    public void setRequest(Request req) {
        this.req = req;
    }

    private void dismiss() {
        if (null != dialog && dialog.isShowing())
            dialog.dismiss();
    }

    private Type getTType(Class<?> clazz) {
        Type mySuperClassType = clazz.getGenericSuperclass();
        Type[] types = ((ParameterizedType) mySuperClassType).getActualTypeArguments();
        if (types != null && types.length > 0) {
            return types[0];
        }
        return null;
    }
}
