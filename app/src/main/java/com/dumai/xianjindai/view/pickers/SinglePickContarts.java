package com.dumai.xianjindai.view.pickers;

import com.dumai.xianjindai.view.pickers.common.LineConfig;
import com.dumai.xianjindai.view.pickers.listeners.OnItemPickListener;
import com.dumai.xianjindai.view.pickers.listeners.OnSingleWheelListener;
import com.dumai.xianjindai.view.pickers.picker.SinglePicker;

/**
 * 名称：CashLoan
 * 描述：紧急联系人单选
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/27 13:55
 */
public class SinglePickContarts {

    private static SinglePickContarts singlePickContarts;
    private LineConfig config;

    public static SinglePickContarts getIntance() {
        if (singlePickContarts == null) {
            singlePickContarts = new SinglePickContarts();
        }
        return singlePickContarts;
    }

    public void Singlepicker(SinglePicker<String> picker, final SingleItem singleItem, String selectedItem, int selectedIndex) {
        picker.setCanLoop(true);//禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText("请选择");
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelText("取消");
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitText("确定");
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0xFFEE0000);
        picker.setUnSelectedTextColor(0xFF999999);
        config = new LineConfig();
        config.setColor(0xFFEE0000);//线颜色
        config.setAlpha(140);//线透明度
        config.setRatio((float) (1.0 / 8.0));//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
        picker.setSelectedItem(selectedItem);
        picker.setSelectedIndex(selectedIndex);
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                singleItem.onItemPicked(index, item);
            }
        });
        picker.setOnSingleWheelListener(new OnSingleWheelListener() {
            @Override
            public void onWheeled(int index, String item) {
                singleItem.onItemPicked(index, item);
            }
        });
        picker.show();
    }

}
