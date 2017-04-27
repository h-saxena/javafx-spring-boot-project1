package com.mvp.java.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ManagerReportDetailId implements Serializable {
    @Column(name = "ORDER_NUMBER")
    String orderNum;

    @Column(name = "PRODUCT_DESCRIPTION")
    String prodDesc;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }
}
