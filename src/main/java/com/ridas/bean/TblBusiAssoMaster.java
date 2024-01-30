/*
 * Created on 29 Feb 2016 ( Time 17:45:55 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class TblBusiAssoMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    @Size( min = 1, max = 50 )
    private String buAsId;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Size( max = 50 )
    private String memberId;

    @Size( max = 50 )
    private String receiptPk;


    private Long countpk;

    @Size( max = 100 )
    private String prefixcolumn;


    private Date createDate;


    private Date modifiedDate;

    @Size( max = 100 )
    private String status;

    @Size( max = 100 )
    private String userId;

	private TblMembershipForm member;
	private TblReceiptMaster receipt;

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
    public void setMemberId( String memberId ) {
        this.memberId = memberId;
    }
    public String getMemberId() {
        return this.memberId;
    }

    public void setReceiptPk( String receiptPk ) {
        this.receiptPk = receiptPk;
    }
    public String getReceiptPk() {
        return this.receiptPk;
    }

    public void setCountpk( Long countpk ) {
        this.countpk = countpk;
    }
    public Long getCountpk() {
        return this.countpk;
    }

    public void setPrefixcolumn( String prefixcolumn ) {
        this.prefixcolumn = prefixcolumn;
    }
    public String getPrefixcolumn() {
        return this.prefixcolumn;
    }

    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setModifiedDate( Date modifiedDate ) {
        this.modifiedDate = modifiedDate;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setStatus( String status ) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }
    public String getUserId() {
        return this.userId;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(buAsId);
        sb.append("|");
        sb.append(memberId);
        sb.append("|");
        sb.append(receiptPk);
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

		public TblMembershipForm getMember() {
			return member;
		}

		public void setMember(TblMembershipForm member) {
			this.member = member;
		}

		public TblReceiptMaster getReceipt() {
			return receipt;
		}

		public void setReceipt(TblReceiptMaster receipt) {
			this.receipt = receipt;
		}

		

}
