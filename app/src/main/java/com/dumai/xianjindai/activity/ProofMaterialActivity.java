package com.dumai.xianjindai.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.util.view.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 名称：ProofMaterialActivity.java
 * 描述：证明材料
 *
 * @author haoruigang
 * @version v1.0
 * @date： 2017-11-27 10:50:49
 */
public class ProofMaterialActivity extends BaseActivity {

    @BindView(R.id.ll_work_proof)
    LinearLayout llWorkProof;
    @BindView(R.id.ll_financial_proof)
    LinearLayout llFinancialProof;
    @BindView(R.id.ll_education_proof)
    LinearLayout llEducationProof;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_proof_material;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("证明材料");

        Toolbar toolbar = toolbarHelper.getToolbar();
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.ll_work_proof, R.id.ll_financial_proof, R.id.ll_education_proof})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_work_proof:
                startActivity(new Intent(ProofMaterialActivity.this, WorkProofActivity.class));
                break;
            case R.id.ll_financial_proof:
                startActivity(new Intent(ProofMaterialActivity.this, FinancialProofActivity.class));
                break;
            case R.id.ll_education_proof:
                startActivity(new Intent(ProofMaterialActivity.this, EducationProofActivity.class));
                break;
        }
    }
}
