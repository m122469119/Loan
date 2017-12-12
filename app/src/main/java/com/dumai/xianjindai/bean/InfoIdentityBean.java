package com.dumai.xianjindai.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * 名称：CashLoan
 * 描述：com.dumai.xianjindai.bean
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/29 18:57
 */
public class InfoIdentityBean extends DataSupport {

    // 02身份信息表:InfoIdentityBean
    // IdentityName:身份证名称
    // IdentityCode:身份证号码
    // Province 省
    // City 市
    // County 区
    // DetailedArs 详细地址
    // Education 最高学历
    // MaritalStatus 婚姻状况

    private int id;
    @Column(unique = true, nullable = false)
    private String IdentityCode;
    private String IdentityName;
    private String Province;
    private String City;
    private String County;
    private String DetailedArs;
    private String Education;
    private String MaritalStatus;

    public int getId() {
        return id;
    }

    public InfoIdentityBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getIdentityName() {
        return IdentityName;
    }

    public InfoIdentityBean setIdentityName(String identityName) {
        IdentityName = identityName;
        return this;
    }

    public String getIdentityCode() {
        return IdentityCode;
    }

    public InfoIdentityBean setIdentityCode(String identityCode) {
        IdentityCode = identityCode;
        return this;
    }

    public String getProvince() {
        return Province;
    }

    public InfoIdentityBean setProvince(String province) {
        Province = province;
        return this;
    }

    public String getCity() {
        return City;
    }

    public InfoIdentityBean setCity(String city) {
        City = city;
        return this;
    }

    public String getCounty() {
        return County;
    }

    public InfoIdentityBean setCounty(String county) {
        County = county;
        return this;
    }

    public String getDetailedArs() {
        return DetailedArs;
    }

    public InfoIdentityBean setDetailedArs(String detailedArs) {
        DetailedArs = detailedArs;
        return this;
    }

    public String getEducation() {
        return Education;
    }

    public InfoIdentityBean setEducation(String education) {
        Education = education;
        return this;
    }

    public String getMaritalStatus() {
        return MaritalStatus;
    }

    public InfoIdentityBean setMaritalStatus(String maritalStatus) {
        MaritalStatus = maritalStatus;
        return this;
    }

}
