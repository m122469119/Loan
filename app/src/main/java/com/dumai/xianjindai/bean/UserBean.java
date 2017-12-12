package com.dumai.xianjindai.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * 名称：CashLoan
 * 描述：com.dumai.xianjindai.dao
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/29 14:27
 */
public class UserBean extends DataSupport {

    // 01登录表:UserBean
    // USERNAME:用户名
    // PSD:密码

    //unique 唯一标识,nullab 不可空 @Column(unique = true, nullable = false)
    private int id;
    @Column(unique = true, nullable = false)
    private String username;
    private String psd;

    public int getId() {
        return id;
    }

    public UserBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserBean setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPsd() {
        return psd;
    }

    public UserBean setPsd(String psd) {
        this.psd = psd;
        return this;
    }
}
