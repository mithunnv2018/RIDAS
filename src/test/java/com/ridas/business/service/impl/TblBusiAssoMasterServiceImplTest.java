/*
 * Created on 29 Feb 2016 ( Time 17:46:20 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.ridas.bean.TblBusiAssoMaster;
import com.ridas.bean.jpa.TblBusiAssoMasterEntity;
import java.util.Date;
import com.ridas.business.service.mapping.TblBusiAssoMasterServiceMapper;
import com.ridas.persistence.services.jpa.TblBusiAssoMasterPersistenceJPA;
import com.ridas.test.TblBusiAssoMasterFactoryForTest;
import com.ridas.test.TblBusiAssoMasterEntityFactoryForTest;
import com.ridas.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of TblBusiAssoMasterService
 */
@RunWith(MockitoJUnitRunner.class)
public class TblBusiAssoMasterServiceImplTest {

	@InjectMocks
	private TblBusiAssoMasterServiceImpl tblBusiAssoMasterService;
	@Mock
	private TblBusiAssoMasterPersistenceJPA tblBusiAssoMasterPersistenceJPA;
	@Mock
	private TblBusiAssoMasterServiceMapper tblBusiAssoMasterServiceMapper;
	
	private TblBusiAssoMasterFactoryForTest tblBusiAssoMasterFactoryForTest = new TblBusiAssoMasterFactoryForTest();

	private TblBusiAssoMasterEntityFactoryForTest tblBusiAssoMasterEntityFactoryForTest = new TblBusiAssoMasterEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		String buAsId = mockValues.nextString(50);
		
		TblBusiAssoMasterEntity tblBusiAssoMasterEntity = tblBusiAssoMasterPersistenceJPA.load(buAsId);
		
		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		when(tblBusiAssoMasterServiceMapper.mapTblBusiAssoMasterEntityToTblBusiAssoMaster(tblBusiAssoMasterEntity)).thenReturn(tblBusiAssoMaster);

		// When
		TblBusiAssoMaster tblBusiAssoMasterFound = tblBusiAssoMasterService.findById(buAsId);

