package com.mvp.java.vo;

import javax.persistence.*;
import java.util.Date;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "usp_Search_ComplensationPlan", procedureName = "usp_Search_ComplensationPlan"
                , resultClasses = CompensationPlan.class,
                parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "PlanName", type = String.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "StartPageNumber", type = Integer.class)
                ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "EndPageNumber", type = Integer.class)
        }),
        //CompensationPlanName Varchar(50), CompensationPlanType Char(1), ProductID BigInt, ProductTypeID Int,
        // PublishPlan Bit, UserID Varchar(50), CompensationDataXml Text, INOUT CompensationPlanID BigInt)

        @NamedStoredProcedureQuery(name = "usp_CompensationPlan_IU", procedureName = "usp_CompensationPlan_IU"
                ,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "CompensationPlanName", type = String.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "CompensationPlanType", type = String.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "ProductID", type = Integer.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "ProductTypeID", type = Integer.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "PublishPlan", type = Boolean.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "UserID", type = String.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.IN, name = "CompensationDataXml", type = String.class)
                        ,@StoredProcedureParameter(mode = ParameterMode.INOUT, name = "CompensationPlanID", type = Integer.class)
                })

})
@Entity

// COMPENSATIONPLAN_ID, COMPENSATION_PLAN_NAME, COMPENSATIONPLAN_TYPE, COMPENSATIONPLAN_TYPE_DESC
// , PRODUCT_ID, PRODUCT_DESCRIPTION, PRODUCTTYPE_ID, PRODUCTTYPE_DESCRIPTION
// , COMPENSATIONPLAN_ISPUBLISHED
public class CompensationPlan {
    @Id
    @Column(name = "COMPENSATIONPLAN_ID")
    Integer planId;
    @Column(name = "COMPENSATION_PLAN_NAME")
    String name;
    @Column(name = "COMPENSATIONPLAN_TYPE")
    String type;
    @Column(name = "COMPENSATIONPLAN_TYPE_DESC")
    String typeDesc;
    @Column(name = "PRODUCT_ID")
    Integer prodId;
    @Column(name = "PRODUCT_DESCRIPTION")
    String prodDesc;
    @Column(name = "PRODUCTTYPE_ID")
    Integer prodTypeId;
    @Column(name = "PRODUCTTYPE_DESCRIPTION")
    String prodTypeDesc;
    @Column(name = "COMPENSATIONPLAN_ISPUBLISHED")
    Boolean published;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public Integer getProdTypeId() {
        return prodTypeId;
    }

    public void setProdTypeId(Integer prodTypeId) {
        this.prodTypeId = prodTypeId;
    }

    public String getProdTypeDesc() {
        return prodTypeDesc;
    }

    public void setProdTypeDesc(String prodTypeDesc) {
        this.prodTypeDesc = prodTypeDesc;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
       return   "Name: " + name
       + " | "+ "Type: " + typeDesc
               + " | "+ "Product: " + prodDesc
               + " | "+ "Pub: " + published
       ;
    }
}
