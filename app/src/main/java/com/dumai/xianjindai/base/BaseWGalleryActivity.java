package com.dumai.xianjindai.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dumai.xianjindai.R;
import com.dumai.xianjindai.util.view.ToolbarHelper;
import com.wgallery.android.WGallery;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author haoruigang
 *         2017年11月20日11:55:16
 *         Gallery画廊基类
 */
public class BaseWGalleryActivity extends BaseFragment {


    @BindView(R.id.wgallery)
    WGallery gallery;
    Unbinder unbinder;

    @Override
    protected int getContentViewId() {
        return R.layout.dm_fragment_cash;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("手机贷Pro");
    }

    @Override
    public void onUpdateUI() {
        gallery.setAdapter(initGalleryAdapter());//适配器
        gallery.setScalePivot(WGallery.SCALE_PIVOT_CENTER);//显示居中
        gallery.setSelectedScale(0.8f + 0.1f * 4);//选中gallery效果 第三位0-4
        gallery.setUnselectedAlpha(3 * 1.f / 10);//未选中gallery效果 第一位0-10

    }

    protected BaseAdapter initGalleryAdapter() {
        throw new RuntimeException("必须重写该方法");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
