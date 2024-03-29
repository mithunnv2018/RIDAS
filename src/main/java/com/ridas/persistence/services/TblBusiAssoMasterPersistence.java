/*
 * Created on 29 Feb 2016 ( Time 17:46:06 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.persistence.services;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.ridas.bean.jpa.TblBusiAssoMasterEntity;

/**
 * Basic persistence operations for entity "TblBusiAssoMaster"
 * 
 * This Bean has a basic Primary Key : String
 *
 * @author Telosys Tools Generator
 *
 */
public interface TblBusiAssoMasterPersistence {

	/**
	 * Deletes the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param tblBusiAssoMaster
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(TblBusiAssoMasterEntity tblBusiAssoMaster) ;

	/**
	 * Deletes the entity by its Primary Key <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param buAsId
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(String buAsId) ;

	/**
	 * Inserts the given entity and commit <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param tblBusiAssoMaster
	 */
	public void insert(TblBusiAssoMasterEntity tblBusiAssoMaster) ;

	/**
	 * Loads the entity for the given Primary Key <br>
	 * @param buAsId
	 * @return the entity loaded (or null if not found)
	 */
	public TblBusiAssoMasterEntity load(String buAsId) ;

	/**
	 * Loads ALL the entities (use with caution)
	 * @return
	 */
	public List<TblBusiAssoMasterEntity> loadAll() ;

	/**
	 * Loads a list of entities using the given "named query" without parameter 
	 * @param queryName
	 * @return
	 */
	public List<TblBusiAssoMasterEntity> loadByNamedQuery(String queryName) ;

	/**
	 * Loads a list of entities using the given "named query" with parameters 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<TblBusiAssoMasterEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) ;

	/**
	 * Saves (create or update) the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param tblBusiAssoMaster
	 * @return
	 */
	public TblBusiAssoMasterEntity save(TblBusiAssoMasterEntity tblBusiAssoMaster) ;

	/**
	 * Search the entities matching the given search criteria
	 * @param criteria
	 * @return
	 */
	public List<TblBusiAssoMasterEntity> search( Map<String, Object> criteria ) ;

	/**
	 * Count all the occurrences
	 * @return
	 */
	public long countAll();
    /**
	 * 	done as on 13 feb 2016 added for TblUserMaster for retrieving user details for login
    */

	public Connection myconnection();
}
