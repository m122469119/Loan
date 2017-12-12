package com.dumai.xianjindai.activity;

import android.graphics.PixelFormat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.adapter.MyDataPagerAdapter;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.fragments.InfoContactFragment;
import com.dumai.xianjindai.fragments.InfoCreditFragment;
import com.dumai.xianjindai.fragments.InfoIdentityFragment;
import com.dumai.xianjindai.fragments.InfoJobFragment;
import com.dumai.xianjindai.fragments.InfoMoreFragment;
import com.dumai.xianjindai.util.ToastUtils;
import com.dumai.xianjindai.util.view.ToolbarHelper;
import com.dumai.xianjindai.view.CalculaLimitDialog;
import com.dumai.xianjindai.view.HorizontalProgressBar;
import com.dumai.xianjindai.view.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 名称：MyDataActivity.java
 * 描述：我的资料
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017-11-22 11:49:58
 */
public class MyDataActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    // viewpagerindicator
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.indicator)
    TabPageIndicator indicator;
    private static ArrayList<String> CONTENT = new ArrayList<>();// 分类列表
    private static final int[] ICONS = new int[]{
            R.drawable.perm_group_calendar,
            R.drawable.perm_group_camera,
            R.drawable.perm_group_device_alarms,
            R.drawable.perm_group_location,
            R.drawable.perm_group_more,
    };
    // tab标签中可切换的fragment
    InfoIdentityFragment infoIdentityFragment;// 身份信息Fragment
    InfoJobFragment infoJobFragment;// 工作信息Fragment
    InfoContactFragment infoContactFragment;// 联系信息Fragment
    InfoCreditFragment infoCreditFragment;// 信用信息Fragment
    InfoMoreFragment infoMoreFragment;// 更多信息Fragment
    // viewpagerindicator

    @BindView(R.id.horizontal_progress_view)
    HorizontalProgressBar horizontalProgressView;

    @Override
    protected int getContentViewId() {
        // 使窗口支持透明度
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        return R.layout.dm_activity_my_data;
    }

    /**
     * init the tab-fragments
     */
    private void initTabFragments() {
        CONTENT.clear();//清空缓存
        CONTENT.add("身份信息");
        CONTENT.add("工作信息");
        CONTENT.add("联系信息");
        CONTENT.add("信用认证");
        CONTENT.add("更多认证");
    }

    @Override
    protected void init() {
        super.init();
        //viewpagerindicator
        initTabFragments();
        // 0.viewpager的adapter 创建Fragment管理者 getSupportFragmentManager()
        viewpager.setAdapter(new MyDataPagerAdapter(getSupportFragmentManager(), MyDataActivity.this, CONTENT, ICONS));
        // 1.设置幕后item的缓存数目
        viewpager.setOffscreenPageLimit(3);
        // 2.设置页与页之间的间距
        viewpager.setPageMargin(66);
        // 3.将父类的touch事件分发至viewPgaer，否则只能滑动中间的一个view对象
        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewpager.dispatchTouchEvent(event);
            }
        });
        // 4.如果我们要对ViewPager设置监听，用indicator设置就行了
        indicator.setOnPageChangeListener(this);
        // 5.实例化TabPageIndicator然后设置ViewPager与之关联
        indicator.setViewPager(viewpager);
        //horizontalProgressView
        horizontalProgressView.setProgressWithAnimation(60).setProgressListener(new HorizontalProgressBar.ProgressListener() {
            @Override
            public void currentProgressListener(float currentProgress) {
            }
        });
        horizontalProgressView.startProgressAnimation();
        alertMessage();
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        Toolbar tooble = toolbarHelper.getToolbar();
        toolbarHelper.setTitle("我的资料");
        tooble.setNavigationIcon(R.drawable.icon_back);
        tooble.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // to refresh frameLayout
        if (container != null) {
            container.invalidate();
        }
        switch (position) {
            case 0:
                if (infoIdentityFragment == null) {
                    infoIdentityFragment = new InfoIdentityFragment();
                }
                infoIdentityFragment.init();
                break;
            case 1:
                if (infoJobFragment == null) {
                    infoJobFragment = new InfoJobFragment();
                }
                infoJobFragment.init();
                break;
            case 2:
                if (infoContactFragment == null) {
                    infoContactFragment = new InfoContactFragment();
                }
                infoContactFragment.init();
                break;
            case 3:
                if (infoCreditFragment == null) {
                    infoCreditFragment = new InfoCreditFragment();
                }
                infoCreditFragment.init();
                break;
            case 4:
                if (infoMoreFragment == null) {
                    infoMoreFragment = new InfoMoreFragment();
                }
                infoMoreFragment.init();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void alertMessage() {
        // 产生背景变暗效果
        WindowManager.LayoutParams lp = MyDataActivity.this.getWindow().getAttributes();
        lp.alpha = 0.5f;
        MyDataActivity.this.getWindow().setAttributes(lp);
        final CalculaLimitDialog calculaLimitDialog = new CalculaLimitDialog(MyDataActivity.this);
        calculaLimitDialog.setCanceledOnTouchOutside(false);
        calculaLimitDialog.setCancelable(false);
        calculaLimitDialog.show();
        calculaLimitDialog.setClicklistener(new CalculaLimitDialog.ClickListenerInterface() {


            @Override
            public void doClose() {
                calculaLimitDialog.dismiss();
                WindowManager.LayoutParams lp = MyDataActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                MyDataActivity.this.getWindow().setAttributes(lp);
            }

            @Override
            public void doCancel() {
                calculaLimitDialog.dismiss();
                WindowManager.LayoutParams lp = MyDataActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                MyDataActivity.this.getWindow().setAttributes(lp);
                ToastUtils.showToast(MyDataActivity.this, "正在计算额度......");
            }

            @Override
            public void doUpdate() {
                calculaLimitDialog.dismiss();
                WindowManager.LayoutParams lp = MyDataActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                MyDataActivity.this.getWindow().setAttributes(lp);
            }
        });
    }
}
