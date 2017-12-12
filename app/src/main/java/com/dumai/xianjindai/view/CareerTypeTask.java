package com.dumai.xianjindai.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.alibaba.fastjson.JSON;
import com.dumai.xianjindai.view.pickers.entity.Province;
import com.dumai.xianjindai.view.pickers.listeners.OnLinkageListener;
import com.dumai.xianjindai.view.pickers.listeners.OnMoreWheelListener;
import com.dumai.xianjindai.view.pickers.picker.AddressPicker;
import com.dumai.xianjindai.view.pickers.util.ConvertUtils;

import java.util.ArrayList;


/**
 * 获取职业类型选择器
 *
 * @author haoruigang
 *         2017-11-24 18:14:34
 */
public class CareerTypeTask extends AsyncTask<String, Void, ArrayList<Province>> {
    private Activity activity;
    private ProgressDialog dialog;
    private MoreCallback moreCallback;
    private Callback callback;
    private String selectedProvince = "", selectedCity = "", selectedCounty = "";
    private boolean hideProvince = false;
    private boolean hideCounty = false;
    private boolean canLoop = false;

    public CareerTypeTask(Activity activity) {
        this.activity = activity;
    }

    public void setHideProvince(boolean hideProvince) {
        this.hideProvince = hideProvince;
    }

    public void setHideCounty(boolean hideCounty) {
        this.hideCounty = hideCounty;
    }

    public void setMoreCallback(MoreCallback moreCallback) {
        this.moreCallback = moreCallback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(activity, null, "正在初始化数据...", true, true);
    }

    @Override
    protected ArrayList<Province> doInBackground(String... params) {
        if (params != null) {
            switch (params.length) {
                case 1:
                    selectedProvince = params[0];
                    break;
                case 2:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    break;
                case 3:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    selectedCounty = params[2];
                    break;
                default:
                    break;
            }
        }
        ArrayList<Province> data = new ArrayList<>();
        try {
            String json = ConvertUtils.toString(activity.getAssets().open("career.json"));
            data.addAll(JSON.parseArray(json, Province.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(ArrayList<Province> result) {
        dialog.dismiss();
        if (result.size() > 0) {
            AddressPicker picker = new AddressPicker(activity, result);
            picker.setCanLoop(false);
            picker.setTitleText("请选择");
            picker.setCancelText("取消");
            picker.setSubmitText("确定");
            picker.setHideProvince(hideProvince);
            picker.setHideCounty(hideCounty);
            picker.setWheelModeEnable(true);
            if (hideCounty) {
                picker.setColumnWeight(1 / 3.0f, 2 / 3.0f);//将屏幕分为3份，省级和地级的比例为1:2
            } else {
                picker.setColumnWeight(2 / 8.0f, 3 / 8.0f, 3 / 8.0f);//省级、地级和县级的比例为2:3:3
            }
            picker.setSelectedItem(selectedProvince, selectedCity, selectedCounty);
            picker.setOnMoreWheelListener(moreCallback);
            picker.setOnLinkageListener(callback);
            picker.show();
        } else {
            callback.onAddressInitFailed();
        }
    }

    public interface MoreCallback extends OnMoreWheelListener {

        void onAddressInitFailed();

    }

    public interface Callback extends OnLinkageListener {

        void onAddressInitFailed();

    }

}
