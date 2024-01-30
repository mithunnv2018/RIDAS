/*
 * Created on 29 Feb 2016 ( Time 17:46:07 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.test.persistence;


import com.ridas.bean.jpa.TblReceiptMasterEntity;
import com.ridas.mock.TblReceiptMasterEntityMock;
import com.ridas.persistence.PersistenceServiceProvider;
import com.ridas.persistence.services.TblReceiptMasterPersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for TblReceiptMaster persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class TblReceiptMasterPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test count ..." );
		
		TblReceiptMasterPersistence service = PersistenceServiceProvider.getService(TblReceiptMasterPersistence.class);
		System.out.println("CountAll = " + service.countAll() );
	}
	
	@Test
	public void test2() {
		
		System.out.println("Test TblReceiptMaster persistence : delete + load ..." );
		
		TblReceiptMasterPersistence service = PersistenceServiceProvider.getService(TblReceiptMasterPersistence.class);
		
		TblReceiptMasterEntityMock mock = new TblReceiptMasterEntityMock();
		
		// TODO : set primary key values here 
		process( service, mock, "A"  );
		// process( service, mock, ... );
	}

	private void process(TblReceiptMasterPersistence service, TblReceiptMasterEntityMock mock, String receiptPk ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		TblReceiptMasterEntity entity = service.load( receiptPk );
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
			entity = mock.createInstance( receiptPk ) ;
			Assert.assertNotNull(entity);

			// This entity references the following entities : 
			// . TblMembershipForm
			/* Insert only if references are OK
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );
			*/

			System.out.println(" . delete : " );
			boolean deleted = service.delete( receiptPk );
			System.out.println("   deleted = " + deleted);
		}		
	}
}