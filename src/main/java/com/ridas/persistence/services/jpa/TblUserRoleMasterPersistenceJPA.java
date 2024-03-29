/*
 * Created on 29 Feb 2016 ( Time 17:46:08 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.ridas.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import com.ridas.bean.jpa.TblUserRoleMasterEntity;
import com.ridas.persistence.commons.jpa.GenericJpaService;
import com.ridas.persistence.commons.jpa.JpaOperation;
import com.ridas.persistence.services.TblUserRoleMasterPersistence;
import java.util.List;
import java.util.Date;
import com.ridas.web.common.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

//done as on 17 feb 2016 import the related entities too.
import com.ridas.bean.jpa.TblUserRoleDetailsEntity;



/**
 * JPA implementation for basic persistence operations ( entity "TblUserRoleMaster" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class TblUserRoleMasterPersistenceJPA extends GenericJpaService<TblUserRoleMasterEntity, String> implements TblUserRoleMasterPersistence {

	/**
	 * Constructor
	 */
	public TblUserRoleMasterPersistenceJPA() {
		super(TblUserRoleMasterEntity.class);
	}

	@Override
	public TblUserRoleMasterEntity load( String userRoleId ) {
		return super.load( userRoleId );
	}

	@Override
	public boolean delete( String userRoleId ) {
	// done as on 17 feb 2016 commented below logic of deleting to dormant by new method above for making the dormant fields for related entities
		return deleteRelationships(userRoleId);

	}

	@Override
	public boolean delete(TblUserRoleMasterEntity entity) {
		if ( entity != null ) {
		// done as on 17 feb 2016 commented below logic of deleting to dormant by new method above for making the dormant fields for related entities
			return deleteRelationships(entity.getUserRoleId());
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
    	final TblUserRoleMasterEntity entity = em.find(TblUserRoleMasterEntity.class, pk);
    	/*String entityname=TblSectorMasterEntity.class.getName();*/
    	entity.setStatus("dormant");
    	em.merge(entity);

		
 
		List<TblUserRoleDetailsEntity> TblUserRoleDetailslist=entity.getListOfTblUserRoleDetails();
		for(int i=0;i<TblUserRoleDetailslist.size();i++)
		{
			TblUserRoleDetailsEntity obj= TblUserRoleDetailslist.get(i);
			obj.setStatus("dormant");
			em.merge(obj);
		}
 

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
				Query query = em.createNamedQuery("TblUserRoleMasterEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

 	/**
	 * Save the given entity ( TRANSACTIONAL )
	 * @param TblUserRoleMasterEntity
	 * 
	 * @return
	 */
	@Override
	public TblUserRoleMasterEntity save(final TblUserRoleMasterEntity entityToSave) {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {

				Boolean isnew=false;

//done as on 10 feb 2016 add condition to check if the table has auto increment if so hide the logic of manual key geenration.
				
//done as on 09 feb 2016 logic to check if this is an update or savenew call.
				if(entityToSave.getUserRoleId().equals("temp"))
				{
					isnew=true;
				
				}
				else
				{
					isnew=false;
				}	
				if(isnew==true)
				{
					TypedQuery<TblUserRoleMasterEntity> query = em.createQuery("select t from TblUserRoleMasterEntity t where t.countpk in (SELECT max(t2.countpk) from TblUserRoleMasterEntity t2)",TblUserRoleMasterEntity.class);
					TblUserRoleMasterEntity q=query.getSingleResult();
					Long maxpk=q.getCountpk();
					String prefix=q.getPrefixcolumn();
					++maxpk;
					String pkstring=prefix+maxpk;
//done as on 09 feb 2016 logic to check if the priamry is a string or numerical
					entityToSave.setUserRoleId(pkstring);
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
			
				TblUserRoleMasterEntity managedEntity = em.merge(entityToSave);
				return managedEntity;
			}
		} ;
		// JPA operation execution 
		return (TblUserRoleMasterEntity) execute(operation, true) ;
	}
/**
	 * 	done as on 13 feb 2016 added for TblUserMaster for retrieving user details for login
    */
}
