package com.dumai.xianjindai.global;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * 名称：ActivityManager.java
 * 描述：用于处理退出程序时可以退出所有的activity，而编写的通用类
 *
 * @author haoruigang
 * @version v1.0
 * @date 2017-11-19 12:00:03
 */
public class ActivityManager {

    private static List<Activity> activityList = new LinkedList<Activity>();
    private static ActivityManager instance;

    private ActivityManager() {
    }

    /**
     * 单例模式中获取唯一的ActivityManager实例.
     *
     * @return
     */
    public static ActivityManager getInstance() {
        if (null == instance) {
            instance = new ActivityManager();
        }
        return instance;
    }


    /**
     * 添加活动Activity到容器中.
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    /**
     * 移除活动
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }


    /**
     * 遍历所有Activity并finish结束所有活动.
     */
    public static void finishAll() {
        for (Activity activity : activityList) {
            if (null != activity && !activity.isFinishing()) {
                activity.finish();
            }
        }
        activityList.clear();
    }
}