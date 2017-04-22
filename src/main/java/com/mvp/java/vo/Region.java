package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_GetRegions", procedureName = "usp_GetRegions", resultClasses = Region.class
        )
})

// REGION_ID, REGION_NAME
@Entity
public class Region {

@Id
@Column(name = "REGION_ID")
    private Integer id;

    @Column (name = "REGION_NAME")
    private String name;



    @Override
    public String toString() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
