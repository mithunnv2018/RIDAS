
/*
 * Created on 29 Feb 2016 ( Time 17:46:06 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.mock;

import java.util.LinkedList;
import java.util.List;

import com.ridas.bean.jpa.TblPlotMasterEntity;
import com.ridas.mock.tool.MockValues;

public class TblPlotMasterEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public TblPlotMasterEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public TblPlotMasterEntity createInstance( Integer plotPk ) {
		TblPlotMasterEntity entity = new TblPlotMasterEntity();
		// Init Primary Key fields
		entity.setPlotPk( plotPk) ;
		// Init Data fields
		entity.setPlotFatherhusbandName( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setPlotMothersName( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setPlotNameOfProject( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setPlotTypeOfProject( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setPlotArea( mockValues.nextString(500) ) ; // java.lang.String 
		entity.setPlotRequiredArea( mockValues.nextString(500) ) ; // java.lang.String 
		entity.setPlotIsfarmer( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setPlotFarmerState( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setPlotFarmerSyNo( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setPlotFarmerDistrict( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setPlotFarmerVillage( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setPlotDate( mockValues.nextDate() ) ; // java.util.Date 
		entity.setPlotBookingDate( mockValues.nextDate() ) ; // java.util.Date 
		entity.setPlotNo( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setPlotDeclarationDate( mockValues.nextDate() ) ; // java.util.Date 
		entity.setPlotDeclarationPlace( mockValues.nextString(50) ) ; // java.lang.String 
		entity.setCountpk( mockValues.nextLong() ) ; // java.lang.Long 
		entity.setPrefixcolumn( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setCreateDate( mockValues.nextDate() ) ; // java.util.Date 
		entity.setModifiedDate( mockValues.nextDate() ) ; // java.util.Date 
		entity.setUserId( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setStatus( mockValues.nextString(100) ) ; // java.lang.String 
		// Init Link fields (if any)
		// setListOfTblPlotRecMaster( TODO ) ; // List<TblPlotRecMaster> 
		// setTblMembershipForm( TODO ) ; // TblMembershipForm 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<TblPlotMasterEntity> createList(int count) {
		List<TblPlotMasterEntity> list = new LinkedList<TblPlotMasterEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
