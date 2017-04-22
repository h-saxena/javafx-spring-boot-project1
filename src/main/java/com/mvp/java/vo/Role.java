package com.mvp.java.vo;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_GetRoles", procedureName = "usp_GetRoles", resultClasses = Role.class
        )
})

// ROLE_ID, ROLE_DESCRIPTION
@Entity
public class Role {

@Id
@Column(name = "ROLE_ID")
    private Integer id;

    @Column (name = "ROLE_DESCRIPTION")
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
