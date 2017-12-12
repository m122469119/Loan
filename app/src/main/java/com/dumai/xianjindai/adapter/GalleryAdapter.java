package com.dumai.xianjindai.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dumai.xianjindai.R;

/**
 * @author haoruigang
 * @Package com.dumai.xianjindai.adapter
 * @project CashLoan
 * @Description: gallery画廊适配器 TODO(这里用一句话描述这个类的作用)
 * @date 2017/11/20 12:53
 */
public class GalleryAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gallery,
                    parent, false);
        }

        ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
        iv.setImageResource(
                parent.getContext().getResources().getIdentifier("pic" + position, "drawable",
                        parent.getContext().getPackageName()));
        return convertView;
    }
}
