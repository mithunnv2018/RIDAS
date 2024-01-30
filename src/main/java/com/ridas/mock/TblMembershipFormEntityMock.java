
/*
 * Created on 29 Feb 2016 ( Time 17:46:06 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.mock;

import java.util.LinkedList;
import java.util.List;

import com.ridas.bean.jpa.TblMembershipFormEntity;
import com.ridas.mock.tool.MockValues;

public class TblMembershipFormEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public TblMembershipFormEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextString(50) );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public TblMembershipFormEntity createInstance( String memberId ) {
		TblMembershipFormEntity entity = new TblMembershipFormEntity();
		// Init Primary Key fields
		entity.setMemberId( memberId) ;
		// Init Data fields
		entity.setMemberPrefix( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setMemberName( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setMemberSoWoDo( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setMemberDateOfBirth( mockValues.nextDate() ) ; // java.util.Date 
		entity.setMemberNationality( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setMemberResidence( mockValues.nextString(2147483647) ) ; // java.lang.String 
		entity.setMemberOfficeAddress( mockValues.nextString(2147483647) ) ; // java.lang.String 
		entity.setMemberResidentalStatus( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setMemberPan( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setMemberOccupation( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setMemberContactMobile( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setMemberContactResidence( mockValues.nextString(2147483647) ) ; // java.lang.String 
		entity.setMemberContactOffice( mockValues.nextString(2147483647) ) ; // java.lang.String 
		entity.setMemberContactOthers( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setMemberContactEmail( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setMemberMembershipThrough( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setNomineeName( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setNomineeSoWoDo( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setNomineeDateOfBirth( mockValues.nextDate() ) ; // java.util.Date 
		entity.setNomineeAddress( mockValues.nextString(2147483647) ) ; // java.lang.String 
		entity.setNomineeContactNumber( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setNomineeGender( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setNomineeRelationWithMember( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setBankAccNo( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setIfscCode( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setTypeOfAcc( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setBankName( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setBranchAndAddress( mockValues.nextString(2147483647) ) ; // java.lang.String 
		entity.setReceiptNo( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setVerifiedBy( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setCountpk( mockValues.nextLong() ) ; // java.lang.Long 
		entity.setPrefixcolumn( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setCreateDate( mockValues.nextDate() ) ; // java.util.Date 
		entity.setModifiedDate( mockValues.nextDate() ) ; // java.util.Date 
		entity.setUserId( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setStatus( mockValues.nextString(100) ) ; // java.lang.String 
		// Init Link fields (if any)
		// setTblMembershipForm( TODO ) ; // TblMembershipForm 
		// setListOfTblPlotMaster( TODO ) ; // List<TblPlotMaster> 
		// setListOfTblReceiptMaster( TODO ) ; // List<TblReceiptMaster> 
		// setListOfTblMembershipForm( TODO ) ; // List<TblMembershipForm> 
		// setListOfTblBusiAssoMaster( TODO ) ; // List<TblBusiAssoMaster> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<TblMembershipFormEntity> createList(int count) {
		List<TblMembershipFormEntity> list = new LinkedList<TblMembershipFormEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}