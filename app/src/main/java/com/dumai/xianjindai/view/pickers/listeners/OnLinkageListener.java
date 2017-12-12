package com.dumai.xianjindai.view.pickers.listeners;

import com.dumai.xianjindai.view.pickers.entity.City;
import com.dumai.xianjindai.view.pickers.entity.County;
import com.dumai.xianjindai.view.pickers.entity.Province;

/**
 * @author haoruigang
 *         2017-11-24 11:31:33
 */

public interface OnLinkageListener {
    /**
     * 选择地址
     *
     * @param province the province
     * @param city     the city
     * @param county   the county ，if {@code hideCounty} is true，this is null
     */
    void onAddressPicked(Province province, City city, County county);
}
