package com.dumai.xianjindai.http.http;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;

import com.dumai.xianjindai.R;

/**
 * 作者： haoruigang on 2017-11-28 11:15:18
 * 类描述：全局dialog
 */
public class MyProgressDialog extends Dialog implements DialogInterface.OnKeyListener {
    private OnDialogCancel dialogCancel;
    private boolean isDismiss;
    private Activity activity;

    /**
     * 不可取消(true不可，false可取消)
     *
     * @param activity
     * @param isDismiss
     */
    public MyProgressDialog(Activity activity, boolean isDismiss) {
        super(activity, R.style.dialog);
        this.isDismiss = isDismiss;
        this.activity = activity;
        this.setOnKeyListener(this);// 增加返回监听
    }

    /**
     * 默认可取消
     *
     * @param activity
     * @param dialogCancel
     */
    public MyProgressDialog(Activity activity, OnDialogCancel dialogCancel) {
        super(activity, R.style.dialog);
        this.dialogCancel = dialogCancel;
        this.activity = activity;
        this.setOnKeyListener(this);// 增加返回监听
    }

    public MyProgressDialog(Activity activity) {
        super(activity, R.style.dialog);
        this.activity = activity;
        this.setOnKeyListener(this);// 增加返回监听
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
        Window window = this.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getDecorView().setPadding(0, 0, 0, 0);
        setCancelable(false);
//        WindowManager.LayoutParams lp = window.getAttributes();
////        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
////        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//        window.setAttributes(lp);
    }

    public void show() {
        if (!isShowing() && this.activity != null && !this.activity.isFinishing()) {
            super.show();
        }
    }

    public void dismiss() {
        if (isShowing() && this.activity != null && !this.activity.isFinishing()) {
            super.dismiss();
        }
    }

    @Override
    public boolean isShowing() {
        return super.isShowing();
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isDismiss && this.isShowing()) {
                dismiss();
                if (null != dialogCancel) {
                    // 取消  haoruigang  2017/9/19 10:59
                    dialogCancel.setOnDialogCancel();
                }
            }
        }
        return true;
    }

    public interface OnDialogCancel {
        void setOnDialogCancel();
    }
}
