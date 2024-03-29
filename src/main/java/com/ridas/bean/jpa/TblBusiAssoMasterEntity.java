/*
 * Created on 29 Feb 2016 ( Time 17:46:06 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.ridas.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "tbl_busi_asso_master"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="tbl_busi_asso_master", catalog="ridasindiaproperties" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="TblBusiAssoMasterEntity.countAll", query="SELECT COUNT(x) FROM TblBusiAssoMasterEntity x" )
} )
public class TblBusiAssoMasterEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="bu_as_id", nullable=false, length=50)
    private String     buAsId       ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="countpk")
    private Long       countpk      ;

    @Column(name="prefixcolumn", length=100)
    private String     prefixcolumn ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date")
    private Date       modifiedDate ;

    @Column(name="status", length=100)
    private String     status       ;

    @Column(name="user_id", length=100)
    private String     userId       ;

	// "memberId" (column "member_id") is not defined by itself because used as FK in a link 
	// "receiptPk" (column "receipt_pk") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
//done as on 13 feb 2016 added below 3 lines to set "fetchtype" to "Eager".
    @ManyToOne
    @JoinColumn(name="member_id", referencedColumnName="member_id")
    private TblMembershipFormEntity tblMembershipForm;
//done as on 13 feb 2016 added below 3 lines to set "fetchtype" to "Eager".
    @ManyToOne
    @JoinColumn(name="receipt_pk", referencedColumnName="receipt_pk")
    private TblReceiptMasterEntity tblReceiptMaster;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public TblBusiAssoMasterEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setBuAsId( String buAsId ) {
        this.buAsId = buAsId ;
    }
    public String getBuAsId() {
        return this.buAsId;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : countpk ( BIGINT ) 
    public void setCountpk( Long countpk ) {
        this.countpk = countpk;
    }
    public Long getCountpk() {
        return this.countpk;
    }

    //--- DATABASE MAPPING : prefixcolumn ( VARCHAR ) 
    public void setPrefixcolumn( String prefixcolumn ) {
        this.prefixcolumn = prefixcolumn;
    }
    public String getPrefixcolumn() {
        return this.prefixcolumn;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    //--- DATABASE MAPPING : modified_date ( DATETIME ) 
    public void setModifiedDate( Date modifiedDate ) {
        this.modifiedDate = modifiedDate;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    //--- DATABASE MAPPING : status ( VARCHAR ) 
    public void setStatus( String status ) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }

    //--- DATABASE MAPPING : user_id ( VARCHAR ) 
    public void setUserId( String userId ) {
        this.userId = userId;
    }
    public String getUserId() {
        return this.userId;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setTblMembershipForm( TblMembershipFormEntity tblMembershipForm ) {
        this.tblMembershipForm = tblMembershipForm;
    }
    public TblMembershipFormEntity getTblMembershipForm() {
        return this.tblMembershipForm;
    }

    public void setTblReceiptMaster( TblReceiptMasterEntity tblReceiptMaster ) {
        this.tblReceiptMaster = tblReceiptMaster;
    }
    public TblReceiptMasterEntity getTblReceiptMaster() {
        return this.tblReceiptMaster;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(buAsId);
        sb.append("]:"); 
        sb.append(countpk);
        sb.append("|");
        sb.append(prefixcolumn);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(modifiedDate);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(userId);
        return sb.toString(); 
    } 

}
