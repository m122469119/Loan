package com.dumai.xianjindai.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.activity.CameraActivity;
import com.dumai.xianjindai.activity.ItemDecorationActivity;
import com.dumai.xianjindai.activity.MyDataActivity;
import com.dumai.xianjindai.base.BaseFragment;
import com.dumai.xianjindai.util.SharedUtils;
import com.dumai.xianjindai.util.view.ToolbarHelper;
import com.dumai.xianjindai.view.CircleImg;
import com.dumai.xianjindai.view.WaveViewByBezier;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * 我的
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.ll_mydata_click)
    LinearLayout llMydataClick;
    @BindView(R.id.wave_bezier)
    WaveViewByBezier waveBezier;
    @BindView(R.id.backdrop)
    CircleImg backdrop;
    @BindView(R.id.username)
    TextView username;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_fragment_mine;
    }

    @Override
    public void initActionBar() {
        super.initActionBar();
        waveBezier.startAnimation();
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setMenuTitle("消息", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ItemDecorationActivity.class));
            }
        });

        Toolbar toolbar = toolbarHelper.getToolbar();
        toolbar.setNavigationIcon(R.drawable.ic_dashboard);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ItemDecorationActivity.class));
            }
        });
    }

    @Override
    public void onUpdateUI() {
        username.setText(SharedUtils.getString(getContext(), "username"));
    }

    @OnClick({R.id.ll_mydata_click, R.id.backdrop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_mydata_click:
                startActivity(new Intent(getContext(), MyDataActivity.class));
                break;
            case R.id.backdrop:
                takePicture();
                break;
        }
    }

    public void takePicture() {
        //上传图片
        Intent intent = new Intent(getActivity(), CameraActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CameraActivity.ISUPDATE, false);
        intent.putExtras(bundle);
        startActivityForResult(intent, CameraActivity.ACTIVITY_RESULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == RESULT_OK) {
            if (requestCode == CameraActivity.ACTIVITY_RESULT) {
                Uri picture = data.getData();
                Bitmap photo = BitmapFactory.decodeFile(picture.getPath());
                backdrop.setImageBitmap(photo);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (waveBezier != null) {
            waveBezier.pauseAnimation();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (waveBezier != null) {
            waveBezier.resumeAnimation();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (waveBezier != null) {
            waveBezier.stopAnimation();
        }
    }
}
