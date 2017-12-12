package com.dumai.xianjindai.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * 名称：CashLoan
 * 描述：com.dumai.xianjindai.bean
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017 2017/11/30 14:50
 */
public class InfoContactBean extends DataSupport {

    // 04联系信息表:InfoContactBean
    // linealRelation:直系关系
    // linealName:直系名称
    // linealPhone 直系电话
    // importantRelation:重要关系
    // importantName:重要名称
    // importantPhone 重要电话

    private int id;

    private String linealRelation;
    @Column(unique = true, nullable = false)
    private String linealName;
    private String linealPhone;

    private String importantRelation;
    private String importantName;
    private String importantPhone;

    public int getId() {
        return id;
    }

    public InfoContactBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getLinealRelation() {
        return linealRelation;
    }

    public InfoContactBean setLinealRelation(String linealRelation) {
        this.linealRelation = linealRelation;
        return this;
    }

    public String getLinealName() {
        return linealName;
    }

    public InfoContactBean setLinealName(String linealName) {
        this.linealName = linealName;
        return this;
    }

    public String getLinealPhone() {
        return linealPhone;
    }

    public InfoContactBean setLinealPhone(String linealPhone) {
        this.linealPhone = linealPhone;
        return this;
    }

    public String getImportantRelation() {
        return importantRelation;
    }

    public InfoContactBean setImportantRelation(String importantRelation) {
        this.importantRelation = importantRelation;
        return this;
    }

    public String getImportantName() {
        return importantName;
    }

    public InfoContactBean setImportantName(String importantName) {
        this.importantName = importantName;
        return this;
    }

    public String getImportantPhone() {
        return importantPhone;
    }

    public InfoContactBean setImportantPhone(String importantPhone) {
        this.importantPhone = importantPhone;
        return this;
    }
}
