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
import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "tbl_plot_master"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="tbl_plot_master", catalog="ridasindiaproperties" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="TblPlotMasterEntity.countAll", query="SELECT COUNT(x) FROM TblPlotMasterEntity x" )
} )
public class TblPlotMasterEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="plot_pk", nullable=false)
    private Integer    plotPk       ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="plot_fatherhusband_name", length=100)
    private String     plotFatherhusbandName ;

    @Column(name="plot_mothers_name", length=100)
    private String     plotMothersName ;

    @Column(name="plot_name_of_project", length=100)
    private String     plotNameOfProject ;

    @Column(name="plot_type_of_project", length=100)
    private String     plotTypeOfProject ;

    @Column(name="plot_area", length=500)
    private String     plotArea     ;

    @Column(name="plot_required_area", length=500)
    private String     plotRequiredArea ;

    @Column(name="plot_isFarmer", length=50)
    private String     plotIsfarmer ;

    @Column(name="plot_farmer_state", length=50)
    private String     plotFarmerState ;

    @Column(name="plot_farmer_sy_no", length=50)
    private String     plotFarmerSyNo ;

    @Column(name="plot_farmer_district", length=50)
    private String     plotFarmerDistrict ;

    @Column(name="plot_farmer_village", length=50)
    private String     plotFarmerVillage ;

    @Temporal(TemporalType.DATE)
    @Column(name="plot_date")
    private Date       plotDate     ;

    @Temporal(TemporalType.DATE)
    @Column(name="plot_booking_date")
    private Date       plotBookingDate ;

    @Column(name="plot_no", length=100)
    private String     plotNo       ;

    @Temporal(TemporalType.DATE)
    @Column(name="plot_declaration_date")
    private Date       plotDeclarationDate ;

    @Column(name="plot_declaration_place", length=50)
    private String     plotDeclarationPlace ;

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

    @Column(name="user_id", length=100)
    private String     userId       ;

    @Column(name="status", length=100)
    private String     status       ;

	// "memberId" (column "member_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
//done as on 13 feb 2016 added below 3 lines to set "fetchtype" to "Eager".
    @OneToMany(mappedBy="tblPlotMaster", targetEntity=TblPlotRecMasterEntity.class)
    private List<TblPlotRecMasterEntity> listOfTblPlotRecMaster;
