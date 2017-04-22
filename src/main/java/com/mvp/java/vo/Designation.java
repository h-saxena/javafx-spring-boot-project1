package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_GetDesignations", procedureName = "usp_GetDesignations", resultClasses = Designation.class
        )
})

// DESIGNATION_ID, DESIGNATION_NAME
@Entity
public class Designation {

@Id
@Column(name = "DESIGNATION_ID")
    private Integer id;

    @Column (name = "DESIGNATION_NAME")
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
