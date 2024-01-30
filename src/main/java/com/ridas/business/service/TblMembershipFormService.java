/*
 * Created on 29 Feb 2016 ( Time 17:46:20 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.business.service;

import java.util.List;

import com.ridas.bean.TblMembershipForm;

//done as on 19 feb 2016 introduced CSV Import logic
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
/**
 * Business Service Interface for entity TblMembershipForm.
 */
public interface TblMembershipFormService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param memberId
	 * @return entity
	 */
	TblMembershipForm findById( String memberId  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<TblMembershipForm> findAll();

	List<TblMembershipForm> findAllWhereClause();
	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	TblMembershipForm save(TblMembershipForm entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	TblMembershipForm update(TblMembershipForm entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	TblMembershipForm create(TblMembershipForm entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param memberId
	 */
	void delete( String memberId );

   /**
	* 	done as on 13 feb 2016 added for TblUserMaster for retrieving user details for login
    */

	/**
	 *	done as on 19 feb 2016 introduced CSV Import logic
	*/
	Boolean doImportExcelSpreadSheet(MultipartFile file) throws IOException;

	

}