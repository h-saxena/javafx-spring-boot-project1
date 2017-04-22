package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_GetProductTypes", procedureName = "usp_GetProductTypes", resultClasses = ProductType.class
        )
})

// PRODUCTTYPE_ID, PRODUCTTYPE_DESCRIPTION
@Entity
public class ProductType {

@Id
@Column(name = "PRODUCTTYPE_ID")
    private Integer id;

    @Column (name = "PRODUCTTYPE_DESCRIPTION")
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
