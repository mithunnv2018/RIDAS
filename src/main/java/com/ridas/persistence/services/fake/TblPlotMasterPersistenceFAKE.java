/*
 * Created on 29 Feb 2016 ( Time 17:46:07 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.persistence.services.fake;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.ridas.bean.jpa.TblPlotMasterEntity;
import com.ridas.persistence.commons.fake.GenericFakeService;
import com.ridas.persistence.services.TblPlotMasterPersistence;

/**
 * Fake persistence service implementation ( entity "TblPlotMaster" )
 *
 * @author Telosys Tools Generator
 */
public class TblPlotMasterPersistenceFAKE extends GenericFakeService<TblPlotMasterEntity> implements TblPlotMasterPersistence {

	public TblPlotMasterPersistenceFAKE () {
		super(TblPlotMasterEntity.class);
	}
	
	protected TblPlotMasterEntity buildEntity(int index) {
		TblPlotMasterEntity entity = new TblPlotMasterEntity();
		// Init fields with mock values
		entity.setPlotPk( nextInteger() ) ;
		entity.setPlotFatherhusbandName( nextString() ) ;
		entity.setPlotMothersName( nextString() ) ;
		entity.setPlotNameOfProject( nextString() ) ;
		entity.setPlotTypeOfProject( nextString() ) ;
		entity.setPlotArea( nextString() ) ;
		entity.setPlotRequiredArea( nextString() ) ;
		entity.setPlotIsfarmer( nextString() ) ;
		entity.setPlotFarmerState( nextString() ) ;
		entity.setPlotFarmerSyNo( nextString() ) ;
		entity.setPlotFarmerDistrict( nextString() ) ;
		entity.setPlotFarmerVillage( nextString() ) ;
		entity.setPlotDate( nextDate() ) ;
		entity.setPlotBookingDate( nextDate() ) ;
		entity.setPlotNo( nextString() ) ;
		entity.setPlotDeclarationDate( nextDate() ) ;
		entity.setPlotDeclarationPlace( nextString() ) ;
		entity.setCountpk( nextLong() ) ;
		entity.setPrefixcolumn( nextString() ) ;
		entity.setCreateDate( nextDate() ) ;
		entity.setModifiedDate( nextDate() ) ;
		entity.setUserId( nextString() ) ;
		entity.setStatus( nextString() ) ;
		return entity ;
	}
	
	
	public boolean delete(TblPlotMasterEntity entity) {
		log("delete ( TblPlotMasterEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer plotPk ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(TblPlotMasterEntity entity) {
		log("insert ( TblPlotMasterEntity : " + entity + ")" ) ;
	}

	public TblPlotMasterEntity load( Integer plotPk ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<TblPlotMasterEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<TblPlotMasterEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<TblPlotMasterEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public TblPlotMasterEntity save(TblPlotMasterEntity entity) {
		log("insert ( TblPlotMasterEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<TblPlotMasterEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

	@Override
	public List<TblPlotMasterEntity> populatePlotNo(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TblPlotMasterEntity> loadAllById(Integer plotPk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection myconnection() {
		// TODO Auto-generated method stub
		return null;
	}

/**
	 * 	done as on 13 feb 2016 added for TblUserMaster for retrieving user details for login
    */
}
