package com.dumai.xianjindai.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.bean.InfoContactBean;
import com.dumai.xianjindai.bean.InfoIdentityBean;
import com.dumai.xianjindai.bean.InfoJobBean;
import com.dumai.xianjindai.util.EmptyUtils;
import com.dumai.xianjindai.util.SharedUtils;
import com.dumai.xianjindai.util.ToastUtils;
import com.dumai.xianjindai.util.view.ToolbarHelper;
import com.dumai.xianjindai.view.LoadingButton;
import com.dumai.xianjindai.view.pickers.SingleItem;
import com.dumai.xianjindai.view.pickers.SinglePickContarts;
import com.dumai.xianjindai.view.pickers.picker.SinglePicker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 名称：InfoContactActivity.java
 * 描述：联系信息
 *
 * @author haoruigang
 * @version v1.0
 * @date： 2017-11-24 15:05:34
 */
public class InfoContactActivity extends BaseActivity {

    @BindView(R.id.ll_lineal_relation)
    LinearLayout llLinealRelation;
    @BindView(R.id.ll_important_relation)
    LinearLayout llImportantRelation;
    @BindView(R.id.tv_lineal_relation)
    TextView tvLinealRelation;
    @BindView(R.id.tv_important_relation)
    TextView tvImportantRelation;
    @BindView(R.id.loading_btn)
    LoadingButton loadingBtn;
    @BindView(R.id.et_lineal_name)
    EditText etLinealName;
    @BindView(R.id.et_lineal_phone)
    EditText etLinealPhone;
    @BindView(R.id.et_important_name)
    EditText etImportantName;
    @BindView(R.id.et_important_pame)
    EditText etImportantPame;
    private String title;
    SinglePicker<String> picker;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_info_contact;
    }

    @Override
    protected void init() {
        super.init();
        Bundle mBundle = getIntent().getExtras();
        title = mBundle.getString("title");

        getInfoContact();
    }

    private void getInfoContact() {
        String linRela = SharedUtils.getString(InfoContactActivity.this, "linealRelation");
        tvLinealRelation.setText(linRela);
        String linName = SharedUtils.getString(InfoContactActivity.this, "linealName");
        etLinealName.setText(linName);
        String linPhone = SharedUtils.getString(InfoContactActivity.this, "linealPhone");
        etLinealPhone.setText(linPhone);
        String importRela = SharedUtils.getString(InfoContactActivity.this, "importantRelation");
        tvImportantRelation.setText(importRela);
        String importName = SharedUtils.getString(InfoContactActivity.this, "importantName");
        etImportantName.setText(importName);
        String importPame = SharedUtils.getString(InfoContactActivity.this, "importantPame");
        etImportantPame.setText(importPame);
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

    @OnClick({R.id.ll_lineal_relation, R.id.ll_important_relation, R.id.loading_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_lineal_relation://直系联系人
                picker = new SinglePicker<>(this, new String[]{
                        "父母", "配偶"
                });
                SinglePickContarts.getIntance().Singlepicker(picker, new SingleItem() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvLinealRelation.setText(item);
                    }
                }, "父母", 0);
                break;
            case R.id.ll_important_relation://重要联系人
                picker = new SinglePicker<>(this, new String[]{
                        "父母", "配偶", "兄弟姐妹", "亲属", "朋友", "同学", "同事"
                });
                SinglePickContarts.getIntance().Singlepicker(picker, new SingleItem() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvImportantRelation.setText(item);
                    }
                }, "父母", 0);
                break;
            case R.id.loading_btn:
                loadingBtn.startLoading();//开始加载按钮动画
                loadingBtn.setClickable(false);
                if (!validate()) {
                    loadingBtn.stopLoading();//停止加载按钮动画
                    loadingBtn.setClickable(true);
                    return;
                }
                setInfoContact();

                break;
        }
    }

    //当前输入数据是否存在
    private Boolean isExistence = false;
    //是否保存或更新本地数据成功
    private Boolean isSaveSuccess = false;

    private int infoContact;

    private void setInfoContact() {
        // 1.先查询表中是否有此条数据
        List<InfoContactBean> mInfoList = InfoIdentityBean.findAll(InfoContactBean.class);
        for (InfoContactBean infoBean : mInfoList) {
            if (String.valueOf(infoBean.getLinealName()).equals(linealName)) {
                infoContact = infoBean.getId();
                isExistence = true;
                break;
            }
        }
        if (!isExistence) {
            // 2.如果不存在,保存联系人信息
            InfoContactBean infoContactBean = new InfoContactBean();
            infoContactBean.setLinealRelation(linealRelation);
            infoContactBean.setLinealName(linealName);
            infoContactBean.setLinealPhone(linealPhone);
            infoContactBean.setImportantRelation(importantRelation);
            infoContactBean.setImportantName(importantName);
            infoContactBean.setImportantPhone(importantPame);
            if (infoContactBean.save()) {
                isSaveSuccess = true;
            } else {
                isSaveSuccess = false;
            }
        } else {
            // 3.如果存在,修改联系人信息
            InfoContactBean infoContactBean = new InfoContactBean();
            infoContactBean.setLinealRelation(linealRelation);
            infoContactBean.setLinealName(linealName);
            infoContactBean.setLinealPhone(linealPhone);
            infoContactBean.setImportantRelation(importantRelation);
            infoContactBean.setImportantName(importantName);
            infoContactBean.setImportantPhone(importantPame);
            infoContactBean.update(infoContact);
            isSaveSuccess = true;
        }
        if (!isSaveSuccess) {
            ToastUtils.showToast(InfoContactActivity.this, "联系信息提交失败!");
            return;
        }

        SharedUtils.putString(InfoContactActivity.this, "linealRelation", linealRelation);
        SharedUtils.putString(InfoContactActivity.this, "linealName", linealName);
        SharedUtils.putString(InfoContactActivity.this, "linealPhone", linealPhone);
        SharedUtils.putString(InfoContactActivity.this, "importantRelation", importantRelation);
        SharedUtils.putString(InfoContactActivity.this, "importantName", importantName);
        SharedUtils.putString(InfoContactActivity.this, "importantPame", importantPame);

        loadingBtn.stopLoading();//停止加载按钮动画
        loadingBtn.setClickable(true);
        ToastUtils.showToast(InfoContactActivity.this, "联系信息提交成功!");
        finish();

    }

    private String linealRelation, linealName, linealPhone, importantRelation, importantName, importantPame;

    private boolean validate() {
        linealRelation = tvLinealRelation.getText().toString().trim();
        linealName = etLinealName.getText().toString().trim();
        linealPhone = etLinealPhone.getText().toString().trim();
        importantRelation = tvImportantRelation.getText().toString().trim();
        importantName = etImportantName.getText().toString().trim();
        importantPame = etImportantPame.getText().toString().trim();
        if (!EmptyUtils.isNotEmpty(linealRelation)) {
            ToastUtils.showToast(InfoContactActivity.this, "直系关系为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(linealName)) {
            ToastUtils.showToast(InfoContactActivity.this, "直系名称为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(linealPhone)) {
            ToastUtils.showToast(InfoContactActivity.this, "直系电话为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(importantRelation)) {
            ToastUtils.showToast(InfoContactActivity.this, "重要关系为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(importantName)) {
            ToastUtils.showToast(InfoContactActivity.this, "重要名称为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(importantPame)) {
            ToastUtils.showToast(InfoContactActivity.this, "重要电话为空!");
            return false;
        }
        return true;
    }
}
