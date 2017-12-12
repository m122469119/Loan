package com.dumai.xianjindai.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.commons.ToUIEvent;
import com.dumai.xianjindai.global.ActivityManager;
import com.dumai.xianjindai.http.okhttp.OkHttpUtils;
import com.dumai.xianjindai.util.view.ToolbarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import okhttp3.OkHttpClient;

/**
 * 应用中所有Activity的基类
 * Created by haoruigang on 2017-11-19.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public String TAG = getClass().getSimpleName();
    private Unbinder unbinder;
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentViewId() > 0) {
            setContentView(getContentViewId());
            unbinder = ButterKnife.bind(this);//注册注解
            init();
            initActionBar();
        }
        ActivityManager.addActivity(this);
        EventBus.getDefault().register(this);//注册EventBus
    }

    protected abstract int getContentViewId();

    protected void init() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ActivityManager.removeActivity(this);
        if (null != unbinder)
            unbinder.unbind();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    protected abstract void initToolbar(ToolbarHelper toolbarHelper);

    public void initActionBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            // 默认不显示原生标题
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            initToolbar(new ToolbarHelper(toolbar));
        }
    }

    public void onEvent(ToUIEvent event) {
    }
}
