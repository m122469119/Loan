package com.dumai.xianjindai.bean;

import org.litepal.crud.DataSupport;

/**
 * 名称：CashLoan
 * 描述：com.dumai.xianjindai.bean
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/30 16:02
 */
public class InfoCreditCardBean extends DataSupport {

    // 05 信用卡信息表:InfoCreditCardBean
    // creditCardNum:信用卡号
    // issuingBank:发卡银行
    // creditLimit 信用额度
    // termMonth:月
    // termYear:年

    private String creditCardNum;
    private String issuingBank;
    private String creditLimit;
    private String termMonth;
    private String termYear;

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public InfoCreditCardBean setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
        return this;
    }

    public String getIssuingBank() {
        return issuingBank;
    }

    public InfoCreditCardBean setIssuingBank(String issuingBank) {
        this.issuingBank = issuingBank;
        return this;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public InfoCreditCardBean setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
        return this;
    }

    public String getTermMonth() {
        return termMonth;
    }

    public InfoCreditCardBean setTermMonth(String termMonth) {
        this.termMonth = termMonth;
        return this;
    }

    public String getTermYear() {
        return termYear;
    }

    public InfoCreditCardBean setTermYear(String termYear) {
        this.termYear = termYear;
        return this;
    }
}
