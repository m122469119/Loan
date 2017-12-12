package com.dumai.xianjindai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dumai.xianjindai.R;

import java.util.ArrayList;

/**
 * 名称：GridMaterial.java
 * 描述：多张图片上传选择Grid
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/12/1 11:03
 */
public class GridMaterial extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> listUrls;
    private LayoutInflater inflater;

    public GridMaterial(Context context, ArrayList<String> listUrls) {
        this.mContext = context;
        this.listUrls = listUrls;
        if (listUrls.size() == 7) {
            listUrls.remove(listUrls.size() - 1);
        }
        inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return listUrls.size();
    }

    @Override
    public String getItem(int position) {
        return listUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_image, parent, false);
            holder.image = (ImageView) convertView.findViewById(R.id.img_proof_material);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final String path = listUrls.get(position);
        if (path.equals("000000")) {
            holder.image.setImageResource(R.drawable.icon_camera);
        } else {
            Glide.with(mContext)
                    .load(path)
                    .placeholder(R.drawable.icon_camera)
                    .error(R.mipmap.default_error)
                    .centerCrop()
                    .crossFade()
                    .into(holder.image);
        }
        return convertView;
    }

    class ViewHolder {
        ImageView image;
    }
}
