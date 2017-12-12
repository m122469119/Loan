/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dumai.xianjindai.util;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;

import com.dumai.xianjindai.fragment.AlertDialogFragment;
import com.dumai.xianjindai.fragment.AbDialogFragment;
import com.dumai.xianjindai.fragment.AbLoadDialogFragment;
import com.dumai.xianjindai.fragment.ProgressDialogFragment;
import com.dumai.xianjindai.fragment.AbRefreshDialogFragment;
import com.dumai.xianjindai.fragment.AbSampleDialogFragment;

import java.util.ArrayList;
import java.util.List;


// TODO: Auto-generated Javadoc

/**
 * 名称：DialogUtil.java
 * 描述：Dialog工具类.
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017-11-19 11:24:19
 */

public class DialogUtil {

    /**
     * dialog tag
     */
    private static String mDialogTag = "dialog";
    private static final DialogUtil instance = new DialogUtil();

    public static DialogUtil getInstance() {
        return instance;
    }

    /**
     * 全屏显示一个对话框不影响下面的View的点击
     *
     * @param view
     * @return
     */
    public static AbSampleDialogFragment showTipsDialog(View view) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Create and show the dialog.
        AbSampleDialogFragment newFragment = AbSampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light);
        newFragment.setContentView(view);

        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // 作为全屏显示,使用“content”作为fragment容器的基本视图,这始终是Activity的基本视图  
        ft.add(android.R.id.content, newFragment, mDialogTag).addToBackStack(null).commit();

        return newFragment;
    }

    /**
     * 全屏显示一个对话框
     *
     * @param view
     * @return
     */
    public static AbSampleDialogFragment showFullScreenDialog(View view) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Create and show the dialog.
        AbSampleDialogFragment newFragment = AbSampleDialogFragment.newInstance(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        newFragment.setContentView(view);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画 
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 显示一个自定义的对话框(有背景层)
     *
     * @param view
     */
    public static AbSampleDialogFragment showDialog(View view) {
        return showDialog(view, Gravity.CENTER);
    }

    /**
     * 描述：显示一个自定义的对话框(有背景层).
     *
     * @param view
     * @param gravity 位置
     * @return
     */
    public static AbSampleDialogFragment showDialog(View view, int gravity) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Create and show the dialog.
        AbSampleDialogFragment newFragment = AbSampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog, gravity);
        newFragment.setContentView(view);

        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);

        return newFragment;
    }

    /**
     * 显示一个自定义的对话框(有背景层)
     *
     * @param view
     * @param animEnter
     * @param animExit
     * @param animPopEnter
     * @param animPopExit
     * @return
     */
    public static AbSampleDialogFragment showDialog(View view, int animEnter, int animExit, int animPopEnter, int animPopExit) {
        return showDialog(view, animEnter, animExit, animPopEnter, animPopExit, Gravity.CENTER);
    }

    /**
     * 描述：显示一个自定义的对话框(有背景层).
     *
     * @param view
     * @param animEnter
     * @param animExit
     * @param animPopEnter
     * @param animPopExit
     * @param gravity      位置
     * @return
     */
    public static AbSampleDialogFragment showDialog(View view, int animEnter, int animExit, int animPopEnter, int animPopExit, int gravity) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Create and show the dialog.
        AbSampleDialogFragment newFragment = AbSampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog, gravity);
        newFragment.setContentView(view);
        //自定义转场动画
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 显示一个自定义的对话框(有背景层)
     *
     * @param view
     * @param onCancelListener
     * @return
     */
    public static AbSampleDialogFragment showDialog(View view, DialogInterface.OnCancelListener onCancelListener) {
        return showDialog(view, Gravity.CENTER, onCancelListener);
    }

    /**
     * 描述：显示一个自定义的对话框(有背景层).
     *
     * @param view
     * @param gravity          位置
     * @param onCancelListener 　取消事件
     * @return
     */
    public static AbSampleDialogFragment showDialog(View view, int gravity, DialogInterface.OnCancelListener onCancelListener) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Create and show the dialog.
        AbSampleDialogFragment newFragment = AbSampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog, gravity);
        newFragment.setContentView(view);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画  
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.setOnCancelListener(onCancelListener);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 描述：显示一个自定义的对话框(有背景层).
     *
     * @param view
     * @param animEnter
     * @param animExit
     * @param animPopEnter
     * @param animPopExit
     * @param onCancelListener
     * @return
     */
    public static AbSampleDialogFragment showDialog(View view, int animEnter, int animExit, int animPopEnter, int animPopExit, DialogInterface.OnCancelListener onCancelListener) {
        return showDialog(view, animEnter, animExit, animPopEnter, animPopExit, Gravity.CENTER, onCancelListener);
    }

    /**
     * 描述：显示一个自定义的对话框(有背景层).
     *
     * @param view
     * @param animEnter
     * @param animExit
     * @param animPopEnter
     * @param animPopExit
     * @param gravity
     * @param onCancelListener
     * @return
     */
    public static AbSampleDialogFragment showDialog(View view, int animEnter, int animExit, int animPopEnter, int animPopExit, int gravity, DialogInterface.OnCancelListener onCancelListener) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Create and show the dialog.
        AbSampleDialogFragment newFragment = AbSampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog, gravity);
        newFragment.setContentView(view);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit);
        newFragment.setOnCancelListener(onCancelListener);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 显示一个自定义的对话框(无背景层)
     *
     * @param view
     */
    public static AbSampleDialogFragment showPanel(View view) {
        return showPanel(view, Gravity.CENTER);
    }

    /**
     * 描述：显示一个自定义的对话框(无背景层).
     *
     * @param view
     * @param gravity
     * @return
     */
    public static AbSampleDialogFragment showPanel(View view, int gravity) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Create and show the dialog.
        AbSampleDialogFragment newFragment = AbSampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Light_Panel, gravity);
        newFragment.setContentView(view);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 显示一个自定义的对话框(无背景层)
     *
     * @param view
     * @param onCancelListener
     * @return
     */
    public static AbSampleDialogFragment showPanel(View view, DialogInterface.OnCancelListener onCancelListener) {
        return showPanel(view, Gravity.CENTER, onCancelListener);
    }

    /**
     * 显示一个自定义的对话框(无背景层)
     *
     * @param view
     * @param onCancelListener
     * @return
     */
    public static AbSampleDialogFragment showPanel(View view, int gravity, DialogInterface.OnCancelListener onCancelListener) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Create and show the dialog.
        AbSampleDialogFragment newFragment = AbSampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Light_Panel, gravity);
        newFragment.setContentView(view);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.setOnCancelListener(onCancelListener);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }


    /**
     * 描述：对话框dialog （图标，标题，String内容）.
     *
     * @param context
     * @param icon
     * @param title   对话框标题内容
     */
    public static AlertDialogFragment showAlertDialog(Context context, int icon, String title, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(icon, title, message, null, null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 显示一个一般的对话框（图标，标题，string内容，确认，取消）.
     *
     * @param context
     * @param icon
     * @param title           对话框标题内容
     * @param message         对话框提示内容
     * @param onClickListener 点击确认按钮的事件监听
     */
    public static AlertDialogFragment showAlertDialog(Context context, int icon, String title, String message, AlertDialogFragment.AbDialogOnClickListener onClickListener) {
        FragmentActivity activity = (FragmentActivity) context;
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(icon, title, message, null, onClickListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }


    /**
     * 显示一个一般的对话框（标题，String内容，确认，取消）.
     *
     * @param context
     * @param title           对话框标题内容
     * @param message         对话框提示内容
     * @param onClickListener 点击确认按钮的事件监听
     */
    public static AlertDialogFragment showAlertDialog(Context context, String title, String message, AlertDialogFragment.AbDialogOnClickListener onClickListener) {
        FragmentActivity activity = (FragmentActivity) context;
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0, title, message, null, onClickListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 显示一个一般的对话框（View内容）.
     *
     * @param view 对话框标题内容
     */
    public static AlertDialogFragment showAlertDialog(View view) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0, null, null, view, null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 显示一个一般的对话框（String内容）.
     *
     * @param context
     */
    public static AlertDialogFragment showAlertDialog(Context context, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0, null, message, null, null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 描述：对话框dialog （图标，标题，View内容）.
     *
     * @param icon
     * @param title 对话框标题内容
     * @param view  对话框提示内容
     */
    public static AlertDialogFragment showAlertDialog(int icon, String title, View view) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(icon, title, null, view, null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 显示一个一般的对话框（图标，标题，View内容，确认，取消）.
     *
     * @param icon
     * @param title           对话框标题内容
     * @param view            对话框提示内容
     * @param onClickListener 点击确认按钮的事件监听
     */
    public static AlertDialogFragment showAlertDialog(int icon, String title, View view, AlertDialogFragment.AbDialogOnClickListener onClickListener) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(icon, title, null, view, onClickListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 描述：对话框dialog （标题，View内容）.
     *
     * @param title 对话框标题内容
     * @param view  对话框提示内容
     */
    public static AlertDialogFragment showAlertDialog(String title, View view) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0, title, null, view, null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 显示一个一般的对话框（标题，View内容，确认，取消）.
     *
     * @param title           对话框标题内容
     * @param view            对话框提示内容
     * @param onClickListener 点击确认按钮的事件监听
     */
    public static AlertDialogFragment showAlertDialog(String title, View view, AlertDialogFragment.AbDialogOnClickListener onClickListener) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0, title, null, view, onClickListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 描述：对话框dialog （标题，String内容）.
     *
     * @param context
     * @param title   对话框标题内容
     */
    public static AlertDialogFragment showAlertDialog(Context context, String title, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0, title, message, null, null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }


    /**
     * 描述：显示进度框.
     *
     * @param context               the context
     * @param indeterminateDrawable 用默认请写0
     * @param message               the message
     */
    public static ProgressDialogFragment showProgressDialog(Context context, int indeterminateDrawable, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        ProgressDialogFragment newFragment = ProgressDialogFragment.newInstance(indeterminateDrawable, message);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 描述：显示加载框.
     *
     * @param context               the context
     * @param indeterminateDrawable
     * @param message               the message
     */
    public static AbLoadDialogFragment showLoadDialog(Context context, int indeterminateDrawable, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        AbLoadDialogFragment newFragment = AbLoadDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 描述：显示加载框.
     *
     * @param context               the context
     * @param indeterminateDrawable
     * @param message               the message
     */
    public static AbLoadDialogFragment showLoadDialog(Context context, int indeterminateDrawable, String message, AbDialogFragment.AbDialogOnLoadListener abDialogOnLoadListener) {
        FragmentActivity activity = (FragmentActivity) context;
        AbLoadDialogFragment newFragment = AbLoadDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        newFragment.setAbDialogOnLoadListener(abDialogOnLoadListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 描述：显示加载框.
     *
     * @param context               the context
     * @param indeterminateDrawable
     * @param message               the message
     */
    public static AbLoadDialogFragment showLoadPanel(Context context, int indeterminateDrawable, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        AbLoadDialogFragment newFragment = AbLoadDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Light_Panel);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 描述：显示加载框.
     *
     * @param context               the context
     * @param indeterminateDrawable
     * @param message               the message
     */
    public static AbLoadDialogFragment showLoadPanel(Context context, int indeterminateDrawable, String message, AbDialogFragment.AbDialogOnLoadListener abDialogOnLoadListener) {
        FragmentActivity activity = (FragmentActivity) context;
        AbLoadDialogFragment newFragment = AbLoadDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Light_Panel);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        newFragment.setAbDialogOnLoadListener(abDialogOnLoadListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 描述：显示刷新框.
     *
     * @param context               the context
     * @param indeterminateDrawable
     * @param message               the message
     */
    public static AbRefreshDialogFragment showRefreshDialog(Context context, int indeterminateDrawable, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        AbRefreshDialogFragment newFragment = AbRefreshDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        newFragment.setAbDialogOnLoadListener(null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 描述：显示刷新框.
     *
     * @param context
     * @param indeterminateDrawable
     * @param message
     * @return
     */
    public static AbRefreshDialogFragment showRefreshDialog(Context context, int indeterminateDrawable, String message, AbDialogFragment.AbDialogOnLoadListener abDialogOnLoadListener) {
        FragmentActivity activity = (FragmentActivity) context;
        AbRefreshDialogFragment newFragment = AbRefreshDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        newFragment.setAbDialogOnLoadListener(abDialogOnLoadListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 描述：显示刷新框.
     *
     * @param context               the context
     * @param indeterminateDrawable
     * @param message               the message
     */
    public static AbRefreshDialogFragment showRefreshPanel(Context context, int indeterminateDrawable, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        AbRefreshDialogFragment newFragment = AbRefreshDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Light_Panel);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        newFragment.setAbDialogOnLoadListener(null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }

    /**
     * 描述：显示刷新框.
     *
     * @param context
     * @param indeterminateDrawable
     * @param message
     * @return
     */
    public static AbRefreshDialogFragment showRefreshPanel(Context context, int indeterminateDrawable, String message, AbDialogFragment.AbDialogOnLoadListener abDialogOnLoadListener) {
        FragmentActivity activity = (FragmentActivity) context;
        AbRefreshDialogFragment newFragment = AbRefreshDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Light_Panel);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        newFragment.setAbDialogOnLoadListener(abDialogOnLoadListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
        return newFragment;
    }


    /**
     * 描述：移除Fragment.
     *
     * @param context the context
     */
    public static void removeDialog(final Context context) {
        new Handler().post(new Runnable() {

            @Override
            public void run() {
                try {
                    FragmentActivity activity = (FragmentActivity) context;
                    FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
                    // 指定一个系统转场动画
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    Fragment prev = activity.getFragmentManager().findFragmentByTag(mDialogTag);
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);
                    ft.commit();
                    DialogUtil.getInstance().setListener(false);
                } catch (Exception e) {
                    //可能有Activity已经被销毁的异常
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 描述：移除Fragment和View
     *
     * @param view
     */
    public static void removeDialog(View view) {
        removeDialog(view.getContext());
        ViewUtil.removeSelfFromParent(view);
    }

    private List<OnDialogChangedListener> onDialogChangedListenerList = new ArrayList<OnDialogChangedListener>();

    public void setOnDialogChangedListener(OnDialogChangedListener listener) {
        if (!onDialogChangedListenerList.contains(listener)) {
            onDialogChangedListenerList.add(listener);
        }
    }

    public interface OnDialogChangedListener {

        public void isShow(boolean state);
    }

    /**
     * 添加监听
     *
     * @param state
     */
    public void setListener(boolean state) {

        for (OnDialogChangedListener listener : onDialogChangedListenerList) {
            listener.isShow(state);
        }
    }

    /**
     * 移除监听
     *
     * @param listener
     */
    public void removeListener(OnDialogChangedListener listener) {
        if (onDialogChangedListenerList.contains(listener)) {
            onDialogChangedListenerList.remove(listener);
        }
    }

}
