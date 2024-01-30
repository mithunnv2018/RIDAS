/*
 * Created on 29 Feb 2016 ( Time 17:46:06 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.ridas.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import com.ridas.bean.jpa.TblBusiAssoMasterEntity;
import com.ridas.persistence.commons.jpa.GenericJpaService;
import com.ridas.persistence.commons.jpa.JpaOperation;
import com.ridas.persistence.services.TblBusiAssoMasterPersistence;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import com.ridas.web.common.CustomUserDetails;

import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.springframework.security.core.context.SecurityContextHolder;

//done as on 17 feb 2016 import the related entities too.
import com.ridas.bean.jpa.TblMembershipFormEntity;
import com.ridas.bean.jpa.TblReceiptMasterEntity;



/**
 * JPA implementation for basic persistence operations ( entity "TblBusiAssoMaster" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class TblBusiAssoMasterPersistenceJPA extends GenericJpaService<TblBusiAssoMasterEntity, String> implements TblBusiAssoMasterPersistence {

	/**
	 * Constructor
	 */
	public TblBusiAssoMasterPersistenceJPA() {
		super(TblBusiAssoMasterEntity.class);
	}

	@Override
	public TblBusiAssoMasterEntity load( String buAsId ) {
		return super.load( buAsId );
	}

	@Override
	public boolean delete( String buAsId ) {
	// done as on 17 feb 2016 commented below logic of deleting to dormant by new method above for making the dormant fields for related entities
		return deleteRelationships(buAsId);

	}

	@Override
	public boolean delete(TblBusiAssoMasterEntity entity) {
		if ( entity != null ) {
		// done as on 17 feb 2016 commented below logic of deleting to dormant by new method above for making the dormant fields for related entities
			return deleteRelationships(entity.getBuAsId());
		}
		return false ;
	}

  /** done as on 17 feb 2016 delete the OnetoMany and OneToOne relations to dormant.
	*
	*/	
  private boolean deleteRelationships(final String pk)
  {
    JpaOperation operation = new JpaOperation() {
    @Override
   	public Object exectue(EntityManager em) throws PersistenceException {
    	final TblBusiAssoMasterEntity entity = em.find(TblBusiAssoMasterEntity.class, pk);
    	/*String entityname=TblSectorMasterEntity.class.getName();*/
    	entity.setStatus("dormant");
    	em.merge(entity);


	    if (entity!=null) {
    			 //em.remove(entity);
     			return Boolean.TRUE ;
    		}
    		else {
     			return Boolean.FALSE ;
    		}
   		}
	  } ;
	  // JPA operation execution 
	  Boolean b = (Boolean) execute(operation, true) ;
	  return b.booleanValue();
 	}


	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("TblBusiAssoMasterEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

 	/**
	 * Save the given entity ( TRANSACTIONAL )
	 * @param TblBusiAssoMasterEntity
	 * 
	 * @return
	 */
	@Override
	public TblBusiAssoMasterEntity save(final TblBusiAssoMasterEntity entityToSave) {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {

				Boolean isnew=false;

//done as on 10 feb 2016 add condition to check if the table has auto increment if so hide the logic of manual key geenration.
				
//done as on 09 feb 2016 logic to check if this is an update or savenew call.
				if(entityToSave.getBuAsId().equals("temp"))
				{
					isnew=true;
				
				}
				else
				{
					isnew=false;
				}	
				if(isnew==true)
				{
					TypedQuery<TblBusiAssoMasterEntity> query = em.createQuery("select t from TblBusiAssoMasterEntity t where t.countpk in (SELECT max(t2.countpk) from TblBusiAssoMasterEntity t2)",TblBusiAssoMasterEntity.class);
					TblBusiAssoMasterEntity q=query.getSingleResult();
					Long maxpk=q.getCountpk();
					String prefix=q.getPrefixcolumn();
					++maxpk;
					String pkstring=prefix+maxpk;
//done as on 09 feb 2016 logic to check if the priamry is a string or numerical
					entityToSave.setBuAsId(pkstring);
					entityToSave.setPrefixcolumn(prefix);
					entityToSave.setCountpk(maxpk);
					//done as on 16 feb 2016 to save create date on save 	
					 entityToSave.setCreateDate(new Date());
	
				}

				//done as on 16 feb 2016 to save create date on save
				entityToSave.setStatus("active");
	    		CustomUserDetails userDetails =
       				(CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				entityToSave.setUserId(userDetails.getUserid());

    			entityToSave.setModifiedDate(new Date());
			
				TblBusiAssoMasterEntity managedEntity = em.merge(entityToSave);
				return managedEntity;
			}
		} ;
		// JPA operation execution 
		return (TblBusiAssoMasterEntity) execute(operation, true) ;
	}
/**
	 * 	done as on 13 feb 2016 added for TblUserMaster for retrieving user details for login
    */

	public Connection connection;
	@Override
	public Connection myconnection(){
		JpaOperation operation = new JpaOperation() {
			

			@Override
			public Object exectue(EntityManager em) throws PersistenceException  {
				Session s = (Session) em.getDelegate();
				org.hibernate.SessionFactory sessionFactory=s.getSessionFactory();
				ConnectionProvider cp=((SessionFactoryImpl)sessionFactory).getConnectionProvider();
				try {
					 connection=cp.getConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null; 
			}
		} ;
		execute(operation);
		return connection;
		
	}
}
