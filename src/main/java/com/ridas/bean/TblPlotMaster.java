/*
 * Created on 26 Feb 2016 ( Time 19:06:44 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class TblPlotMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer plotPk;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Size( max = 100 )
    private String plotFatherhusbandName;

    @Size( max = 100 )
    private String plotMothersName;

    @Size( max = 100 )
    private String plotNameOfProject;

    @Size( max = 100 )
    private String plotTypeOfProject;

    @Size( max = 500 )
    private String plotArea;

    @Size( max = 500 )
    private String plotRequiredArea;

    @Size( max = 50 )
    private String plotIsfarmer;

    @Size( max = 50 )
    private String plotFarmerState;

    @Size( max = 50 )
    private String plotFarmerSyNo;

    @Size( max = 50 )
    private String plotFarmerDistrict;

    @Size( max = 50 )
    private String plotFarmerVillage;


    private Date plotDate;


    private Date plotBookingDate;

    @Size( max = 100 )
    private String plotNo;

    @Size( max = 50 )
    private String memberId;


    private Date plotDeclarationDate;

    @Size( max = 50 )
    private String plotDeclarationPlace;


    private Long countpk;

    @Size( max = 100 )
    private String prefixcolumn;


    private Date createDate;


    private Date modifiedDate;

    @Size( max = 100 )
    private String userId;

    @Size( max = 100 )
    private String status;
    
    private String memberName;
    private Integer plotReceiptId;

    private TblMembershipForm tblMembershipForm;
    
    private TblReceiptMaster tblReceiptMaster;
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setPlotPk( Integer plotPk ) {
        this.plotPk = plotPk ;
    }

    public Integer getPlotPk() {
        return this.plotPk;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setPlotFatherhusbandName( String plotFatherhusbandName ) {
        this.plotFatherhusbandName = plotFatherhusbandName;
    }
    public String getPlotFatherhusbandName() {
        return this.plotFatherhusbandName;
    }

    public void setPlotMothersName( String plotMothersName ) {
        this.plotMothersName = plotMothersName;
    }
    public String getPlotMothersName() {
        return this.plotMothersName;
    }

    public void setPlotNameOfProject( String plotNameOfProject ) {
        this.plotNameOfProject = plotNameOfProject;
    }
    public String getPlotNameOfProject() {
        return this.plotNameOfProject;
    }

    public void setPlotTypeOfProject( String plotTypeOfProject ) {
        this.plotTypeOfProject = plotTypeOfProject;
    }
    public String getPlotTypeOfProject() {
        return this.plotTypeOfProject;
    }

    public void setPlotArea( String plotArea ) {
        this.plotArea = plotArea;
    }
    public String getPlotArea() {
        return this.plotArea;
    }

    public void setPlotRequiredArea( String plotRequiredArea ) {
        this.plotRequiredArea = plotRequiredArea;
    }
    public String getPlotRequiredArea() {
        return this.plotRequiredArea;
    }

    public void setPlotIsfarmer( String plotIsfarmer ) {
        this.plotIsfarmer = plotIsfarmer;
    }
    public String getPlotIsfarmer() {
        return this.plotIsfarmer;
    }

    public void setPlotFarmerState( String plotFarmerState ) {
        this.plotFarmerState = plotFarmerState;
    }
    public String getPlotFarmerState() {
        return this.plotFarmerState;
    }

    public void setPlotFarmerSyNo( String plotFarmerSyNo ) {
        this.plotFarmerSyNo = plotFarmerSyNo;
    }
    public String getPlotFarmerSyNo() {
        return this.plotFarmerSyNo;
    }

    public void setPlotFarmerDistrict( String plotFarmerDistrict ) {
        this.plotFarmerDistrict = plotFarmerDistrict;
    }
    public String getPlotFarmerDistrict() {
        return this.plotFarmerDistrict;
    }

    public void setPlotFarmerVillage( String plotFarmerVillage ) {
        this.plotFarmerVillage = plotFarmerVillage;
    }
    public String getPlotFarmerVillage() {
        return this.plotFarmerVillage;
    }

    public void setPlotDate( Date plotDate ) {
        this.plotDate = plotDate;
    }
    public Date getPlotDate() {
        return this.plotDate;
    }

    public void setPlotBookingDate( Date plotBookingDate ) {
        this.plotBookingDate = plotBookingDate;
    }
    public Date getPlotBookingDate() {
        return this.plotBookingDate;
    }

    public void setPlotNo( String plotNo ) {
        this.plotNo = plotNo;
    }
    public String getPlotNo() {
        return this.plotNo;
    }

    public void setMemberId( String memberId ) {
        this.memberId = memberId;
    }
    public String getMemberId() {
        return this.memberId;
    }

    public void setPlotDeclarationDate( Date plotDeclarationDate ) {
        this.plotDeclarationDate = plotDeclarationDate;
    }
    public Date getPlotDeclarationDate() {
        return this.plotDeclarationDate;
    }

    public void setPlotDeclarationPlace( String plotDeclarationPlace ) {
        this.plotDeclarationPlace = plotDeclarationPlace;
    }
    public String getPlotDeclarationPlace() {
        return this.plotDeclarationPlace;
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

    public void setUserId( String userId ) {
        this.userId = userId;
    }
    public String getUserId() {
        return this.userId;
    }

    public void setStatus( String status ) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(plotPk);
        sb.append("|");
        sb.append(plotFatherhusbandName);
        sb.append("|");
        sb.append(plotMothersName);
        sb.append("|");
        sb.append(plotNameOfProject);
        sb.append("|");
        sb.append(plotTypeOfProject);
        sb.append("|");
        sb.append(plotArea);
        sb.append("|");
        sb.append(plotRequiredArea);
        sb.append("|");
        sb.append(plotIsfarmer);
        sb.append("|");
        sb.append(plotFarmerState);
        sb.append("|");
        sb.append(plotFarmerSyNo);
        sb.append("|");
        sb.append(plotFarmerDistrict);
        sb.append("|");
        sb.append(plotFarmerVillage);
        sb.append("|");
        sb.append(plotDate);
        sb.append("|");
        sb.append(plotBookingDate);
        sb.append("|");
        sb.append(plotNo);
        sb.append("|");
        sb.append(memberId);
        sb.append("|");
        sb.append(plotDeclarationDate);
        sb.append("|");
        sb.append(plotDeclarationPlace);
        sb.append("|");
        sb.append(countpk);
        sb.append("|");
        sb.append(prefixcolumn);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(modifiedDate);
        sb.append("|");
        sb.append(userId);
        sb.append("|");
        sb.append(status);
        return sb.toString(); 
    }

		public TblMembershipForm getTblMembershipForm() {
			return tblMembershipForm;
		}

		public void setTblMembershipForm(TblMembershipForm tblMembershipForm) {
			this.tblMembershipForm = tblMembershipForm;
		}

		public TblReceiptMaster getTblReceiptMaster() {
			return tblReceiptMaster;
		}

		public void setTblReceiptMaster(TblReceiptMaster tblReceiptMaster) {
			this.tblReceiptMaster = tblReceiptMaster;
		}

		public String getMemberName() {
			return memberName;
		}

		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}

		public Integer getPlotReceiptId() {
			return plotReceiptId;
		}

		public void setPlotReceiptId(Integer plotReceiptId) {
			this.plotReceiptId = plotReceiptId;
		} 


}