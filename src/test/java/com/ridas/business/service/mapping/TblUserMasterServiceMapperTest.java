/*
 * Created on 29 Feb 2016 ( Time 17:46:21 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.business.service.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.ridas.bean.TblUserMaster;
import com.ridas.bean.jpa.TblUserMasterEntity;
import com.ridas.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class TblUserMasterServiceMapperTest {

	private TblUserMasterServiceMapper tblUserMasterServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		tblUserMasterServiceMapper = new TblUserMasterServiceMapper();
		tblUserMasterServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'TblUserMasterEntity' to 'TblUserMaster'
	 * @param tblUserMasterEntity
	 */
	@Test
	public void testMapTblUserMasterEntityToTblUserMaster() {
		// Given
		TblUserMasterEntity tblUserMasterEntity = new TblUserMasterEntity();
		tblUserMasterEntity.setUserName(mockValues.nextString(100));
		tblUserMasterEntity.setUserPassword(mockValues.nextString(100));
		tblUserMasterEntity.setUserAdmin(mockValues.nextString(100));
		tblUserMasterEntity.setStatus(mockValues.nextString(100));
		tblUserMasterEntity.setCreateDate(mockValues.nextDate());
		tblUserMasterEntity.setModifiedDate(mockValues.nextDate());
		tblUserMasterEntity.setPrefixcolumn(mockValues.nextString(500));
		tblUserMasterEntity.setCountpk(mockValues.nextLong());
		tblUserMasterEntity.setUserId(mockValues.nextString(100));
		
		// When
		TblUserMaster tblUserMaster = tblUserMasterServiceMapper.mapTblUserMasterEntityToTblUserMaster(tblUserMasterEntity);
		
		// Then
		assertEquals(tblUserMasterEntity.getUserName(), tblUserMaster.getUserName());
		assertEquals(tblUserMasterEntity.getUserPassword(), tblUserMaster.getUserPassword());
		assertEquals(tblUserMasterEntity.getUserAdmin(), tblUserMaster.getUserAdmin());
		assertEquals(tblUserMasterEntity.getStatus(), tblUserMaster.getStatus());
		assertEquals(tblUserMasterEntity.getCreateDate(), tblUserMaster.getCreateDate());
		assertEquals(tblUserMasterEntity.getModifiedDate(), tblUserMaster.getModifiedDate());
		assertEquals(tblUserMasterEntity.getPrefixcolumn(), tblUserMaster.getPrefixcolumn());
		assertEquals(tblUserMasterEntity.getCountpk(), tblUserMaster.getCountpk());
		assertEquals(tblUserMasterEntity.getUserId(), tblUserMaster.getUserId());
	}
	
	/**
	 * Test : Mapping from 'TblUserMaster' to 'TblUserMasterEntity'
	 */
	@Test
	public void testMapTblUserMasterToTblUserMasterEntity() {
		// Given
		TblUserMaster tblUserMaster = new TblUserMaster();
		tblUserMaster.setUserName(mockValues.nextString(100));
		tblUserMaster.setUserPassword(mockValues.nextString(100));
		tblUserMaster.setUserAdmin(mockValues.nextString(100));
		tblUserMaster.setStatus(mockValues.nextString(100));
		tblUserMaster.setCreateDate(mockValues.nextDate());
		tblUserMaster.setModifiedDate(mockValues.nextDate());
		tblUserMaster.setPrefixcolumn(mockValues.nextString(500));
		tblUserMaster.setCountpk(mockValues.nextLong());
		tblUserMaster.setUserId(mockValues.nextString(100));

		TblUserMasterEntity tblUserMasterEntity = new TblUserMasterEntity();
		
		// When
		tblUserMasterServiceMapper.mapTblUserMasterToTblUserMasterEntity(tblUserMaster, tblUserMasterEntity);
		
		// Then
		assertEquals(tblUserMaster.getUserName(), tblUserMasterEntity.getUserName());
		assertEquals(tblUserMaster.getUserPassword(), tblUserMasterEntity.getUserPassword());
		assertEquals(tblUserMaster.getUserAdmin(), tblUserMasterEntity.getUserAdmin());
		assertEquals(tblUserMaster.getStatus(), tblUserMasterEntity.getStatus());
		assertEquals(tblUserMaster.getCreateDate(), tblUserMasterEntity.getCreateDate());
		assertEquals(tblUserMaster.getModifiedDate(), tblUserMasterEntity.getModifiedDate());
		assertEquals(tblUserMaster.getPrefixcolumn(), tblUserMasterEntity.getPrefixcolumn());
		assertEquals(tblUserMaster.getCountpk(), tblUserMasterEntity.getCountpk());
		assertEquals(tblUserMaster.getUserId(), tblUserMasterEntity.getUserId());
	}

}