package com.dumai.xianjindai.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.util.view.ToolbarHelper;

/**
 * 忘记密码
 * haoruigang
 * 2017-11-27 19:47:27
 */
public class ForgetPsdActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_forget_psd;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("忘记密码");
        toolbarHelper.setLeftMenuTitle("登录", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Toolbar toolbar = toolbarHelper.getToolbar();
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
