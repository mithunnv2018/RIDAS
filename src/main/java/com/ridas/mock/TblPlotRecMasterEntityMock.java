
/*
 * Created on 29 Feb 2016 ( Time 17:46:07 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.mock;

import java.util.LinkedList;
import java.util.List;

import com.ridas.bean.jpa.TblPlotRecMasterEntity;
import com.ridas.mock.tool.MockValues;

public class TblPlotRecMasterEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public TblPlotRecMasterEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public TblPlotRecMasterEntity createInstance( Integer plotReceiptId ) {
		TblPlotRecMasterEntity entity = new TblPlotRecMasterEntity();
		// Init Primary Key fields
		entity.setPlotReceiptId( plotReceiptId) ;
		// Init Data fields
		entity.setCountpk( mockValues.nextLong() ) ; // java.lang.Long 
		entity.setPrefixcolumn( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setCreateDate( mockValues.nextDate() ) ; // java.util.Date 
		entity.setModifiedDate( mockValues.nextDate() ) ; // java.util.Date 
		entity.setStatus( mockValues.nextString(100) ) ; // java.lang.String 
		entity.setUserId( mockValues.nextString(100) ) ; // java.lang.String 
		// Init Link fields (if any)
		// setTblReceiptMaster( TODO ) ; // TblReceiptMaster 
		// setTblPlotMaster( TODO ) ; // TblPlotMaster 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<TblPlotRecMasterEntity> createList(int count) {
		List<TblPlotRecMasterEntity> list = new LinkedList<TblPlotRecMasterEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}