/*
 * Created on 29 Feb 2016 ( Time 17:46:08 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.persistence.services;

import java.util.List;
import java.util.Map;

import com.ridas.bean.jpa.TblUserRoleMasterEntity;

/**
 * Basic persistence operations for entity "TblUserRoleMaster"
 * 
 * This Bean has a basic Primary Key : String
 *
 * @author Telosys Tools Generator
 *
 */
public interface TblUserRoleMasterPersistence {

	/**
	 * Deletes the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param tblUserRoleMaster
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(TblUserRoleMasterEntity tblUserRoleMaster) ;

	/**
	 * Deletes the entity by its Primary Key <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param userRoleId
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(String userRoleId) ;

	/**
	 * Inserts the given entity and commit <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param tblUserRoleMaster
	 */
	public void insert(TblUserRoleMasterEntity tblUserRoleMaster) ;

	/**
	 * Loads the entity for the given Primary Key <br>
	 * @param userRoleId
	 * @return the entity loaded (or null if not found)
	 */
	public TblUserRoleMasterEntity load(String userRoleId) ;

	/**
	 * Loads ALL the entities (use with caution)
	 * @return
	 */
	public List<TblUserRoleMasterEntity> loadAll() ;

	/**
	 * Loads a list of entities using the given "named query" without parameter 
	 * @param queryName
	 * @return
	 */
	public List<TblUserRoleMasterEntity> loadByNamedQuery(String queryName) ;

	/**
	 * Loads a list of entities using the given "named query" with parameters 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<TblUserRoleMasterEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) ;

	/**
	 * Saves (create or update) the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param tblUserRoleMaster
	 * @return
	 */
	public TblUserRoleMasterEntity save(TblUserRoleMasterEntity tblUserRoleMaster) ;

	/**
	 * Search the entities matching the given search criteria
	 * @param criteria
	 * @return
	 */
	public List<TblUserRoleMasterEntity> search( Map<String, Object> criteria ) ;

	/**
	 * Count all the occurrences
	 * @return
	 */
	public long countAll();
    /**
	 * 	done as on 13 feb 2016 added for TblUserMaster for retrieving user details for login
    */
}
