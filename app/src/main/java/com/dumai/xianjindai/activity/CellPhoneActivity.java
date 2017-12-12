package com.dumai.xianjindai.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.bean.InfoCellPhoenBean;
import com.dumai.xianjindai.bean.InfoIdentityBean;
import com.dumai.xianjindai.util.EmptyUtils;
import com.dumai.xianjindai.util.SharedUtils;
import com.dumai.xianjindai.util.ToastUtils;
import com.dumai.xianjindai.util.view.ToolbarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 名称：CellPhoneActivity.java
 * 描述：手机号认证
 *
 * @author haoruigang
 * @version v1.0
 * @date： 2017-11-27 10:17:54
 */
public class CellPhoneActivity extends BaseActivity {

    @BindView(R.id.tv_phone_number)
    EditText tvPhoneNumber;
    @BindView(R.id.tv_server_psd)
    EditText tvServerPsd;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_cell_phoen;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("手机号认证");
        toolbarHelper.setLeftMenuTitle("信用认证", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbarHelper.setMenuTitle("提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setClickable(false);
                if (!validate()) {
                    v.setClickable(true);
                    return;
                }
                setInfoCellPhone(v);
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

    @Override
    protected void init() {
        super.init();
        getInfoCellPhone();
    }

    private void getInfoCellPhone() {
        String phoneNum = SharedUtils.getString(CellPhoneActivity.this, "phoneNum");
        tvPhoneNumber.setText(phoneNum);
        String serverPsd = SharedUtils.getString(CellPhoneActivity.this, "serverPsd");
        tvServerPsd.setText(serverPsd);
    }

    //当前输入数据是否存在
    private Boolean isExistence = false;
    //是否保存或更新本地数据成功
    private Boolean isSaveSuccess = false;
    private int infoCellPhoneId;

    private void setInfoCellPhone(View v) {
        // 1.先查询表中是否有此条数据
        List<InfoCellPhoenBean> mInfoList = InfoIdentityBean.findAll(InfoCellPhoenBean.class);
        for (InfoCellPhoenBean infoBean : mInfoList) {
            if (String.valueOf(infoBean.getPhoneNumber()).equals(phoneNum)) {
                infoCellPhoneId = infoBean.getId();
                isExistence = true;
                break;
            }
        }

        if (!isExistence) {
            // 2.如果不存在,保存工作信息
            InfoCellPhoenBean infojobBean = new InfoCellPhoenBean();
            infojobBean.setPhoneNumber(phoneNum);
            infojobBean.setServerPsd(serverPsd);
            if (infojobBean.save()) {
                isSaveSuccess = true;
            } else {
                isSaveSuccess = false;
            }
        } else {
            // 3.如果存在,修改工作信息
            InfoCellPhoenBean infojobBean = new InfoCellPhoenBean();
            infojobBean.setPhoneNumber(phoneNum);
            infojobBean.setServerPsd(serverPsd);
            infojobBean.update(infoCellPhoneId);
            isSaveSuccess = true;
        }

        if (!isSaveSuccess) {
            ToastUtils.showToast(CellPhoneActivity.this, "手机号认证失败!");
            return;
        }

        SharedUtils.putString(CellPhoneActivity.this, "phoneNum", phoneNum);
        SharedUtils.putString(CellPhoneActivity.this, "serverPsd", serverPsd);
        v.setClickable(true);
        ToastUtils.showToast(CellPhoneActivity.this, "手机号认证成功!");
        finish();
    }

    private String phoneNum, serverPsd;

    private boolean validate() {
        phoneNum = tvPhoneNumber.getText().toString().trim();
        serverPsd = tvServerPsd.getText().toString().trim();

        if (!EmptyUtils.isNotEmpty(phoneNum)) {
            ToastUtils.showToast(CellPhoneActivity.this, "手机号为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(serverPsd)) {
            ToastUtils.showToast(CellPhoneActivity.this, "服务密码为空!");
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
