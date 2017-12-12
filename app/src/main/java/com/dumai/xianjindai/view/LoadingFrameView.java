package com.dumai.xianjindai.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dumai.xianjindai.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Android 抽象布局 减少视图层级
 *
 * @author haoruigang
 * @Date 2017年11月17日
 * @Time 18:03:17
 */
public class LoadingFrameView extends RelativeLayout {
    public static final int DELAY_MILLIS = 300;
    // 各种状态桢标志
    /**
     * 显示内容
     */
    public final static int CONTAINER_ITEM = 0;
    /**
     * 显示加载
     */
    public final static int PROGRESS_ITEM = 1;
    /**
     * 显示错误
     */
    public final static int ERROR_ITEM = 2;
    /**
     * 显示自定义
     */
    public final static int CUSTOM_ITEM = 3;
    /**
     * 显示暂无内容
     */
    public final static int NO_ITEM = 4;
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.load_info_pb)
    ProgressBar loadInfoPb;
    @BindView(R.id.load_info)
    TextView loadInfo;
    @BindView(R.id.no_info_pic)
    ImageView noInfoPic;
    @BindView(R.id.no_info)
    TextView noInfo;
    @BindView(R.id.iv_repeat_pic)
    ImageView ivRepeatPic;
    @BindView(R.id.tv_repeat_info)
    TextView tvRepeatInfo;
    @BindView(R.id.tv_try)
    TextView tvTrybt;
    @BindView(R.id.custom_ll)
    LinearLayout custom_ll;
    // 当前布局显示状态
    private int lastItem;
//    private int emptyIcon;
//    private Drawable emptyDrawable;

    public LoadingFrameView(Context context) {
        this(context, null, 0);
    }

    public LoadingFrameView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingFrameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(getContext(), R.layout.dm_loading_frame_layout, this);
        ButterKnife.bind(this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadingFrameView);
        String info = a.getString(R.styleable.LoadingFrameView_fv_emptyInfo);
        String noinfo = a.getString(R.styleable.LoadingFrameView_fv_noInfo);
        String repeatInfo = a.getString(R.styleable.LoadingFrameView_fv_repeatInfo);
        String bt = a.getString(R.styleable.LoadingFrameView_fv_repeatBt);
        if (!TextUtils.isEmpty(info))
            setEmptyInfo(info);
        if (!TextUtils.isEmpty(noinfo))
            setNoInfo(noinfo);
        if (!TextUtils.isEmpty(repeatInfo))
            setRepeatInfo(repeatInfo);
        if (!TextUtils.isEmpty(bt))
            setRepeatBt(bt);
//        emptyIcon = a.getResourceId(R.styleable.LoadingFrameView_fv_emptyIcon, R.drawable.icon_load);
        setNoIcon(a.getResourceId(R.styleable.LoadingFrameView_fv_noIcon, R.drawable.icon_payment_detail_null));
        setRepeatIcon(a.getResourceId(R.styleable.LoadingFrameView_fv_repeatIcon, R.drawable.icon_wifi));
        a.recycle();
        setFrame(PROGRESS_ITEM);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 这里,自定义的layout才解析完,这时能拿到用户自定义的xml布局体,他的位置在最后
        int index = NO_ITEM + 1;// 从此处起始,添加所有用户自定义view
        // 这里不能用i++,因为removeView之后,角标自动前移.就能拿到后面的view
        for (int i = index; i < getChildCount(); ) {
            View childView = getChildAt(i);
            removeView(childView);
            container.addView(childView);
        }
    }

    /**
     * 设置加载中提示信息
     *
     * @param info 设置加载中提示信息
     */
    public void setEmptyInfo(String info) {
        if (null != loadInfo) {
            loadInfo.setText(info);
        }
    }

    /**
     * 设置加载中提示信息
     *
     * @param resId 设置加载中提示信息
     */
    public void setEmptyInfo(@StringRes int resId) {
        if (null != loadInfo) {
            loadInfo.setText(resId);
        }
    }

    /**
     * 设置加载中图片
     *
     * @param drawable 设置加载中图片
     */
//    public void setEmptyIcon(Drawable drawable) {
//        emptyDrawable = drawable;
////        if (null != loadInfoPic) {
////            loadInfoPic.setImageDrawable(drawable);
////        }
//    }

    /**
     * 设置加载中图片
     *
     * @param resId 设置加载中图片
     */
