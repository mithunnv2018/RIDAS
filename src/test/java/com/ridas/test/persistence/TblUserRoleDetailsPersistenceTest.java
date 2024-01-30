/*
 * Created on 29 Feb 2016 ( Time 17:46:08 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.test.persistence;


import com.ridas.bean.jpa.TblUserRoleDetailsEntity;
import com.ridas.mock.TblUserRoleDetailsEntityMock;
import com.ridas.persistence.PersistenceServiceProvider;
import com.ridas.persistence.services.TblUserRoleDetailsPersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for TblUserRoleDetails persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class TblUserRoleDetailsPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test count ..." );
		
		TblUserRoleDetailsPersistence service = PersistenceServiceProvider.getService(TblUserRoleDetailsPersistence.class);
		System.out.println("CountAll = " + service.countAll() );
	}
	
	@Test
	public void test2() {
		
		System.out.println("Test TblUserRoleDetails persistence : delete + load ..." );
		
		TblUserRoleDetailsPersistence service = PersistenceServiceProvider.getService(TblUserRoleDetailsPersistence.class);
		
		TblUserRoleDetailsEntityMock mock = new TblUserRoleDetailsEntityMock();
		
		// TODO : set primary key values here 
		process( service, mock, "A"  );
		// process( service, mock, ... );
	}

	private void process(TblUserRoleDetailsPersistence service, TblUserRoleDetailsEntityMock mock, String userRoleDetailId ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		TblUserRoleDetailsEntity entity = service.load( userRoleDetailId );
		if ( entity != null ) {
			// Found 
			System.out.println("   FOUND : " + entity );
			
			// Save (update) with the same values to avoid database integrity errors  
			System.out.println(" . save : " + entity );
			service.save(entity);
			System.out.println("   saved : " + entity );
		}
		else {
			// Not found 
			System.out.println("   NOT FOUND" );
			// Create a new instance 
			entity = mock.createInstance( userRoleDetailId ) ;
			Assert.assertNotNull(entity);

			// This entity references the following entities : 
			// . TblUserRoleMaster
			// . TblUserMaster
			/* Insert only if references are OK
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );
			*/

			System.out.println(" . delete : " );
			boolean deleted = service.delete( userRoleDetailId );
			System.out.println("   deleted = " + deleted);
		}		
	}
}