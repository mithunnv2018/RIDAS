/*
 * Created on 29 Feb 2016 ( Time 17:46:06 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.persistence.services;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.ridas.bean.TblPlotMaster;
import com.ridas.bean.jpa.TblPlotMasterEntity;

/**
 * Basic persistence operations for entity "TblPlotMaster"
 * 
 * This Bean has a basic Primary Key : Integer
 *
 * @author Telosys Tools Generator
 *
 */
public interface TblPlotMasterPersistence {

	/**
	 * Deletes the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param tblPlotMaster
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(TblPlotMasterEntity tblPlotMaster) ;

	/**
	 * Deletes the entity by its Primary Key <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param plotPk
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(Integer plotPk) ;

	/**
	 * Inserts the given entity and commit <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param tblPlotMaster
	 */
	public void insert(TblPlotMasterEntity tblPlotMaster) ;

	/**
	 * Loads the entity for the given Primary Key <br>
	 * @param plotPk
	 * @return the entity loaded (or null if not found)
	 */
	public TblPlotMasterEntity load(Integer plotPk) ;

	/**
	 * Loads ALL the entities (use with caution)
	 * @return
	 */
	public List<TblPlotMasterEntity> loadAll() ;

	/**
	 * Loads a list of entities using the given "named query" without parameter 
	 * @param queryName
	 * @return
	 */
	public List<TblPlotMasterEntity> loadByNamedQuery(String queryName) ;

	/**
	 * Loads a list of entities using the given "named query" with parameters 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<TblPlotMasterEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) ;

	/**
	 * Saves (create or update) the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param tblPlotMaster
	 * @return
	 */
	public TblPlotMasterEntity save(TblPlotMasterEntity tblPlotMaster) ;

	/**
	 * Search the entities matching the given search criteria
	 * @param criteria
	 * @return
	 */
	public List<TblPlotMasterEntity> search( Map<String, Object> criteria ) ;

	/**
	 * Count all the occurrences
	 * @return
	 */
	public long countAll();
    /**
	 * 	done as on 13 feb 2016 added for TblUserMaster for retrieving user details for login
    */
	
	

	List<TblPlotMasterEntity> populatePlotNo(String memberId);

	List<TblPlotMasterEntity> loadAllById(Integer plotPk);

	public Connection myconnection();

	
}
