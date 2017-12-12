package com.dumai.xianjindai.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * 名称：InfoJobBean.java
 * 描述：com.dumai.xianjindai.bean
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/30 12:13
 */
public class InfoJobBean extends DataSupport {

    // 03工作信息表:InfoJobBean
    // careertype:职业类型
    // unitname:单位名称
    // Province 省
    // City 市
    // County 区
    // DetailedArs 详细地址
    // contactnumber 联系电话
    // place 职位

    //unique 唯一标识,nullab 不可空 @Column(unique = true, nullable = false)
    private int id;
    private String careertype1;
    private String careertype2;
    private String careertype3;
    @Column(unique = true, nullable = false)
    private String unitname;
    private String Province;
    private String City;
    private String County;
    private String DetailedArs;
    private String contactnumber;
    private String place;
    private String monthlyincome;

    public int getId() {
        return id;
    }

    public InfoJobBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getCareertype1() {
        return careertype1;
    }

    public InfoJobBean setCareertype1(String careertype1) {
        this.careertype1 = careertype1;
        return this;
    }

    public String getCareertype2() {
        return careertype2;
    }

    public InfoJobBean setCareertype2(String careertype2) {
        this.careertype2 = careertype2;
        return this;
    }

    public String getCareertype3() {
        return careertype3;
    }

    public InfoJobBean setCareertype3(String careertype3) {
        this.careertype3 = careertype3;
        return this;
    }

    public String getUnitname() {
        return unitname;
    }

    public InfoJobBean setUnitname(String unitname) {
        this.unitname = unitname;
        return this;
    }

    public String getProvince() {
        return Province;
    }

    public InfoJobBean setProvince(String province) {
        Province = province;
        return this;
    }

    public String getCity() {
        return City;
    }

    public InfoJobBean setCity(String city) {
        City = city;
        return this;
    }

    public String getCounty() {
        return County;
    }

    public InfoJobBean setCounty(String county) {
        County = county;
        return this;
    }

    public String getDetailedArs() {
        return DetailedArs;
    }

    public InfoJobBean setDetailedArs(String detailedArs) {
        DetailedArs = detailedArs;
        return this;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public InfoJobBean setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public InfoJobBean setPlace(String place) {
        this.place = place;
        return this;
    }

    public String getMonthlyincome() {
        return monthlyincome;
    }

    public InfoJobBean setMonthlyincome(String monthlyincome) {
        this.monthlyincome = monthlyincome;
        return this;
    }
}
