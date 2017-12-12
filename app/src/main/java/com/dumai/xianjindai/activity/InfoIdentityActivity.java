package com.dumai.xianjindai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.bean.InfoIdentityBean;
import com.dumai.xianjindai.http.http.HttpCallBack;
import com.dumai.xianjindai.http.http.HttpManager;
import com.dumai.xianjindai.jparser.JsonLoginObject;
import com.dumai.xianjindai.util.EmptyUtils;
import com.dumai.xianjindai.util.LogUtil;
import com.dumai.xianjindai.util.SharedUtils;
import com.dumai.xianjindai.util.ToastUtils;
import com.dumai.xianjindai.util.view.ToolbarHelper;
import com.dumai.xianjindai.view.AddressPickTask;
import com.dumai.xianjindai.view.LoadingButton;
import com.dumai.xianjindai.view.pickers.SingleItem;
import com.dumai.xianjindai.view.pickers.SinglePickContarts;
import com.dumai.xianjindai.view.pickers.entity.City;
import com.dumai.xianjindai.view.pickers.entity.County;
import com.dumai.xianjindai.view.pickers.entity.Province;
import com.dumai.xianjindai.view.pickers.picker.SinglePicker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 身份信息
 * haoruigang
 * 2017-11-23 15:15:22
 */
public class InfoIdentityActivity extends BaseActivity {

