/*
 * Created on 29 Feb 2016 ( Time 17:46:20 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.business.service.mapping;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.ridas.bean.TblBusiAssoMaster;
import com.ridas.bean.TblReceiptMaster;
import com.ridas.bean.jpa.TblBusiAssoMasterEntity;
import com.ridas.bean.jpa.TblMembershipFormEntity;
import com.ridas.bean.jpa.TblReceiptMasterEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class TblBusiAssoMasterServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	@Resource
	private TblReceiptMasterServiceMapper tblReceiptMasterServiceMapper;
	
	/**
	 * Constructor.
	 */
	public TblBusiAssoMasterServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'TblBusiAssoMasterEntity' to 'TblBusiAssoMaster'
	 * @param tblBusiAssoMasterEntity
	 */
	public TblBusiAssoMaster mapTblBusiAssoMasterEntityToTblBusiAssoMaster(TblBusiAssoMasterEntity tblBusiAssoMasterEntity) {
		if(tblBusiAssoMasterEntity == null) {
			return null;
		}

		//--- Generic mapping 
		TblBusiAssoMaster tblBusiAssoMaster = map(tblBusiAssoMasterEntity, TblBusiAssoMaster.class);

		//--- Link mapping ( link to TblMembershipForm )
		if(tblBusiAssoMasterEntity.getTblMembershipForm() != null) {
			tblBusiAssoMaster.setMemberId(tblBusiAssoMasterEntity.getTblMembershipForm().getMemberId());
		}
		//--- Link mapping ( link to TblReceiptMaster )
		if(tblBusiAssoMasterEntity.getTblReceiptMaster() != null) {
			tblBusiAssoMaster.setReceiptPk(tblBusiAssoMasterEntity.getTblReceiptMaster().getReceiptPk());
			
			 TblReceiptMasterEntity tblReceiptMasterEntity = tblBusiAssoMasterEntity.getTblReceiptMaster();
			 TblReceiptMaster receipt=tblReceiptMasterServiceMapper.mapTblReceiptMasterEntityToTblReceiptMaster(tblReceiptMasterEntity);
			
			tblBusiAssoMaster.setReceipt(receipt);
		}
		System.out.println("TblBusiAssoMasterServiceMapper.mapTblBusiAssoMasterEntityToTblBusiAssoMaster()"+tblBusiAssoMaster.toString());
		return tblBusiAssoMaster;
	}
	
	/**
	 * Mapping from 'TblBusiAssoMaster' to 'TblBusiAssoMasterEntity'
	 * @param tblBusiAssoMaster
	 * @param tblBusiAssoMasterEntity
	 */
	public void mapTblBusiAssoMasterToTblBusiAssoMasterEntity(TblBusiAssoMaster tblBusiAssoMaster, TblBusiAssoMasterEntity tblBusiAssoMasterEntity) {
		if(tblBusiAssoMaster == null) {
			return;
		}

		//--- Generic mapping 
		map(tblBusiAssoMaster, tblBusiAssoMasterEntity);

		//--- Link mapping ( link : tblBusiAssoMaster )
		if( hasLinkToTblMembershipForm(tblBusiAssoMaster) ) {
			TblMembershipFormEntity tblMembershipForm1 = new TblMembershipFormEntity();
			tblMembershipForm1.setMemberId( tblBusiAssoMaster.getMemberId() );
			tblBusiAssoMasterEntity.setTblMembershipForm( tblMembershipForm1 );
		} else {
			tblBusiAssoMasterEntity.setTblMembershipForm( null );
		}

		//--- Link mapping ( link : tblBusiAssoMaster )
		if( hasLinkToTblReceiptMaster(tblBusiAssoMaster) ) {
			TblReceiptMasterEntity tblReceiptMaster2 = new TblReceiptMasterEntity();
			tblReceiptMaster2.setReceiptPk( tblBusiAssoMaster.getReceiptPk() );
			tblBusiAssoMasterEntity.setTblReceiptMaster( tblReceiptMaster2 );
		} else {
			tblBusiAssoMasterEntity.setTblReceiptMaster( null );
		}

	}
	
	/**
	 * Verify that TblMembershipForm id is valid.
	 * @param TblMembershipForm TblMembershipForm
	 * @return boolean
	 */
	private boolean hasLinkToTblMembershipForm(TblBusiAssoMaster tblBusiAssoMaster) {
		if(tblBusiAssoMaster.getMemberId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that TblReceiptMaster id is valid.
	 * @param TblReceiptMaster TblReceiptMaster
	 * @return boolean
	 */
	private boolean hasLinkToTblReceiptMaster(TblBusiAssoMaster tblBusiAssoMaster) {
		if(tblBusiAssoMaster.getReceiptPk() != null) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}