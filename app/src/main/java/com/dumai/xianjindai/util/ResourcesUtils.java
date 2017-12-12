package com.dumai.xianjindai.util;

import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.widget.TextView;

import com.dumai.xianjindai.MineApplication;

/**
 * 项目名称：Sosoliuda
 * 类描述：获取资源
 * 创建人：haoruigang
 * 创建时间：2017-11-20 19:08:56
 */

public class ResourcesUtils {
    /**
     * 获取字符串
     *
     * @param id
     * @param obj
     * @return
     */
    public static String getString(@StringRes int id, Object... obj) {
        String string = MineApplication.getInstance().getResources().getString(id);
        if (null != obj && obj.length > 0)
            return String.format(string, obj);
        return string;
    }

    public static String[] getStrings(@ArrayRes int id) {
        String[] stringArray = MineApplication.getInstance().getResources().getStringArray(id);
        return stringArray;
    }

    /**
     * 获取文字id内容
     *
     * @param id
     * @return
     */
//    public static List<StringArrBean> getStringList(@ArrayRes int id) {
//        List<StringArrBean> list = new ArrayList<>();
//        String[] strings = getStrings(id);
//        for (int i = 0; i < strings.length; i++) {
//            String[] split = strings[i].split("_");
//            StringArrBean bean = new StringArrBean(split[0], split[split.length - 1]);
//            list.add(bean);
//        }
//        return list;
//    }

    /**
     * 获取颜色
     *
     * @param color
     * @return
     */
    public static int getColor(@ColorRes int color) {
        return MineApplication.getInstance().getResources().getColor(color);
    }

    /**
     * 获取图片
     *
     * @param drawable
     * @return
     */
    public static Drawable getDrawable(@DrawableRes int drawable) {
        return MineApplication.getInstance().getResources().getDrawable(drawable);
    }

    /**
     * 文字中添加图片
     *
     * @param textView
     * @param imgResId
     * @param index
     * @param padding
     */
    public static void setTvaddDrawable(TextView textView, @DrawableRes int imgResId, int index, int padding) {
        if (imgResId == -1) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(index == 1 ? imgResId : 0, index == 2 ? imgResId : 0, index == 3 ? imgResId : 0, index == 4 ? imgResId : 0);
            textView.setCompoundDrawablePadding(padding);
        }
    }
}
