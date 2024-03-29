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

import com.ridas.bean.TblMembershipForm;
import com.ridas.bean.jpa.TblMembershipFormEntity;
import java.util.Date;
import java.util.List;
import com.ridas.business.service.mapping.TblMembershipFormServiceMapper;
import com.ridas.persistence.services.jpa.TblMembershipFormPersistenceJPA;
import com.ridas.test.TblMembershipFormFactoryForTest;
import com.ridas.test.TblMembershipFormEntityFactoryForTest;
import com.ridas.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of TblMembershipFormService
 */
@RunWith(MockitoJUnitRunner.class)
public class TblMembershipFormServiceImplTest {

	@InjectMocks
	private TblMembershipFormServiceImpl tblMembershipFormService;
	@Mock
	private TblMembershipFormPersistenceJPA tblMembershipFormPersistenceJPA;
	@Mock
	private TblMembershipFormServiceMapper tblMembershipFormServiceMapper;
	
	private TblMembershipFormFactoryForTest tblMembershipFormFactoryForTest = new TblMembershipFormFactoryForTest();

	private TblMembershipFormEntityFactoryForTest tblMembershipFormEntityFactoryForTest = new TblMembershipFormEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		String memberId = mockValues.nextString(50);
		
		TblMembershipFormEntity tblMembershipFormEntity = tblMembershipFormPersistenceJPA.load(memberId);
		
		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();
		when(tblMembershipFormServiceMapper.mapTblMembershipFormEntityToTblMembershipForm(tblMembershipFormEntity)).thenReturn(tblMembershipForm);

		// When
		TblMembershipForm tblMembershipFormFound = tblMembershipFormService.findById(memberId);

		// Then
		assertEquals(tblMembershipForm.getMemberId(),tblMembershipFormFound.getMemberId());
	}

	@Test
	public void findAll() {
		// Given
		List<TblMembershipFormEntity> tblMembershipFormEntitys = new ArrayList<TblMembershipFormEntity>();
		TblMembershipFormEntity tblMembershipFormEntity1 = tblMembershipFormEntityFactoryForTest.newTblMembershipFormEntity();
		tblMembershipFormEntitys.add(tblMembershipFormEntity1);
		TblMembershipFormEntity tblMembershipFormEntity2 = tblMembershipFormEntityFactoryForTest.newTblMembershipFormEntity();
		tblMembershipFormEntitys.add(tblMembershipFormEntity2);
		when(tblMembershipFormPersistenceJPA.loadAll()).thenReturn(tblMembershipFormEntitys);
		
		TblMembershipForm tblMembershipForm1 = tblMembershipFormFactoryForTest.newTblMembershipForm();
		when(tblMembershipFormServiceMapper.mapTblMembershipFormEntityToTblMembershipForm(tblMembershipFormEntity1)).thenReturn(tblMembershipForm1);
		TblMembershipForm tblMembershipForm2 = tblMembershipFormFactoryForTest.newTblMembershipForm();
		when(tblMembershipFormServiceMapper.mapTblMembershipFormEntityToTblMembershipForm(tblMembershipFormEntity2)).thenReturn(tblMembershipForm2);

		// When
		List<TblMembershipForm> tblMembershipFormsFounds = tblMembershipFormService.findAll();

		// Then
		assertTrue(tblMembershipForm1 == tblMembershipFormsFounds.get(0));
		assertTrue(tblMembershipForm2 == tblMembershipFormsFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();

		TblMembershipFormEntity tblMembershipFormEntity = tblMembershipFormEntityFactoryForTest.newTblMembershipFormEntity();
		when(tblMembershipFormPersistenceJPA.load(tblMembershipForm.getMemberId())).thenReturn(null);
		
		tblMembershipFormEntity = new TblMembershipFormEntity();
		tblMembershipFormServiceMapper.mapTblMembershipFormToTblMembershipFormEntity(tblMembershipForm, tblMembershipFormEntity);
		TblMembershipFormEntity tblMembershipFormEntitySaved = tblMembershipFormPersistenceJPA.save(tblMembershipFormEntity);
		
		TblMembershipForm tblMembershipFormSaved = tblMembershipFormFactoryForTest.newTblMembershipForm();
		when(tblMembershipFormServiceMapper.mapTblMembershipFormEntityToTblMembershipForm(tblMembershipFormEntitySaved)).thenReturn(tblMembershipFormSaved);

		// When
		TblMembershipForm tblMembershipFormResult = tblMembershipFormService.create(tblMembershipForm);

		// Then
		assertTrue(tblMembershipFormResult == tblMembershipFormSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();

		TblMembershipFormEntity tblMembershipFormEntity = tblMembershipFormEntityFactoryForTest.newTblMembershipFormEntity();
		when(tblMembershipFormPersistenceJPA.load(tblMembershipForm.getMemberId())).thenReturn(tblMembershipFormEntity);

		// When
		Exception exception = null;
		try {
			tblMembershipFormService.create(tblMembershipForm);
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
		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();

		TblMembershipFormEntity tblMembershipFormEntity = tblMembershipFormEntityFactoryForTest.newTblMembershipFormEntity();
		when(tblMembershipFormPersistenceJPA.load(tblMembershipForm.getMemberId())).thenReturn(tblMembershipFormEntity);
		
		TblMembershipFormEntity tblMembershipFormEntitySaved = tblMembershipFormEntityFactoryForTest.newTblMembershipFormEntity();
		when(tblMembershipFormPersistenceJPA.save(tblMembershipFormEntity)).thenReturn(tblMembershipFormEntitySaved);
		
		TblMembershipForm tblMembershipFormSaved = tblMembershipFormFactoryForTest.newTblMembershipForm();
		when(tblMembershipFormServiceMapper.mapTblMembershipFormEntityToTblMembershipForm(tblMembershipFormEntitySaved)).thenReturn(tblMembershipFormSaved);

		// When
		TblMembershipForm tblMembershipFormResult = tblMembershipFormService.update(tblMembershipForm);

		// Then
		verify(tblMembershipFormServiceMapper).mapTblMembershipFormToTblMembershipFormEntity(tblMembershipForm, tblMembershipFormEntity);
		assertTrue(tblMembershipFormResult == tblMembershipFormSaved);
	}

	@Test
	public void delete() {
		// Given
		String memberId = mockValues.nextString(50);

		// When
		tblMembershipFormService.delete(memberId);

		// Then
		verify(tblMembershipFormPersistenceJPA).delete(memberId);
		
	}

}
