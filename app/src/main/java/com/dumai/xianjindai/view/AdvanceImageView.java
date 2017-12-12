package com.dumai.xianjindai.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.dumai.xianjindai.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by haoruigang on 16/8/10.
 * Picasso图片加载
 */
public class AdvanceImageView extends ImageView {
    public AdvanceImageView(Context context) {
        super(context);
    }

    public AdvanceImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdvanceImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setUrl(String url) {
        Picasso.with(getContext())
                .load(url).fit()
                .placeholder(R.drawable.loading_thumb)
                .error(R.drawable.loading_thumb)
                .into(this);
    }

    public void setUrl(String url, ImageViewSpec spec) {
        RequestCreator creator = Picasso.with(getContext()).load(url);
        if (spec.isCircle) {
            creator.transform(new CropCircleTransformation());
        }
        creator.fit()
                .placeholder(R.drawable.loading_thumb)
                .error(R.drawable.loading_thumb)
                .into(this);
    }


    public static class ImageViewSpec {
        private boolean isCircle;

        public boolean isCircle() {
            return isCircle;
        }

        public void setCircle(boolean circle) {
            isCircle = circle;
        }
    }

}
