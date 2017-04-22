package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_GetReportingManagerList", procedureName = "usp_GetReportingManagerList", resultClasses = ReportingManager.class
        )
})

// SALESHIERARCHY_ID, SALESHIEARARCHY_NAME
@Entity
public class ReportingManager {

@Id
@Column(name = "SALESHIERARCHY_ID")
    private Integer id;

    @Column (name = "SALESHIEARARCHY_NAME")
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
