package com.dumai.xianjindai.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.bean.InfoContactBean;
import com.dumai.xianjindai.bean.InfoCreditCardBean;
import com.dumai.xianjindai.commons.ToUIEvent;
import com.dumai.xianjindai.util.DateUtil;
import com.dumai.xianjindai.util.EmptyUtils;
import com.dumai.xianjindai.util.ToastUtils;
import com.dumai.xianjindai.util.view.ToolbarHelper;
import com.dumai.xianjindai.view.pickers.SingleItem;
import com.dumai.xianjindai.view.pickers.SinglePickContarts;
import com.dumai.xianjindai.view.pickers.picker.DatePicker;
import com.dumai.xianjindai.view.pickers.picker.SinglePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 名称：CreditCardAddActivity.java
 * 描述：添加信用卡
 *
 * @author haoruigang
 * @version v1.0
 * @date： 2017-11-27 10:00:02
 */
public class CreditCardAddActivity extends BaseActivity {

    @BindView(R.id.ll_issuing_bank)
    LinearLayout llIssuingBank;
    @BindView(R.id.ll_credit_limit)
    LinearLayout llCreditLimit;
    @BindView(R.id.ll_term_validity)
    LinearLayout llTermValidity;
    @BindView(R.id.tv_my_credit_card_number)
    EditText tvMyCreditCardNumber;
    @BindView(R.id.tv_issuing_bank)
    TextView tvIssuingBank;
    @BindView(R.id.tv_credit_limit)
    TextView tvCreditLimit;
    @BindView(R.id.tv_term_month)
    TextView tvTermMonth;
    @BindView(R.id.tv_term_year)
    TextView tvTermYear;

    private SinglePicker<String> picker;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_credit_card_add;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("添加信用卡");
        toolbarHelper.setMenuTitle("提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setClickable(false);
                if (!validate()) {
                    v.setClickable(true);
                    return;
                }
                setInfoCreditCard(v);
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

    //是否保存或更新本地数据成功
    private Boolean isSaveSuccess = false;

    private void setInfoCreditCard(View v) {
        // 保存信用卡信息
        InfoCreditCardBean infoCreditCardBean = new InfoCreditCardBean();
        infoCreditCardBean.setCreditCardNum(creditCardNum);
        infoCreditCardBean.setIssuingBank(issuingBank);
        infoCreditCardBean.setCreditLimit(creditLimit);
        infoCreditCardBean.setTermMonth(termMonth);
        infoCreditCardBean.setTermYear(termYear);
        if (infoCreditCardBean.save()) {
            isSaveSuccess = true;
        } else {
            isSaveSuccess = false;
        }
        if (!isSaveSuccess) {
            ToastUtils.showToast(CreditCardAddActivity.this, "信用卡信息提交失败!");
            return;
        }
        EventBus.getDefault().post(new ToUIEvent(ToUIEvent.CREDIT_CARD_EVENT));
        v.setClickable(true);
        ToastUtils.showToast(CreditCardAddActivity.this, "信用卡信息提交成功!");
        finish();
    }

    private String creditCardNum, issuingBank, creditLimit, termMonth, termYear;

    private boolean validate() {
        creditCardNum = tvMyCreditCardNumber.getText().toString().trim();
        issuingBank = tvIssuingBank.getText().toString().trim();
        creditLimit = tvCreditLimit.getText().toString().trim();
        termMonth = tvTermMonth.getText().toString().trim();
        termYear = tvTermYear.getText().toString().trim();
        if (!EmptyUtils.isNotEmpty(creditCardNum)) {
            ToastUtils.showToast(CreditCardAddActivity.this, "信用卡号为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(issuingBank)) {
            ToastUtils.showToast(CreditCardAddActivity.this, "发卡银行为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(creditLimit)) {
            ToastUtils.showToast(CreditCardAddActivity.this, "信用额度为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(termMonth) || !EmptyUtils.isNotEmpty(termYear)) {
            ToastUtils.showToast(CreditCardAddActivity.this, "有效期为空!");
            return false;
        }
        return true;
    }


    @OnClick({R.id.ll_issuing_bank, R.id.ll_credit_limit, R.id.ll_term_validity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_issuing_bank:
                picker = new SinglePicker<>(this, new String[]{
                        "中国工商银行", "中国建设银行", "中国农业银行", "中国邮政储蓄银行", "中国银行", "招商银行", "交通银行", "平安银行", "中国民生银行", "中国光大银行", "中信银行", "兴业银行", "上海浦东发展银行", "北京银行", "上海银行"
                });
                SinglePickContarts.getIntance().Singlepicker(picker, new SingleItem() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvIssuingBank.setText(item);
                    }
                }, "中国工商银行", 0);
                break;
            case R.id.ll_credit_limit:
                picker = new SinglePicker<>(this, new String[]{
                        "3000元以下", "3000元-10000元", "10001-30000元", "30001-50000元", "50000元及以上"
                });
                SinglePickContarts.getIntance().Singlepicker(picker, new SingleItem() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvCreditLimit.setText(item);
                    }
                }, "3000元以下", 0);
                break;
            case R.id.ll_term_validity:
                int yearStr = Integer.valueOf(DateUtil.getCurrentDate("yyyy"));//年

                DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH);
                picker.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
                picker.setCanLoop(false);
                picker.setRangeStart(yearStr, 1, 1);
                picker.setRangeEnd(yearStr + 5, 12, 31);
                picker.setSelectedItem(yearStr, 1);
                picker.setWeightEnable(true);
                picker.setWheelModeEnable(true);
                picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
                    @Override
                    public void onDatePicked(String year, String month) {
                        tvTermMonth.setText(month + "/");
                        tvTermYear.setText(year);
                    }
                });
                picker.setOnWheelListener(new DatePicker.OnWheelListener() {
                    @Override
                    public void onYearWheeled(int index, String year) {
                        tvTermYear.setText(year);
                    }

                    @Override
                    public void onMonthWheeled(int index, String month) {
                        tvTermMonth.setText(month + "/");
                    }

                    @Override
                    public void onDayWheeled(int index, String day) {

                    }
                });
                picker.show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
