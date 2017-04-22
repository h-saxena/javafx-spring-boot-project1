package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_GetProducts", procedureName = "usp_GetProducts", resultClasses = Product.class
        )
})

// PRODUCT_ID, PRODUCT_DESCRIPTION
@Entity
public class Product {

@Id
@Column(name = "PRODUCT_ID")
    private Integer id;

    @Column (name = "PRODUCT_DESCRIPTION")
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