//    public void setEmptyIcon(@DrawableRes int resId) {
//        emptyIcon = resId;
////        if (null != loadInfoPic) {
////            loadInfoPic.setImageResource(resId);
////        }
//    }

    /**
     * 设置暂无内容提示信息
     *
     * @param info 设置暂无内容提示信息
     */
    public void setNoInfo(String info) {
        if (null != noInfo) {
            noInfo.setText(info);
        }
    }

    /**
     * 设置暂无内容提示信息
     *
     * @param resId 设置暂无内容提示信息
     */
    public void setNoInfo(@StringRes int resId) {
        if (null != noInfo) {
            noInfo.setText(resId);
        }
    }

    /**
     * 设置暂无图片
     *
     * @param drawable 设置暂无图片
     */
    public void setNoIcon(Drawable drawable) {
        if (null != noInfoPic) {
            noInfoPic.setImageDrawable(drawable);
        }
    }

    /**
     * 设置自定义布局
     *
     * @param view 设置自定义布局
     */
    public void setCustomView(View view) {
        if (null != custom_ll) {
            custom_ll.removeAllViews();
            custom_ll.addView(view);
        }
    }

    /**
     * 设置暂无图片
     *
     * @param resId 设置暂无图片
     */
    public void setNoIcon(@DrawableRes int resId) {
        if (null != noInfoPic) {
            noInfoPic.setImageResource(resId);
        }
    }

    /**
     * 设置失败提示信息
     *
     * @param info 设置失败提示信息
     */
    public void setRepeatInfo(String info) {
        if (null != tvRepeatInfo) {
            tvRepeatInfo.setText(info);
        }
    }

    /**
     * 设置失败提示信息
     *
     * @param resId 设置失败提示信息
     */
    public void setRepeatInfo(@StringRes int resId) {
        if (null != tvRepeatInfo) {
            tvRepeatInfo.setText(resId);
        }
    }

    /**
     * 设置失败图片
     *
     * @param drawable 设置失败图片
     */
    public void setRepeatIcon(Drawable drawable) {
        if (null != ivRepeatPic) {
            ivRepeatPic.setImageDrawable(drawable);
        }
    }

    /**
     * 设置失败图片
     *
     * @param resId 设置失败图片
     */
    public void setRepeatIcon(@DrawableRes int resId) {
        if (null != ivRepeatPic) {
            ivRepeatPic.setImageResource(resId);
        }
    }

    /**
     * 设置失败按钮文字
     *
     * @param info 设置失败按钮文字
     */
    public void setRepeatBt(String info) {
        if (null != tvTrybt) {
            tvTrybt.setText(info);
        }
    }

    /**
     * 设设置失败按钮文字
     *
     * @param resId 设置失败按钮文字
     */
    public void setRepeatBt(@StringRes int resId) {
        if (null != tvTrybt) {
            tvTrybt.setText(resId);
        }
    }

    /**
     * 设置失败按钮
     *
     * @param resId 设置失败按钮
     */
    public void setRepeatBtB(@DrawableRes int resId) {
        if (null != tvTrybt) {
            tvTrybt.setBackgroundResource(resId);
        }
    }

    /**
     * 设置进度装载显示
     *
     * @param animate true:显示 false:隐藏
     */
    public void setProgressShown(boolean animate) {
        setFrame(PROGRESS_ITEM, animate, false);
    }

    /**
     * 设置暂无内容显示
     *
     * @param animate true:显示 false:隐藏
     */
    public void setNoShown(boolean animate) {
        setFrame(NO_ITEM, animate, false);
    }

    /**
     * 设置布局体显示
     *
     * @param animate true:显示 false:隐藏
     * @param delay   延迟加载时间(不能为负数)
     */
    public void setContainerShown(final boolean animate, int delay) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                setFrame(CONTAINER_ITEM, animate, false);
            }
        }, delay);
    }

    /**
     * 设置自定义布局体显示
     *
     * @param animate true:显示 false:隐藏
     */
    public void setCustomShown(boolean animate) {
        setFrame(CUSTOM_ITEM, animate, false);
    }

    /**
     * 设置错误显示
     *
     * @param animate true:显示 false:隐藏
     */

    public void setErrorShown(boolean animate, OnClickListener clickListener) {
        if (null != clickListener) {
            tvTrybt.setOnClickListener(clickListener);
            tvTrybt.setVisibility(View.VISIBLE);
        } else
            tvTrybt.setVisibility(View.GONE);
        setFrame(ERROR_ITEM, animate, false);
    }

    /**
     * 延迟设置布局体
     *
     * @param animate
     */
    public void delayShowContainer(final boolean animate) {
        setContainerShown(animate, DELAY_MILLIS);
    }

    public boolean isStatus(int status) {
        return lastItem == status;
    }

    /**
     * 设置显示桢
     *
     * @param item
     */
    public void setFrame(int item) {
        setFrame(item, false, true);
    }

    /**
     * 设置展示桢view
     *
     * @param item      当前展示桢
     * @param animate   是否显示动画
     * @param unChecked 无须检测last==当前指定桢
     */
    private void setFrame(final int item, final boolean animate, boolean unChecked) {
//        if (item == PROGRESS_ITEM) {
////            if (null != emptyDrawable)
////                loadInfoPic.setImageDrawable(emptyDrawable);
////            else if (R.drawable.tj_icon_load != emptyIcon) {
////                loadInfoPic.setImageResource(emptyIcon);
////            } else {
//            if (null != loadInfoPic.getDrawable() && loadInfoPic.getDrawable() instanceof AnimationDrawable) {
//                AnimationDrawable drawable = (AnimationDrawable) (loadInfoPic.getDrawable());
//                if (!drawable.isRunning())
//                    drawable.start();
//            }
////            }
//        }
        if (null != container && (unChecked || item != lastItem)) {
            View showView = getChildAt(item);
            View closeView = getChildAt(lastItem);
//            if (animate) {
//                ViewHelper.setAlpha(showView, 0f);
//                ViewPropertyAnimator.animate(showView).setDuration(200).alpha(1f);
//            }
            showView.clearAnimation();
            closeView.clearAnimation();
            showView.setVisibility(View.VISIBLE);
            closeView.setVisibility(View.GONE);
            lastItem = item;
        }
    }

    /**
     * 设置背景色
     *
     * @param color
     */
    public void setBackGroundColor(int color) {
        setBackgroundColor(color);
    }

    /**
     * 加载失败按钮
     *
     * @return
     */
    public TextView getTvTry() {
        return tvTrybt;
    }

    /**
     * 加载中
     *
     * @return
     */
    public ProgressBar getLoadInfoPb() {
        return loadInfoPb;
    }

    /**
     * 加载中文字
     *
     * @return
     */
    public TextView getLoadInfo() {
        return loadInfo;
    }

    /**
     * 加载失败图片
     *
     * @return
     */
    public ImageView getIvRepeatPic() {
        return ivRepeatPic;
    }

    /**
     * 加载失败文字
     *
     * @return
     */
    public TextView getTvRepeatInfo() {
        return tvRepeatInfo;
    }
}
