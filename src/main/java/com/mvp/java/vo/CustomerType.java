package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_GetCustomerTypes", procedureName = "usp_GetCustomerTypes", resultClasses = CustomerType.class
        )
})

// CUSTOMERTYPE_ID, CUSTOMERTYPE_DESCRIPTION
@Entity
public class CustomerType {

@Id
@Column(name = "CUSTOMERTYPE_ID")
    private Integer id;

    @Column (name = "CUSTOMERTYPE_DESCRIPTION")
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
