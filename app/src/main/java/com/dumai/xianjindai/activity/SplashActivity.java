package com.dumai.xianjindai.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.util.ResourcesUtils;
import com.dumai.xianjindai.util.SharedUtils;
import com.dumai.xianjindai.util.view.ToolbarHelper;

/**
 * 2017年11月20日17:56:32
 * 启动页
 * haoruigang
 */
public class SplashActivity extends BaseActivity {


    boolean isFirstIn = false;
    Handler handler = new Handler();
    private final int SPLASH_DISPLAY_LENGHT = 2000;


    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_splash;
    }

    @Override
    protected void init() {
        // 启动one apm性能监测（后续添加）
        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isFirstIn = SharedUtils.getBoolean(this, "isFirstIn", true);
        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        handler.postDelayed(runnable, SPLASH_DISPLAY_LENGHT);
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        Toolbar toolbar = toolbarHelper.getToolbar();
        //动态改变“colorPrimaryDark”来实现沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ResourcesUtils.getColor(R.color.deepblue));
        }
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
