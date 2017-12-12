package com.dumai.xianjindai.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.util.view.ToolbarHelper;
import com.dumai.xianjindai.view.LoadingButton;
import com.dumai.xianjindai.view.pickers.SingleItem;
import com.dumai.xianjindai.view.pickers.SinglePickContarts;
import com.dumai.xianjindai.view.pickers.picker.SinglePicker;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 名称：UrgentContactsActivity.java
 * 描述：紧急联系人
 *
 * @author haoruigang
 * @version v1.0
 * @date： 2017-11-24 16:19:49
 */
public class UrgentContactsActivity extends BaseActivity {

    @BindView(R.id.ll_contacts_1)
    LinearLayout llContacts1;
    @BindView(R.id.ll_contacts_2)
    LinearLayout llContacts2;
    @BindView(R.id.ll_contacts_3)
    LinearLayout llContacts3;
    @BindView(R.id.loading_btn)
    LoadingButton loadingBtn;
    @BindView(R.id.tv_relation_1)
    TextView tvRelation1;
    @BindView(R.id.tv_relation_2)
    TextView tvRelation2;
    @BindView(R.id.tv_relation_3)
    TextView tvRelation3;

    SinglePicker<String> picker;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_urgent_contacts;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("紧急联系人");
        Toolbar toolbar = toolbarHelper.getToolbar();
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.ll_contacts_1, R.id.ll_contacts_2, R.id.ll_contacts_3, R.id.loading_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_contacts_1:
                picker = new SinglePicker<>(this, new String[]{
                        "父母", "配偶"
                });
                SinglePickContarts.getIntance().Singlepicker(picker, new SingleItem() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvRelation1.setText(item);
                    }
                }, "父母", 0);
                break;
            case R.id.ll_contacts_2:
                picker = new SinglePicker<>(this, new String[]{
                        "父母", "配偶"
                });
                SinglePickContarts.getIntance().Singlepicker(picker, new SingleItem() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvRelation2.setText(item);
                    }
                }, "父母", 0);
                break;
            case R.id.ll_contacts_3:
                picker = new SinglePicker<>(this, new String[]{
                        "父母", "配偶"
                });
                SinglePickContarts.getIntance().Singlepicker(picker, new SingleItem() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvRelation3.setText(item);
                    }
                }, "父母", 0);
                break;
            case R.id.loading_btn:

                break;
        }
    }
}