    @BindView(R.id.loading_btn)
    LoadingButton loadingBtn;
    @BindView(R.id.ll_provincial_city)
    LinearLayout llProvincialCity;
    @BindView(R.id.ll_highest_education)
    LinearLayout llHighestEducation;
    @BindView(R.id.et_identity_name)
    EditText etIdentityName;
    @BindView(R.id.et_identity_code)
    EditText etIdentityCode;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_county)
    TextView tvCounty;
    @BindView(R.id.et_detailed_ars)
    EditText etDetailedArs;
    @BindView(R.id.tv_education)
    TextView tvEducation;
    @BindView(R.id.tv_marital_status)
    TextView tvMaritalStatus;

    private String title;
    private SinglePicker<String> picker;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_info_identity;
    }

    @Override
    protected void init() {
        super.init();
        Bundle mBundle = getIntent().getExtras();
        title = mBundle.getString("title");

        getInfoIdentity();

    }

    private void getInfoIdentity() {
        String IdenName = SharedUtils.getString(InfoIdentityActivity.this, "IdentityName");
        etIdentityName.setText(IdenName);
        String IdenCode = SharedUtils.getString(InfoIdentityActivity.this, "IdentityCode");
        etIdentityCode.setText(IdenCode);
        String province = SharedUtils.getString(InfoIdentityActivity.this, "Province");
        tvProvince.setText(province);
        String city = SharedUtils.getString(InfoIdentityActivity.this, "City");
        tvCity.setText(city);
        String county = SharedUtils.getString(InfoIdentityActivity.this, "County");
        tvCounty.setText(county);
        String detailed = SharedUtils.getString(InfoIdentityActivity.this, "DetailedArs");
        etDetailedArs.setText(detailed);
        String educa = SharedUtils.getString(InfoIdentityActivity.this, "Education");
        tvEducation.setText(educa);
        String marital = SharedUtils.getString(InfoIdentityActivity.this, "MaritalStatus");
        tvMaritalStatus.setText(marital);
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

    @OnClick({R.id.loading_btn, R.id.ll_provincial_city, R.id.ll_highest_education, R.id.ll_marital_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loading_btn:
                loadingBtn.startLoading();//开始加载按钮动画
                loadingBtn.setClickable(false);
                if (!validate()) {
                    loadingBtn.stopLoading();//停止加载按钮动画
                    loadingBtn.setClickable(true);
                    return;
                }

                setInfoIdentity();

                break;
            case R.id.ll_provincial_city://地址
                AddressPickTask task = new AddressPickTask(this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        ToastUtils.showToast(InfoIdentityActivity.this, "数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
                            tvProvince.setText(province.getAreaName());
                            tvCity.setText(city.getAreaName());
                        } else {
                            tvProvince.setText(province.getAreaName());
                            tvCity.setText(city.getAreaName());
                            tvCounty.setText(county.getAreaName());
                        }
                    }
                });
                task.setMoreCallback(new AddressPickTask.MoreCallback() {
                    @Override
                    public void onAddressInitFailed() {
                        ToastUtils.showToast(InfoIdentityActivity.this, "数据初始化失败");
                    }

                    @Override
                    public void onFirstWheeled(int index, String item) {
                        tvProvince.setText(item);
                    }

                    @Override
                    public void onSecondWheeled(int index, String item) {
                        tvCity.setText(item);
                    }

                    @Override
                    public void onThirdWheeled(int index, String item) {
                        tvCounty.setText(item);
                    }
                });
                task.execute("北京市", "北京市", "朝阳区");
                break;
            case R.id.ll_highest_education://学历
                picker = new SinglePicker<>(this, new String[]{
                        "高中以下", "高中", "中专", "大专", "本科", "硕士", "博士"
                });
                SinglePickContarts.getIntance().Singlepicker(picker, new SingleItem() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvEducation.setText(item);
                    }
                }, "高中", 1);
                break;
            case R.id.ll_marital_status://婚姻状况
                picker = new SinglePicker<>(this, new String[]{
                        "未婚", "已婚未育", "已婚已育", "离异", "其他"
                });
                SinglePickContarts.getIntance().Singlepicker(picker, new SingleItem() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvMaritalStatus.setText(item);
                    }
                }, "已婚未育", 1);
                break;
        }
    }

    //当前输入数据是否存在
    private Boolean isExistence = false;
    //是否保存或更新本地数据成功
    private Boolean isSaveSuccess = false;

    private int infoIdenId;

    private void setInfoIdentity() {
        // 1.先查询表中是否有此条数据
        List<InfoIdentityBean> mInfoList = InfoIdentityBean.findAll(InfoIdentityBean.class);
        for (InfoIdentityBean infoBean : mInfoList) {
            if (String.valueOf(infoBean.getIdentityCode()).equals(IdentityCode)) {
                infoIdenId = infoBean.getId();
                isExistence = true;
                break;
            }
        }
        if (!isExistence) {
            // 2.如果不存在,保存身份信息
            InfoIdentityBean infoIdentityBean = new InfoIdentityBean();
            infoIdentityBean.setIdentityName(IdentityName);
            infoIdentityBean.setIdentityCode(IdentityCode);
            infoIdentityBean.setProvince(Province);
            infoIdentityBean.setCity(City);
            infoIdentityBean.setCounty(County);
            infoIdentityBean.setDetailedArs(DetailedArs);
            infoIdentityBean.setEducation(Education);
            infoIdentityBean.setMaritalStatus(MaritalStatus);
            if (infoIdentityBean.save()) {
                isSaveSuccess = true;
            } else {
                isSaveSuccess = false;
            }
        } else {
            // 3.如果存在,修改身份信息
            InfoIdentityBean infoIdentityBean = new InfoIdentityBean();
            infoIdentityBean.setIdentityName(IdentityName);
            infoIdentityBean.setIdentityCode(IdentityCode);
            infoIdentityBean.setProvince(Province);
            infoIdentityBean.setCity(City);
            infoIdentityBean.setCounty(County);
            infoIdentityBean.setDetailedArs(DetailedArs);
            infoIdentityBean.setEducation(Education);
            infoIdentityBean.setMaritalStatus(MaritalStatus);
            infoIdentityBean.update(infoIdenId);
            isSaveSuccess = true;
        }
        if (!isSaveSuccess) {
            ToastUtils.showToast(InfoIdentityActivity.this, "身份信息提交失败!");
            return;
        }

        HttpManager.getInstance().doIdentityRequest("InfoIdentityActivity", IdentityName, IdentityCode, Province, City, County, DetailedArs, Education, MaritalStatus, new HttpCallBack<JsonLoginObject>(InfoIdentityActivity.this, true) {
            @Override
            public void onError(Throwable throwable) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToastInThread(InfoIdentityActivity.this, "网络不可用!");
                    }
                });
            }

            @Override
            public void onSuccess(JsonLoginObject date) {
                if (date != null) {
                    final JsonLoginObject.ValueBean Value = date.getValue();
                    if (Value.getCode().equals("1")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToastInThread(InfoIdentityActivity.this, "身份信息提交成功!");
                            }
                        });
                        //保存成功后的信息
                        SharedUtils.putString(InfoIdentityActivity.this, "IdentityName", IdentityName);
                        SharedUtils.putString(InfoIdentityActivity.this, "IdentityCode", IdentityCode);
                        SharedUtils.putString(InfoIdentityActivity.this, "Province", Province);
                        SharedUtils.putString(InfoIdentityActivity.this, "City", City);
                        SharedUtils.putString(InfoIdentityActivity.this, "County", County);
                        SharedUtils.putString(InfoIdentityActivity.this, "DetailedArs", DetailedArs);
                        SharedUtils.putString(InfoIdentityActivity.this, "Education", Education);
                        SharedUtils.putString(InfoIdentityActivity.this, "MaritalStatus", MaritalStatus);

                        loadingBtn.stopLoading();//停止加载按钮动画
                        loadingBtn.setClickable(true);
                        ToastUtils.showToast(InfoIdentityActivity.this, "身份信息提交成功!");
                        finish();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToastInThread(InfoIdentityActivity.this, "身份信息提交失败");
                            }
                        });
                    }
                    LogUtil.i(InfoIdentityActivity.this, "身份信息提交" + Value.getMsg());
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showToastInThread(InfoIdentityActivity.this, "网络不可用!");
                        }
                    });
                }
            }
        });
    }

    private String IdentityName, IdentityCode, Province, City, County, DetailedArs, Education, MaritalStatus;

    private boolean validate() {
        IdentityName = etIdentityName.getText().toString();
        IdentityCode = etIdentityCode.getText().toString();
        Province = tvProvince.getText().toString();
        City = tvCity.getText().toString();
        County = tvCounty.getText().toString();
        DetailedArs = etDetailedArs.getText().toString();
        Education = tvEducation.getText().toString();
        MaritalStatus = tvMaritalStatus.getText().toString();

        if (!EmptyUtils.isNotEmpty(IdentityName)) {
            ToastUtils.showToast(InfoIdentityActivity.this, "身份证名称为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(IdentityCode)) {
            ToastUtils.showToast(InfoIdentityActivity.this, "身份证号为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(Province) || !EmptyUtils.isNotEmpty(City) || !EmptyUtils.isNotEmpty(County)) {
            ToastUtils.showToast(InfoIdentityActivity.this, "常住地址为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(DetailedArs)) {
            ToastUtils.showToast(InfoIdentityActivity.this, "详细地址为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(Education)) {
            ToastUtils.showToast(InfoIdentityActivity.this, "最高学历为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(MaritalStatus)) {
            ToastUtils.showToast(InfoIdentityActivity.this, "婚姻状况为空!");
            return false;
        }
        return true;
    }
}