		// Then
		assertEquals(tblBusiAssoMaster.getBuAsId(),tblBusiAssoMasterFound.getBuAsId());
	}

	@Test
	public void findAll() {
		// Given
		List<TblBusiAssoMasterEntity> tblBusiAssoMasterEntitys = new ArrayList<TblBusiAssoMasterEntity>();
		TblBusiAssoMasterEntity tblBusiAssoMasterEntity1 = tblBusiAssoMasterEntityFactoryForTest.newTblBusiAssoMasterEntity();
		tblBusiAssoMasterEntitys.add(tblBusiAssoMasterEntity1);
		TblBusiAssoMasterEntity tblBusiAssoMasterEntity2 = tblBusiAssoMasterEntityFactoryForTest.newTblBusiAssoMasterEntity();
		tblBusiAssoMasterEntitys.add(tblBusiAssoMasterEntity2);
		when(tblBusiAssoMasterPersistenceJPA.loadAll()).thenReturn(tblBusiAssoMasterEntitys);
		
		TblBusiAssoMaster tblBusiAssoMaster1 = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		when(tblBusiAssoMasterServiceMapper.mapTblBusiAssoMasterEntityToTblBusiAssoMaster(tblBusiAssoMasterEntity1)).thenReturn(tblBusiAssoMaster1);
		TblBusiAssoMaster tblBusiAssoMaster2 = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		when(tblBusiAssoMasterServiceMapper.mapTblBusiAssoMasterEntityToTblBusiAssoMaster(tblBusiAssoMasterEntity2)).thenReturn(tblBusiAssoMaster2);

		// When
		List<TblBusiAssoMaster> tblBusiAssoMastersFounds = tblBusiAssoMasterService.findAll();

		// Then
		assertTrue(tblBusiAssoMaster1 == tblBusiAssoMastersFounds.get(0));
		assertTrue(tblBusiAssoMaster2 == tblBusiAssoMastersFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();

		TblBusiAssoMasterEntity tblBusiAssoMasterEntity = tblBusiAssoMasterEntityFactoryForTest.newTblBusiAssoMasterEntity();
		when(tblBusiAssoMasterPersistenceJPA.load(tblBusiAssoMaster.getBuAsId())).thenReturn(null);
		
		tblBusiAssoMasterEntity = new TblBusiAssoMasterEntity();
		tblBusiAssoMasterServiceMapper.mapTblBusiAssoMasterToTblBusiAssoMasterEntity(tblBusiAssoMaster, tblBusiAssoMasterEntity);
		TblBusiAssoMasterEntity tblBusiAssoMasterEntitySaved = tblBusiAssoMasterPersistenceJPA.save(tblBusiAssoMasterEntity);
		
		TblBusiAssoMaster tblBusiAssoMasterSaved = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		when(tblBusiAssoMasterServiceMapper.mapTblBusiAssoMasterEntityToTblBusiAssoMaster(tblBusiAssoMasterEntitySaved)).thenReturn(tblBusiAssoMasterSaved);

		// When
		TblBusiAssoMaster tblBusiAssoMasterResult = tblBusiAssoMasterService.create(tblBusiAssoMaster);

		// Then
		assertTrue(tblBusiAssoMasterResult == tblBusiAssoMasterSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();

		TblBusiAssoMasterEntity tblBusiAssoMasterEntity = tblBusiAssoMasterEntityFactoryForTest.newTblBusiAssoMasterEntity();
		when(tblBusiAssoMasterPersistenceJPA.load(tblBusiAssoMaster.getBuAsId())).thenReturn(tblBusiAssoMasterEntity);

		// When
		Exception exception = null;
		try {
			tblBusiAssoMasterService.create(tblBusiAssoMaster);
		} catch(Exception e) {
			exception = e;
		}

		// Then
		assertTrue(exception instanceof IllegalStateException);
		assertEquals("already.exists", exception.getMessage());
	}

	@Test
	public void update() {
		// Given
		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();

		TblBusiAssoMasterEntity tblBusiAssoMasterEntity = tblBusiAssoMasterEntityFactoryForTest.newTblBusiAssoMasterEntity();
		when(tblBusiAssoMasterPersistenceJPA.load(tblBusiAssoMaster.getBuAsId())).thenReturn(tblBusiAssoMasterEntity);
		
		TblBusiAssoMasterEntity tblBusiAssoMasterEntitySaved = tblBusiAssoMasterEntityFactoryForTest.newTblBusiAssoMasterEntity();
		when(tblBusiAssoMasterPersistenceJPA.save(tblBusiAssoMasterEntity)).thenReturn(tblBusiAssoMasterEntitySaved);
		
		TblBusiAssoMaster tblBusiAssoMasterSaved = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		when(tblBusiAssoMasterServiceMapper.mapTblBusiAssoMasterEntityToTblBusiAssoMaster(tblBusiAssoMasterEntitySaved)).thenReturn(tblBusiAssoMasterSaved);

		// When
		TblBusiAssoMaster tblBusiAssoMasterResult = tblBusiAssoMasterService.update(tblBusiAssoMaster);

		// Then
		verify(tblBusiAssoMasterServiceMapper).mapTblBusiAssoMasterToTblBusiAssoMasterEntity(tblBusiAssoMaster, tblBusiAssoMasterEntity);
		assertTrue(tblBusiAssoMasterResult == tblBusiAssoMasterSaved);
	}

	@Test
	public void delete() {
		// Given
		String buAsId = mockValues.nextString(50);

		// When
		tblBusiAssoMasterService.delete(buAsId);

		// Then
		verify(tblBusiAssoMasterPersistenceJPA).delete(buAsId);
		
	}

}