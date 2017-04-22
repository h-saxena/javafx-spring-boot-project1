package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_GetPaymentFrequencies", procedureName = "usp_GetPaymentFrequencies", resultClasses = PayoutFrequency.class
        )
})

// PAYMENTFREQUENCY_ID, PAYMENTFREQUENCY_DESCR
@Entity
public class PayoutFrequency {

@Id
@Column(name = "PAYMENTFREQUENCY_ID")
    private Integer id;

    @Column (name = "PAYMENTFREQUENCY_DESCR")
    private String desc;



    @Override
    public String toString() {
        return desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
