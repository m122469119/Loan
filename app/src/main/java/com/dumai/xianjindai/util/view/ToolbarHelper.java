package com.dumai.xianjindai.util.view;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.dumai.xianjindai.R;

import butterknife.BindView;


/**
 * ToolBar标题栏
 * Created by haoruigang on 2016/11/8.
 */
public class ToolbarHelper {

    private Toolbar mToolbar;

    public ToolbarHelper(Toolbar toolbar) {
        this.mToolbar = toolbar;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void setLeftMenuTitle(String leftmenuTitle, View.OnClickListener listener) {
        TextView leftMenuTitle = (TextView) mToolbar.findViewById(R.id.left_toolbar_menu_title);
        leftMenuTitle.setText(leftmenuTitle);
        leftMenuTitle.setOnClickListener(listener);
    }

    public void setTitle(String title) {
        TextView titleTV = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        titleTV.setText(title);
    }

    public void setMenuTitle(String menuTitle, View.OnClickListener listener) {
        TextView menuTitleTV = (TextView) mToolbar.findViewById(R.id.toolbar_menu_title);
        menuTitleTV.setText(menuTitle);
        menuTitleTV.setOnClickListener(listener);
    }
}
