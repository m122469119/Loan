package com.dumai.xianjindai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.util.view.ToolbarHelper;
import com.dumai.xianjindai.view.pickers.common.LineConfig;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 名称：InfoCreditActivity.java
 * 描述：信用认证信息
 *
 * @author haoruigang
 * @version v1.0
 * @date： 2017-11-24 15:52:48
 */
public class InfoCreditActivity extends BaseActivity {

    @BindView(R.id.ll_credit_card)
    LinearLayout llCreditCard;
    @BindView(R.id.ll_cell_phone)
    LinearLayout llCellPhone;
    private String title;
    private LineConfig config;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_info_credit;
    }

    @Override
    protected void init() {
        super.init();
        Bundle mBundle = getIntent().getExtras();
        title = mBundle.getString("title");
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle(title);
        Toolbar toolbar = toolbarHelper.getToolbar();
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.ll_credit_card, R.id.ll_cell_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_credit_card:
                startActivity(new Intent(InfoCreditActivity.this, CreditCardDataActivity.class));
                break;
            case R.id.ll_cell_phone:
                startActivity(new Intent(InfoCreditActivity.this, CellPhoneActivity.class));
                break;
        }
    }
}
