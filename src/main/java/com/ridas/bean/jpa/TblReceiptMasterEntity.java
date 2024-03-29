/*
 * Created on 29 Feb 2016 ( Time 17:46:07 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.ridas.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "tbl_receipt_master"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="tbl_receipt_master", catalog="ridasindiaproperties" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="TblReceiptMasterEntity.countAll", query="SELECT COUNT(x) FROM TblReceiptMasterEntity x" )
} )
public class TblReceiptMasterEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="receipt_pk", nullable=false, length=50)
    private String     receiptPk    ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="receipt_amount")
    private Double     receiptAmount ;

    @Column(name="receipt_modeofpayment", length=100)
    private String     receiptModeofpayment ;

    @Column(name="receipt_amountinwords", length=500)
    private String     receiptAmountinwords ;

    @Column(name="receipt_otherdetails", length=255)
    private String     receiptOtherdetails ;

    @Temporal(TemporalType.DATE)
    @Column(name="receipt_currentdate")
    private Date       receiptCurrentdate ;

    @Column(name="receipt_type", length=500)
    private String     receiptType  ;

    @Column(name="receipt_bank", length=50)
    private String     receiptBank  ;

    @Temporal(TemporalType.DATE)
    @Column(name="receipt_chequedate")
    private Date       receiptChequedate ;

    @Column(name="receipt_drawnOn", length=50)
    private String     receiptDrawnon ;

    @Column(name="receipt_branch", length=500)
    private String     receiptBranch ;

    @Column(name="receipt_city", length=100)
    private String     receiptCity  ;

    @Column(name="receipt_atProject", length=100)
    private String     receiptAtproject ;

    @Column(name="receipt_place", length=50)
    private String     receiptPlace ;

    @Column(name="receipt_profit_from")
    private Double     receiptProfitFrom ;

    @Column(name="receipt_profit_to")
    private Double     receiptProfitTo ;

    @Column(name="receipt_security_cheque_no", length=100)
    private String     receiptSecurityChequeNo ;

    @Temporal(TemporalType.DATE)
    @Column(name="receipt_security_cheque_date")
    private Date       receiptSecurityChequeDate ;

    @Column(name="receipt_security_cheque_drawnOn", length=1000)
    private String     receiptSecurityChequeDrawnon ;

    @Column(name="receipt_security_cheque_address", length=1000)
    private String     receiptSecurityChequeAddress ;

    @Temporal(TemporalType.DATE)
    @Column(name="receipt_NEFT_date_applicable")
    private Date       receiptNeftDateApplicable ;

    @Column(name="receipt_NEFT_day", length=100)
    private String     receiptNeftDay ;
    
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

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
//done as on 13 feb 2016 added below 3 lines to set "fetchtype" to "Eager".
    @OneToMany(mappedBy="tblReceiptMaster", targetEntity=TblBusiAssoMasterEntity.class)
    private List<TblBusiAssoMasterEntity> listOfTblBusiAssoMaster;
//done as on 13 feb 2016 added below 3 lines to set "fetchtype" to "Eager".
    @ManyToOne
    @JoinColumn(name="member_id", referencedColumnName="member_id")
    private TblMembershipFormEntity tblMembershipForm;
//done as on 13 feb 2016 added below 3 lines to set "fetchtype" to "Eager".
    @OneToMany(mappedBy="tblReceiptMaster", targetEntity=TblPlotRecMasterEntity.class)
    private List<TblPlotRecMasterEntity> listOfTblPlotRecMaster;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public TblReceiptMasterEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setReceiptPk( String receiptPk ) {
        this.receiptPk = receiptPk ;
    }
    public String getReceiptPk() {
        return this.receiptPk;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : receipt_amount ( DOUBLE ) 
    public void setReceiptAmount( Double receiptAmount ) {
        this.receiptAmount = receiptAmount;
    }
    public Double getReceiptAmount() {
        return this.receiptAmount;
    }

    //--- DATABASE MAPPING : receipt_modeofpayment ( VARCHAR ) 
    public void setReceiptModeofpayment( String receiptModeofpayment ) {
        this.receiptModeofpayment = receiptModeofpayment;
    }
    public String getReceiptModeofpayment() {
        return this.receiptModeofpayment;
    }

    //--- DATABASE MAPPING : receipt_amountinwords ( VARCHAR ) 
    public void setReceiptAmountinwords( String receiptAmountinwords ) {
        this.receiptAmountinwords = receiptAmountinwords;
    }
    public String getReceiptAmountinwords() {
        return this.receiptAmountinwords;
    }

    //--- DATABASE MAPPING : receipt_otherdetails ( VARCHAR ) 
    public void setReceiptOtherdetails( String receiptOtherdetails ) {
        this.receiptOtherdetails = receiptOtherdetails;
    }
    public String getReceiptOtherdetails() {
        return this.receiptOtherdetails;
    }

    //--- DATABASE MAPPING : receipt_currentdate ( DATE ) 
    public void setReceiptCurrentdate( Date receiptCurrentdate ) {
        this.receiptCurrentdate = receiptCurrentdate;
    }
    public Date getReceiptCurrentdate() {
        return this.receiptCurrentdate;
    }

    //--- DATABASE MAPPING : receipt_type ( VARCHAR ) 
    public void setReceiptType( String receiptType ) {
        this.receiptType = receiptType;
    }
    public String getReceiptType() {
        return this.receiptType;
    }

    //--- DATABASE MAPPING : receipt_bank ( VARCHAR ) 
    public void setReceiptBank( String receiptBank ) {
        this.receiptBank = receiptBank;
    }
    public String getReceiptBank() {
        return this.receiptBank;
    }

    //--- DATABASE MAPPING : receipt_chequedate ( DATE ) 
    public void setReceiptChequedate( Date receiptChequedate ) {
        this.receiptChequedate = receiptChequedate;
    }
    public Date getReceiptChequedate() {
        return this.receiptChequedate;
    }

    //--- DATABASE MAPPING : receipt_drawnOn ( VARCHAR ) 
    public void setReceiptDrawnon( String receiptDrawnon ) {
        this.receiptDrawnon = receiptDrawnon;
    }
    public String getReceiptDrawnon() {
        return this.receiptDrawnon;
    }

    //--- DATABASE MAPPING : receipt_branch ( VARCHAR ) 
    public void setReceiptBranch( String receiptBranch ) {
        this.receiptBranch = receiptBranch;
    }
    public String getReceiptBranch() {
        return this.receiptBranch;
    }

    //--- DATABASE MAPPING : receipt_city ( VARCHAR ) 
    public void setReceiptCity( String receiptCity ) {
        this.receiptCity = receiptCity;
    }
    public String getReceiptCity() {
        return this.receiptCity;
    }

    //--- DATABASE MAPPING : receipt_atProject ( VARCHAR ) 
    public void setReceiptAtproject( String receiptAtproject ) {
        this.receiptAtproject = receiptAtproject;
    }
    public String getReceiptAtproject() {
        return this.receiptAtproject;
    }

    //--- DATABASE MAPPING : receipt_place ( VARCHAR ) 
    public void setReceiptPlace( String receiptPlace ) {
        this.receiptPlace = receiptPlace;
    }
    public String getReceiptPlace() {
        return this.receiptPlace;
    }

    //--- DATABASE MAPPING : receipt_profit_from ( DOUBLE ) 
    public void setReceiptProfitFrom( Double receiptProfitFrom ) {
        this.receiptProfitFrom = receiptProfitFrom;
    }
    public Double getReceiptProfitFrom() {
        return this.receiptProfitFrom;
    }

    //--- DATABASE MAPPING : receipt_profit_to ( DOUBLE ) 
    public void setReceiptProfitTo( Double receiptProfitTo ) {
        this.receiptProfitTo = receiptProfitTo;
    }
    public Double getReceiptProfitTo() {
        return this.receiptProfitTo;
    }

    //--- DATABASE MAPPING : receipt_security_cheque_no ( VARCHAR ) 
    public void setReceiptSecurityChequeNo( String receiptSecurityChequeNo ) {
        this.receiptSecurityChequeNo = receiptSecurityChequeNo;
    }
    public String getReceiptSecurityChequeNo() {
        return this.receiptSecurityChequeNo;
    }

    //--- DATABASE MAPPING : receipt_security_cheque_date ( DATE ) 
    public void setReceiptSecurityChequeDate( Date receiptSecurityChequeDate ) {
        this.receiptSecurityChequeDate = receiptSecurityChequeDate;
    }
    public Date getReceiptSecurityChequeDate() {
        return this.receiptSecurityChequeDate;
    }

    //--- DATABASE MAPPING : receipt_security_cheque_drawnOn ( VARCHAR ) 
    public void setReceiptSecurityChequeDrawnon( String receiptSecurityChequeDrawnon ) {
        this.receiptSecurityChequeDrawnon = receiptSecurityChequeDrawnon;
    }
    public String getReceiptSecurityChequeDrawnon() {
        return this.receiptSecurityChequeDrawnon;
    }

    //--- DATABASE MAPPING : receipt_security_cheque_address ( VARCHAR ) 
    public void setReceiptSecurityChequeAddress( String receiptSecurityChequeAddress ) {
        this.receiptSecurityChequeAddress = receiptSecurityChequeAddress;
    }
    public String getReceiptSecurityChequeAddress() {
        return this.receiptSecurityChequeAddress;
    }
    //--- DATABASE MAPPING : receipt_NEFT_date_applicable ( DATE ) 
    public void setReceiptNeftDateApplicable( Date receiptNeftDateApplicable ) {
        this.receiptNeftDateApplicable = receiptNeftDateApplicable;
    }
    public Date getReceiptNeftDateApplicable() {
        return this.receiptNeftDateApplicable;
    }

    //--- DATABASE MAPPING : receipt_NEFT_day ( VARCHAR ) 
    public void setReceiptNeftDay( String receiptNeftDay ) {
        this.receiptNeftDay = receiptNeftDay;
    }
    public String getReceiptNeftDay() {
        return this.receiptNeftDay;
    }
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
    public void setListOfTblBusiAssoMaster( List<TblBusiAssoMasterEntity> listOfTblBusiAssoMaster ) {
        this.listOfTblBusiAssoMaster = listOfTblBusiAssoMaster;
    }
    public List<TblBusiAssoMasterEntity> getListOfTblBusiAssoMaster() {
        return this.listOfTblBusiAssoMaster;
    }

    public void setTblMembershipForm( TblMembershipFormEntity tblMembershipForm ) {
        this.tblMembershipForm = tblMembershipForm;
    }
    public TblMembershipFormEntity getTblMembershipForm() {
        return this.tblMembershipForm;
    }

    public void setListOfTblPlotRecMaster( List<TblPlotRecMasterEntity> listOfTblPlotRecMaster ) {
        this.listOfTblPlotRecMaster = listOfTblPlotRecMaster;
    }
    public List<TblPlotRecMasterEntity> getListOfTblPlotRecMaster() {
        return this.listOfTblPlotRecMaster;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(receiptPk);
        sb.append("]:"); 
        sb.append(receiptAmount);
        sb.append("|");
        sb.append(receiptModeofpayment);
        sb.append("|");
        sb.append(receiptAmountinwords);
        sb.append("|");
        sb.append(receiptOtherdetails);
        sb.append("|");
        sb.append(receiptCurrentdate);
        sb.append("|");
        sb.append(receiptType);
        sb.append("|");
        sb.append(receiptBank);
        sb.append("|");
        sb.append(receiptChequedate);
        sb.append("|");
        sb.append(receiptDrawnon);
        sb.append("|");
        sb.append(receiptBranch);
        sb.append("|");
        sb.append(receiptCity);
        sb.append("|");
        sb.append(receiptAtproject);
        sb.append("|");
        sb.append(receiptPlace);
        sb.append("|");
        sb.append(receiptProfitFrom);
        sb.append("|");
        sb.append(receiptProfitTo);
        sb.append("|");
        sb.append(receiptSecurityChequeNo);
        sb.append("|");
        sb.append(receiptSecurityChequeDate);
        sb.append("|");
        sb.append(receiptSecurityChequeDrawnon);
        sb.append("|");
        sb.append(receiptSecurityChequeAddress);
        sb.append("|");
        sb.append(receiptNeftDateApplicable);
        sb.append("|");
        sb.append(receiptNeftDay);
        sb.append("|");
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
