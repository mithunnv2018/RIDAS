/*
 * Created on 29 Feb 2016 ( Time 17:46:21 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.ridas.bean.TblUserMaster;
import com.ridas.bean.jpa.TblUserMasterEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class TblUserMasterServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public TblUserMasterServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'TblUserMasterEntity' to 'TblUserMaster'
	 * @param tblUserMasterEntity
	 */
	public TblUserMaster mapTblUserMasterEntityToTblUserMaster(TblUserMasterEntity tblUserMasterEntity) {
		if(tblUserMasterEntity == null) {
			return null;
		}

		//--- Generic mapping 
		TblUserMaster tblUserMaster = map(tblUserMasterEntity, TblUserMaster.class);

		return tblUserMaster;
	}
	
	/**
	 * Mapping from 'TblUserMaster' to 'TblUserMasterEntity'
	 * @param tblUserMaster
	 * @param tblUserMasterEntity
	 */
	public void mapTblUserMasterToTblUserMasterEntity(TblUserMaster tblUserMaster, TblUserMasterEntity tblUserMasterEntity) {
		if(tblUserMaster == null) {
			return;
		}

		//--- Generic mapping 
		map(tblUserMaster, tblUserMasterEntity);

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