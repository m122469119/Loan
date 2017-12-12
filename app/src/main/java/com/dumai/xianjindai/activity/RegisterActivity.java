package com.dumai.xianjindai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.http.http.HttpCallBack;
import com.dumai.xianjindai.http.http.HttpManager;
import com.dumai.xianjindai.jparser.JsonLoginObject;
import com.dumai.xianjindai.util.EmptyUtils;
import com.dumai.xianjindai.util.LogUtil;
import com.dumai.xianjindai.util.SharedUtils;
import com.dumai.xianjindai.util.ToastUtils;
import com.dumai.xianjindai.util.view.ToolbarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册
 * haoruigang
 * 2017-11-23 16:10:21
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_register;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("注册");
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


    @OnClick(R.id.btn_register)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                setRegister();
                break;
        }
    }

    private void setRegister() {
        if (validate()) {

            HttpManager.getInstance().doReginster("RegisterActivity", phoneNum, psd, new HttpCallBack<JsonLoginObject>(RegisterActivity.this, true) {
                @Override
                public void onError(Throwable throwable) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showToastInThread(RegisterActivity.this, "网络不可用!");
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
                                    ToastUtils.showToastInThread(RegisterActivity.this, "注册成功");
                                }
                            });
                            //保存登录成功后的信息
                            SharedUtils.putString(RegisterActivity.this, "regusername", phoneNum);
                            SharedUtils.putString(RegisterActivity.this, "regpsd", psd);
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showToastInThread(RegisterActivity.this, "注册失败!");
                                }
                            });
                        }
                        LogUtil.i(RegisterActivity.this, "注册" + Value.getMsg());
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToastInThread(RegisterActivity.this, "网络不可用!");
                            }
                        });
                    }
                }
            });
        }
    }

    private String phoneNum, psd;

    private boolean validate() {
        phoneNum = etPhoneNumber.getText().toString();
        psd = etPassword.getText().toString().trim();
        if (!EmptyUtils.isNotEmpty(phoneNum)) {
            ToastUtils.showToast(RegisterActivity.this, "账号为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(psd)) {
            ToastUtils.showToast(RegisterActivity.this, "密码为空!");
            return false;
        }
        return true;
    }
}
