package com.dumai.xianjindai.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dumai.xianjindai.R;
import com.dumai.xianjindai.commons.ToUIEvent;
import com.dumai.xianjindai.util.view.ToolbarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

public abstract class BaseFragment extends Fragment {
    protected String TAG = getClass().getSimpleName();
    protected View mView;
    private Unbinder unbinder;
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    protected abstract int getContentViewId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getContentViewId(), container, false);
        unbinder = ButterKnife.bind(this, mView);
        onUpdateUI();
        initActionBar();
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    protected abstract void initToolbar(ToolbarHelper toolbarHelper);

    public void initActionBar() {
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            // 默认不显示原生标题
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            initToolbar(new ToolbarHelper(toolbar));
        }
    }

    public abstract void onUpdateUI();

    public void onEvent(ToUIEvent toUIEvent) {

    }
}
