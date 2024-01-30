/*
 * Created on 29 Feb 2016 ( Time 17:46:07 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.persistence.services;

import java.util.List;
import java.util.Map;

import com.ridas.bean.jpa.TblPlotRecMasterEntity;

/**
 * Basic persistence operations for entity "TblPlotRecMaster"
 * 
 * This Bean has a basic Primary Key : Integer
 *
 * @author Telosys Tools Generator
 *
 */
public interface TblPlotRecMasterPersistence {

	/**
	 * Deletes the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param tblPlotRecMaster
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(TblPlotRecMasterEntity tblPlotRecMaster) ;

	/**
	 * Deletes the entity by its Primary Key <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param plotReceiptId
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(Integer plotReceiptId) ;

	/**
	 * Inserts the given entity and commit <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param tblPlotRecMaster
	 */
	public void insert(TblPlotRecMasterEntity tblPlotRecMaster) ;

	/**
	 * Loads the entity for the given Primary Key <br>
	 * @param plotReceiptId
	 * @return the entity loaded (or null if not found)
	 */
	public TblPlotRecMasterEntity load(Integer plotReceiptId) ;

	/**
	 * Loads ALL the entities (use with caution)
	 * @return
	 */
	public List<TblPlotRecMasterEntity> loadAll() ;

	/**
	 * Loads a list of entities using the given "named query" without parameter 
	 * @param queryName
	 * @return
	 */
	public List<TblPlotRecMasterEntity> loadByNamedQuery(String queryName) ;

	/**
	 * Loads a list of entities using the given "named query" with parameters 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<TblPlotRecMasterEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) ;

	/**
	 * Saves (create or update) the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param tblPlotRecMaster
	 * @return
	 */
	public TblPlotRecMasterEntity save(TblPlotRecMasterEntity tblPlotRecMaster) ;

	/**
	 * Search the entities matching the given search criteria
	 * @param criteria
	 * @return
	 */
	public List<TblPlotRecMasterEntity> search( Map<String, Object> criteria ) ;

	/**
	 * Count all the occurrences
	 * @return
	 */
	public long countAll();
    /**
	 * 	done as on 13 feb 2016 added for TblUserMaster for retrieving user details for login
    */
}