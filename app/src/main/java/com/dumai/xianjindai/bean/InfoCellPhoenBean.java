package com.dumai.xianjindai.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * 名称：CashLoan
 * 描述：com.dumai.xianjindai.bean
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/30 19:50
 */
public class InfoCellPhoenBean extends DataSupport {

    // 06 手机号认证表:InfoCreditCardBean
    // PhoneNumber:手机号码
    // ServerPsd: 服务密码

    private int id;
    @Column(unique = true, nullable = false)
    private String PhoneNumber;
    private String ServerPsd;

    public int getId() {
        return id;
    }

    public InfoCellPhoenBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public InfoCellPhoenBean setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
        return this;
    }

    public String getServerPsd() {
        return ServerPsd;
    }

    public InfoCellPhoenBean setServerPsd(String serverPsd) {
        ServerPsd = serverPsd;
        return this;
    }
}
