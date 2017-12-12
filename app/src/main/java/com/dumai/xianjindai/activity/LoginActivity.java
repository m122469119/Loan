package com.dumai.xianjindai.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.base.BaseActivity;
import com.dumai.xianjindai.bean.UserBean;
import com.dumai.xianjindai.http.http.HttpCallBack;
import com.dumai.xianjindai.http.http.HttpManager;
import com.dumai.xianjindai.jparser.JsonLoginObject;
import com.dumai.xianjindai.util.EmptyUtils;
import com.dumai.xianjindai.util.LogUtil;
import com.dumai.xianjindai.util.SharedUtils;
import com.dumai.xianjindai.util.ToastUtils;
import com.dumai.xianjindai.util.view.ToolbarHelper;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * haoruigang
 * 2017年11月17日17:42:41
 */
public class LoginActivity extends BaseActivity {

    private String TAG = getClass().getName();

    @BindView(R.id.user_id)
    EditText userId;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.rl_user_id)
    RelativeLayout rlUserId;
    @BindView(R.id.rl_password)
    RelativeLayout rlPassword;
    @BindView(R.id.registerAction)
    TextView registerAction;
    @BindView(R.id.forget_psd)
    TextView forgetPsd;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_activity_login;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("登录");
        Toolbar toolbar = toolbarHelper.getToolbar();
        // 显示导航按钮
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

        //第一次保存的值
        String userName, Psd;
        userName = SharedUtils.getString(LoginActivity.this, "username");
        if (EmptyUtils.isNotEmpty(userName)) {
            userId.setText(userName);
        }
        Psd = SharedUtils.getString(LoginActivity.this, "psd");
        if (EmptyUtils.isNotEmpty(Psd)) {
            password.setText(Psd);
        }
    }


    @OnClick({R.id.btn_login, R.id.registerAction, R.id.forget_psd})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                setLogin();
                break;
            case R.id.registerAction:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.forget_psd:
                startActivity(new Intent(LoginActivity.this, ForgetPsdActivity.class));
                break;
        }
    }

    //当前输入数据是否存在
    private Boolean isExistence = false;
    //是否保存或更新本地数据成功
    private Boolean isSaveSuccess = false;
    private String username, psd;

    private void setLogin() {
        if (validate()) {
            int userBeanId = 0;
            //如果第一次（上次）输入的用户名与当前输入的不相等,新建一条UserBean表数据并保存
            List<UserBean> mUserList = DataSupport.findAll(UserBean.class);
            for (UserBean uBean : mUserList) {
                if (String.valueOf(uBean.getUsername()).equals(username)) {
                    userBeanId = uBean.getId();//当前用户存在获取id
                    isExistence = true;
                    break;
                }
            }
            if (!isExistence) {
                //保存UserBean
                UserBean adduserBean = new UserBean();
                adduserBean.setUsername(username);
                adduserBean.setPsd(psd);
                if (adduserBean.save()) {
                    isSaveSuccess = true;
                } else {
                    isSaveSuccess = false;
                }
            } else {
                UserBean updateUser = new UserBean();
                updateUser.setUsername(username);
                updateUser.setPsd(psd);
                updateUser.update(userBeanId);
                isSaveSuccess = true;
            }
            if (!isSaveSuccess) {
                LogUtil.e(LoginActivity.this, "UserBean保存或更新数据失败!");
                return;
            }
            LogUtil.i(LoginActivity.this, "UserBean保存或更新数据成功!");
            HttpManager.getInstance().doLoginRequest("LoginActivity", username, psd, new HttpCallBack<JsonLoginObject>(LoginActivity.this, true) {
                @Override
                public void onError(Throwable throwable) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showToastInThread(LoginActivity.this, "网络不可用!");
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
                                    ToastUtils.showToastInThread(LoginActivity.this, "登录成功!");
                                }
                            });
                            //保存登录成功后的信息
                            SharedUtils.putString(LoginActivity.this, "username", username);
                            SharedUtils.putString(LoginActivity.this, "psd", psd);
                            // 登录成功删除UserBean表
                            // int deluserBean = DataSupport.deleteAll(UserBean.class);
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showToastInThread(LoginActivity.this, "登录失败");
                                }
                            });
                        }
                        LogUtil.i(LoginActivity.this, "登录" + Value.getMsg());
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToastInThread(LoginActivity.this, "网络不可用!");
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     * 非空验证
     *
     * @return
     */
    private boolean validate() {
        username = userId.getText().toString().trim();
        psd = password.getText().toString().trim();
        if (!EmptyUtils.isNotEmpty(username)) {
            ToastUtils.showToast(LoginActivity.this, "账号为空!");
            return false;
        }
        if (!EmptyUtils.isNotEmpty(psd)) {
            ToastUtils.showToast(LoginActivity.this, "密码为空!");
            return false;
        }
        return true;
    }
}
