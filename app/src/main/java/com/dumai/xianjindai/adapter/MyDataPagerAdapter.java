package com.dumai.xianjindai.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dumai.xianjindai.fragments.InfoContactFragment;
import com.dumai.xianjindai.fragments.InfoCreditFragment;
import com.dumai.xianjindai.fragments.InfoIdentityFragment;
import com.dumai.xianjindai.fragments.InfoJobFragment;
import com.dumai.xianjindai.fragments.InfoMoreFragment;
import com.dumai.xianjindai.view.viewpagerindicator.IconPagerAdapter;

import java.util.ArrayList;

/**
 * 名称：MyDataPagerAdapter
 * 描述：我的资料认证信息
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/22 17:38
 */
public class MyDataPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    private Context context;
    private ArrayList<String> CONTENT;
    private int[] ICONS;

    public MyDataPagerAdapter(FragmentManager fm, Context context, ArrayList<String> CONTENT, int[] ICONS) {
        super(fm);
        this.context = context;
        this.CONTENT = CONTENT;
        this.ICONS = ICONS;
    }

    @Override
    public Fragment getItem(int position) {
//        if (position >= CONTENT.size()) {//防止数组越界
//            position = 0;
//        }
        // 新建一个Fragment来展示ViewPager item的内容，并传递参数
        Fragment fragment;
        Bundle args = new Bundle();
        args.putString("titlebar", CONTENT.get(position % CONTENT.size()));
        args.putInt("position", position);
        switch (position) {
            case 0:
                fragment = new InfoIdentityFragment();
                fragment.setArguments(args);
                return fragment;// 身份信息
            case 1:
                fragment = new InfoJobFragment();
                fragment.setArguments(args);
                return fragment;// 工作信息
            case 2:
                fragment = new InfoContactFragment();
                fragment.setArguments(args);
                return fragment;// 联系信息
            case 3:
                fragment = new InfoCreditFragment();
                fragment.setArguments(args);
                return fragment;// 信用信息
            case 4:
                fragment = new InfoMoreFragment();
                fragment.setArguments(args);
                return fragment;// 更多信息
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CONTENT.get(position % CONTENT.size()).toUpperCase();
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[index];
    }

    @Override
    public int getCount() {
        return CONTENT.size();
    }
}
