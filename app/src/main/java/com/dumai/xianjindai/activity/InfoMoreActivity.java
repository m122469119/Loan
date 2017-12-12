package com.dumai.xianjindai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.util.view.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 名称：InfoMoreActivity.java
 * 描述：更多信息
 *
 * @author haoruigang
 * @version v1.0
 * @date： 2017-11-24 16:19:49
 */
public class InfoMoreActivity extends BaseActivity {

    @BindView(R.id.ll_proof_data)
    LinearLayout llProofData;
    @BindView(R.id.ll_address_info)
    LinearLayout llAddressInfo;
    @BindView(R.id.ll_urgent_contacts)
    LinearLayout llUrgentContacts;
    private String title;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_info_more;
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

    @OnClick({R.id.ll_proof_data, R.id.ll_address_info, R.id.ll_urgent_contacts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_proof_data:
                startActivity(new Intent(InfoMoreActivity.this, ProofMaterialActivity.class));
                break;
            case R.id.ll_address_info:
                startActivity(new Intent(InfoMoreActivity.this, AddressInfoActivity.class));
                break;
            case R.id.ll_urgent_contacts:
                startActivity(new Intent(InfoMoreActivity.this, UrgentContactsActivity.class));
                break;
        }
    }
}