//done as on 13 feb 2016 added below 3 lines to set "fetchtype" to "Eager".
    @ManyToOne
    @JoinColumn(name="member_id", referencedColumnName="member_id")
    private TblMembershipFormEntity tblMembershipForm;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public TblPlotMasterEntity() {
		super();
    }
    
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
    //--- DATABASE MAPPING : plot_fatherhusband_name ( VARCHAR ) 
    public void setPlotFatherhusbandName( String plotFatherhusbandName ) {
        this.plotFatherhusbandName = plotFatherhusbandName;
    }
    public String getPlotFatherhusbandName() {
        return this.plotFatherhusbandName;
    }

    //--- DATABASE MAPPING : plot_mothers_name ( VARCHAR ) 
    public void setPlotMothersName( String plotMothersName ) {
        this.plotMothersName = plotMothersName;
    }
    public String getPlotMothersName() {
        return this.plotMothersName;
    }

    //--- DATABASE MAPPING : plot_name_of_project ( VARCHAR ) 
    public void setPlotNameOfProject( String plotNameOfProject ) {
        this.plotNameOfProject = plotNameOfProject;
    }
    public String getPlotNameOfProject() {
        return this.plotNameOfProject;
    }

    //--- DATABASE MAPPING : plot_type_of_project ( VARCHAR ) 
    public void setPlotTypeOfProject( String plotTypeOfProject ) {
        this.plotTypeOfProject = plotTypeOfProject;
    }
    public String getPlotTypeOfProject() {
        return this.plotTypeOfProject;
    }

    //--- DATABASE MAPPING : plot_area ( VARCHAR ) 
    public void setPlotArea( String plotArea ) {
        this.plotArea = plotArea;
    }
    public String getPlotArea() {
        return this.plotArea;
    }

    //--- DATABASE MAPPING : plot_required_area ( VARCHAR ) 
    public void setPlotRequiredArea( String plotRequiredArea ) {
        this.plotRequiredArea = plotRequiredArea;
    }
    public String getPlotRequiredArea() {
        return this.plotRequiredArea;
    }

    //--- DATABASE MAPPING : plot_isFarmer ( VARCHAR ) 
    public void setPlotIsfarmer( String plotIsfarmer ) {
        this.plotIsfarmer = plotIsfarmer;
    }
    public String getPlotIsfarmer() {
        return this.plotIsfarmer;
    }

    //--- DATABASE MAPPING : plot_farmer_state ( VARCHAR ) 
    public void setPlotFarmerState( String plotFarmerState ) {
        this.plotFarmerState = plotFarmerState;
    }
    public String getPlotFarmerState() {
        return this.plotFarmerState;
    }

    //--- DATABASE MAPPING : plot_farmer_sy_no ( VARCHAR ) 
    public void setPlotFarmerSyNo( String plotFarmerSyNo ) {
        this.plotFarmerSyNo = plotFarmerSyNo;
    }
    public String getPlotFarmerSyNo() {
        return this.plotFarmerSyNo;
    }

    //--- DATABASE MAPPING : plot_farmer_district ( VARCHAR ) 
    public void setPlotFarmerDistrict( String plotFarmerDistrict ) {
        this.plotFarmerDistrict = plotFarmerDistrict;
    }
    public String getPlotFarmerDistrict() {
        return this.plotFarmerDistrict;
    }

    //--- DATABASE MAPPING : plot_farmer_village ( VARCHAR ) 
    public void setPlotFarmerVillage( String plotFarmerVillage ) {
        this.plotFarmerVillage = plotFarmerVillage;
    }
    public String getPlotFarmerVillage() {
        return this.plotFarmerVillage;
    }

    //--- DATABASE MAPPING : plot_date ( DATE ) 
    public void setPlotDate( Date plotDate ) {
        this.plotDate = plotDate;
    }
    public Date getPlotDate() {
        return this.plotDate;
    }

    //--- DATABASE MAPPING : plot_booking_date ( DATE ) 
    public void setPlotBookingDate( Date plotBookingDate ) {
        this.plotBookingDate = plotBookingDate;
    }
    public Date getPlotBookingDate() {
        return this.plotBookingDate;
    }

    //--- DATABASE MAPPING : plot_no ( VARCHAR ) 
    public void setPlotNo( String plotNo ) {
        this.plotNo = plotNo;
    }
    public String getPlotNo() {
        return this.plotNo;
    }

    //--- DATABASE MAPPING : plot_declaration_date ( DATE ) 
    public void setPlotDeclarationDate( Date plotDeclarationDate ) {
        this.plotDeclarationDate = plotDeclarationDate;
    }
    public Date getPlotDeclarationDate() {
        return this.plotDeclarationDate;
    }

    //--- DATABASE MAPPING : plot_declaration_place ( VARCHAR ) 
    public void setPlotDeclarationPlace( String plotDeclarationPlace ) {
        this.plotDeclarationPlace = plotDeclarationPlace;
    }
    public String getPlotDeclarationPlace() {
        return this.plotDeclarationPlace;
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

    //--- DATABASE MAPPING : user_id ( VARCHAR ) 
    public void setUserId( String userId ) {
        this.userId = userId;
    }
    public String getUserId() {
        return this.userId;
    }

    //--- DATABASE MAPPING : status ( VARCHAR ) 
    public void setStatus( String status ) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfTblPlotRecMaster( List<TblPlotRecMasterEntity> listOfTblPlotRecMaster ) {
        this.listOfTblPlotRecMaster = listOfTblPlotRecMaster;
    }
    public List<TblPlotRecMasterEntity> getListOfTblPlotRecMaster() {
        return this.listOfTblPlotRecMaster;
    }

    public void setTblMembershipForm( TblMembershipFormEntity tblMembershipForm ) {
        this.tblMembershipForm = tblMembershipForm;
    }
    public TblMembershipFormEntity getTblMembershipForm() {
        return this.tblMembershipForm;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(plotPk);
        sb.append("]:"); 
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

}
