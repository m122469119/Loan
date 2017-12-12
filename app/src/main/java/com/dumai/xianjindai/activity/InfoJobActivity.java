package com.dumai.xianjindai.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.bean.InfoIdentityBean;
import com.dumai.xianjindai.bean.InfoJobBean;
import com.dumai.xianjindai.util.EmptyUtils;
import com.dumai.xianjindai.util.SharedUtils;
import com.dumai.xianjindai.util.ToastUtils;
import com.dumai.xianjindai.util.view.ToolbarHelper;
import com.dumai.xianjindai.view.AddressPickTask;
import com.dumai.xianjindai.view.CareerTypeTask;
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
 * 工作信息
 * haoruigang
 * 2017-11-24 14:19:04
 */
public class InfoJobActivity extends BaseActivity {

    @BindView(R.id.ll_career_type)
    LinearLayout llCareerType;
    @BindView(R.id.ll_provincial_city)
    LinearLayout llProvincialCity;
    @BindView(R.id.ll_monthly_income)
    LinearLayout llMonthlyIncome;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_county)
    TextView tvCounty;
    @BindView(R.id.loading_btn)
    LoadingButton loadingBtn;
    @BindView(R.id.tv_monthly_income)
    TextView tvMonthlyIncome;
    @BindView(R.id.tv_career_type1)
    TextView tvCareerType1;
    @BindView(R.id.tv_career_type2)
    TextView tvCareerType2;
    @BindView(R.id.tv_career_type3)
    TextView tvCareerType3;
    @BindView(R.id.et_detailed_ars)
    EditText etDetailedArs;
    @BindView(R.id.et_unit_name)
    EditText etUnitName;
    @BindView(R.id.et_place)
    EditText etPlace;
    @BindView(R.id.et_contact_number)
    EditText etContactNumber;
    private String title;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_info_job;
    }

    @Override
    protected void init() {
        super.init();
        Bundle mBundle = getIntent().getExtras();
        title = mBundle.getString("title");

        getInfoJob();
    }

    private void getInfoJob() {
        String careetType1 = SharedUtils.getString(InfoJobActivity.this, "CareetType1");
        tvCareerType1.setText(careetType1);
        String CareetType2 = SharedUtils.getString(InfoJobActivity.this, "CareetType2");
        tvCareerType2.setText(CareetType2);
        String CareetType3 = SharedUtils.getString(InfoJobActivity.this, "CareetType3");
        tvCareerType3.setText(CareetType3);
        String unitName = SharedUtils.getString(InfoJobActivity.this, "UnitName");
        etUnitName.setText(unitName);
        String province = SharedUtils.getString(InfoJobActivity.this, "Province1");
        tvProvince.setText(province);
        String city = SharedUtils.getString(InfoJobActivity.this, "City1");
        tvCity.setText(city);
        String county = SharedUtils.getString(InfoJobActivity.this, "County1");
        tvCounty.setText(county);
        String detailed = SharedUtils.getString(InfoJobActivity.this, "DetailedArs1");
        etDetailedArs.setText(detailed);
        String contactNmuber = SharedUtils.getString(InfoJobActivity.this, "ContactNumber");
        etContactNumber.setText(contactNmuber);
        String place = SharedUtils.getString(InfoJobActivity.this, "Place");
        etPlace.setText(place);
        String monthlyIncome = SharedUtils.getString(InfoJobActivity.this, "MonthlyIncome");
        tvMonthlyIncome.setText(monthlyIncome);
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

    @OnClick({R.id.ll_career_type, R.id.ll_provincial_city, R.id.ll_monthly_income, R.id.loading_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_career_type://职位类型
                CareerTypeTask careertask = new CareerTypeTask(this);
                careertask.setHideProvince(false);
                careertask.setHideCounty(false);
                careertask.setCallback(new CareerTypeTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        ToastUtils.showToast(InfoJobActivity.this, "数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
                            tvCareerType1.setText(province.getAreaName());
                            tvCareerType2.setText(city.getAreaName());
                        } else {
                            tvCareerType1.setText(province.getAreaName());
                            tvCareerType2.setText(city.getAreaName());
                            tvCareerType3.setText(county.getAreaName());
                        }
                    }
                });
                careertask.setMoreCallback(new CareerTypeTask.MoreCallback() {
                    @Override
                    public void onAddressInitFailed() {
                        ToastUtils.showToast(InfoJobActivity.this, "数据初始化失败");
                    }

                    @Override
                    public void onFirstWheeled(int index, String item) {
                        tvCareerType1.setText(item);
                    }

                    @Override
                    public void onSecondWheeled(int index, String item) {
                        tvCareerType2.setText(item);
                    }

                    @Override
                    public void onThirdWheeled(int index, String item) {
                        tvCareerType3.setText(item);
                    }
                });
                careertask.execute("自由职业", "无业", "无业");
                break;
            case R.id.ll_provincial_city://地址
                AddressPickTask addresstask = new AddressPickTask(this);
                addresstask.setHideProvince(false);
                addresstask.setHideCounty(false);
                addresstask.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        ToastUtils.showToast(InfoJobActivity.this, "数据初始化失败");
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
                addresstask.setMoreCallback(new AddressPickTask.MoreCallback() {
                    @Override
                    public void onAddressInitFailed() {
                        ToastUtils.showToast(InfoJobActivity.this, "数据初始化失败");
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
                addresstask.execute("北京市", "北京市", "朝阳区");
                break;
            case R.id.ll_monthly_income://月收入
                SinglePicker<String> picker = new SinglePicker<>(this, new String[]{
                        "2000以下", "2000-3000元", "3001-5000元", "5001-8000元", "8000-12000元", "12001-15000元", "15001-18000元", "18000以上"
                });
                SinglePickContarts.getIntance().Singlepicker(picker, new SingleItem() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvMonthlyIncome.setText(item);
                    }
                }, "2000以下", 0);
                break;
            case R.id.loading_btn://提交
                loadingBtn.startLoading();//开始加载按钮动画
                loadingBtn.setClickable(false);
                if (!validate()) {
                    loadingBtn.stopLoading();//停止加载按钮动画
                    loadingBtn.setClickable(true);
                    return;
                }

                setInfoJob();

                break;
        }
    }

    //当前输入数据是否存在
    private Boolean isExistence = false;
    //是否保存或更新本地数据成功
    private Boolean isSaveSuccess = false;

    private int infoJobId;

    private void setInfoJob() {
        // 1.先查询表中是否有此条数据
        List<InfoJobBean> mInfoList = InfoIdentityBean.findAll(InfoJobBean.class);
        for (InfoJobBean infoBean : mInfoList) {
            if (String.valueOf(infoBean.getUnitname()).equals(UnitName)) {
                infoJobId = infoBean.getId();
                isExistence = true;
                break;
            }
        }
        if (!isExistence) {
            // 2.如果不存在,保存工作信息
            InfoJobBean infojobBean = new InfoJobBean();
            infojobBean.setCareertype1(CareetType1);
            infojobBean.setCareertype2(CareetType2);
            infojobBean.setCareertype3(CareetType3);
            infojobBean.setUnitname(UnitName);
            infojobBean.setProvince(Province);
            infojobBean.setCity(City);
            infojobBean.setCounty(County);
            infojobBean.setDetailedArs(DetailedArs);
            infojobBean.setContactnumber(ContactNumber);
            infojobBean.setPlace(Place);
            infojobBean.setMonthlyincome(MonthlyIncome);
            if (infojobBean.save()) {
                isSaveSuccess = true;
            } else {
                isSaveSuccess = false;
            }
        } else {
            // 3.如果存在,修改工作信息
            InfoJobBean infojobBean = new InfoJobBean();
            infojobBean.setCareertype1(CareetType1);
            infojobBean.setCareertype2(CareetType2);
            infojobBean.setCareertype3(CareetType3);
            infojobBean.setUnitname(UnitName);
            infojobBean.setProvince(Province);
            infojobBean.setCity(City);
            infojobBean.setCounty(County);
            infojobBean.setDetailedArs(DetailedArs);
            infojobBean.setContactnumber(ContactNumber);
            infojobBean.setPlace(Place);
            infojobBean.setMonthlyincome(MonthlyIncome);
            infojobBean.update(infoJobId);
            isSaveSuccess = true;
        }
        if (!isSaveSuccess) {
            ToastUtils.showToast(InfoJobActivity.this, "工作信息提交失败!");
            return;
        }

        SharedUtils.putString(InfoJobActivity.this, "CareetType1", CareetType1);
        SharedUtils.putString(InfoJobActivity.this, "CareetType2", CareetType2);
        SharedUtils.putString(InfoJobActivity.this, "CareetType3", CareetType3);
        SharedUtils.putString(InfoJobActivity.this, "UnitName", UnitName);
        SharedUtils.putString(InfoJobActivity.this, "Province1", Province);
        SharedUtils.putString(InfoJobActivity.this, "City1", City);
        SharedUtils.putString(InfoJobActivity.this, "County1", County);
        SharedUtils.putString(InfoJobActivity.this, "DetailedArs1", DetailedArs);
        SharedUtils.putString(InfoJobActivity.this, "ContactNumber", ContactNumber);
        SharedUtils.putString(InfoJobActivity.this, "Place", Place);
        SharedUtils.putString(InfoJobActivity.this, "MonthlyIncome", MonthlyIncome);

        loadingBtn.stopLoading();//停止加载按钮动画
        loadingBtn.setClickable(true);
        ToastUtils.showToast(InfoJobActivity.this, "工作信息提交成功!");
        finish();

    }

    private String CareetType1, CareetType2, CareetType3, UnitName, Province, City, County, DetailedArs, ContactNumber, Place, MonthlyIncome;

    private boolean validate() {
        CareetType1 = tvCareerType1.getText().toString().trim();
        CareetType2 = tvCareerType2.getText().toString().trim();
        CareetType3 = tvCareerType3.getText().toString().trim();
        UnitName = etUnitName.getText().toString().trim();
        Province = tvProvince.getText().toString();
        City = tvCity.getText().toString();
        County = tvCounty.getText().toString();
        DetailedArs = etDetailedArs.getText().toString();
        ContactNumber = etContactNumber.getText().toString();
        Place = etPlace.getText().toString();
        MonthlyIncome = tvMonthlyIncome.getText().toString();

        if (!EmptyUtils.isNotEmpty(CareetType1) || !EmptyUtils.isNotEmpty(CareetType2) || !EmptyUtils.isNotEmpty(CareetType3)) {
            ToastUtils.showToast(InfoJobActivity.this, "职业类型为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(UnitName)) {
            ToastUtils.showToast(InfoJobActivity.this, "单位名称为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(Province) || !EmptyUtils.isNotEmpty(City) || !EmptyUtils.isNotEmpty(County)) {
            ToastUtils.showToast(InfoJobActivity.this, "常住地址为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(DetailedArs)) {
            ToastUtils.showToast(InfoJobActivity.this, "详细地址为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(ContactNumber)) {
            ToastUtils.showToast(InfoJobActivity.this, "联系电话为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(Place)) {
            ToastUtils.showToast(InfoJobActivity.this, "职位为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(MonthlyIncome)) {
            ToastUtils.showToast(InfoJobActivity.this, "月收入为空!");
            return false;
        }
        return true;
    }
}
