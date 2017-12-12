package com.dumai.xianjindai.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.fragments.CashFragment;
import com.dumai.xianjindai.fragments.MineFragment;
import com.dumai.xianjindai.util.view.ToolbarHelper;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * 2017年11月17日17:14:34
 * 主界面
 * hrg
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @BindViews({R.id.radio_home, R.id.radio_monitor})
    List<TextView> radioButtons;
    private int position;
    private CashFragment cashFrament;
    private MineFragment mineFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_home;
    }


    @Override
    protected void init() {
        super.init();
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        radioButtons.get(0).setOnClickListener(this);
        radioButtons.get(1).setOnClickListener(this);
        setChioceItem(position);
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_home:
                setChioceItem(0);
                break;
            case R.id.radio_monitor:
                setChioceItem(1);
                break;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        position = savedInstanceState.getInt("position");
        setChioceItem(position);
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //记录当前的position
        outState.putInt("position", position);
    }

    public void setChioceItem(int index) {
        this.position = index;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideFragments(ft);
        ButterKnife.apply(radioButtons, TABSPEC, radioButtons.get(index));
        switch (index) {
            case 0:
                //借贷
                if (null == cashFrament) {
                    cashFrament = new CashFragment();
                    ft.add(R.id.tab_content, cashFrament);
                } else {
                    ft.show(cashFrament);
                }
                break;
            case 1:
                // 我的
                if (null == mineFragment) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.tab_content, mineFragment);
                } else {
                    ft.show(mineFragment);
                }
                break;
        }
        ft.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction ft) {
        if (null != cashFrament)
            ft.hide(cashFrament);
        if (null != mineFragment)
            ft.hide(mineFragment);
    }

    //控制normal 状态的当前View 隐藏，其它空间仍然为显示
    static final ButterKnife.Setter<TextView, TextView> TABSPEC = new ButterKnife.Setter<TextView, TextView>() {
        @Override
        public void set(TextView view, TextView value, int index) {
            if (view.getId() == value.getId()) {
                view.setSelected(true);
            } else {
                view.setSelected(false);
            }
        }
    };
}
