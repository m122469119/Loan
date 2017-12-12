package com.dumai.xianjindai.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.activity.InfoCreditActivity;
import com.dumai.xianjindai.activity.InfoMoreActivity;
import com.dumai.xianjindai.base.BaseFragment;
import com.dumai.xianjindai.util.view.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 名称：InfoMoreFragment.java
 * 描述：更多认证信息
 *
 * @author haoruigang
 * @version v1.0
 * @date： 2017-11-23 10:50:13
 */
public final class InfoMoreFragment extends BaseFragment {

    @BindView(R.id.frg_bg)
    FrameLayout frgBg;
    @BindView(R.id.submit_btn_info)
    Button submitBtnInfo;

    private String title;
    private int currentItem;

    @Override
    protected int getContentViewId() {
        return R.layout.info_fragment_identity;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        //无标题
    }

    @Override
    public void onUpdateUI() {
        // 获取Activity传递过来的参数
        Bundle mBundle = getArguments();
        title = mBundle.getString("titlebar");
        currentItem = mBundle.getInt("position");
        frgBg.setBackground(getResources().getDrawable(R.drawable.phone_4));
    }

    public void init() {

    }

    @OnClick(R.id.submit_btn_info)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit_btn_info:
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                startActivity(new Intent(getActivity(), InfoMoreActivity.class).putExtras(bundle));
                break;
        }
    }
}
