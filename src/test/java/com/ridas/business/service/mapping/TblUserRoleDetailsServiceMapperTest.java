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
import com.ridas.bean.TblUserRoleDetails;
import com.ridas.bean.jpa.TblUserRoleDetailsEntity;
import com.ridas.bean.jpa.TblUserRoleMasterEntity;
import com.ridas.bean.jpa.TblUserMasterEntity;
import com.ridas.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class TblUserRoleDetailsServiceMapperTest {

	private TblUserRoleDetailsServiceMapper tblUserRoleDetailsServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		tblUserRoleDetailsServiceMapper = new TblUserRoleDetailsServiceMapper();
		tblUserRoleDetailsServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'TblUserRoleDetailsEntity' to 'TblUserRoleDetails'
	 * @param tblUserRoleDetailsEntity
	 */
	@Test
	public void testMapTblUserRoleDetailsEntityToTblUserRoleDetails() {
		// Given
		TblUserRoleDetailsEntity tblUserRoleDetailsEntity = new TblUserRoleDetailsEntity();
		tblUserRoleDetailsEntity.setUserRoleDetailName(mockValues.nextString(100));
		tblUserRoleDetailsEntity.setStatus(mockValues.nextString(100));
		tblUserRoleDetailsEntity.setUserId(mockValues.nextString(100));
		tblUserRoleDetailsEntity.setCreateDate(mockValues.nextDate());
		tblUserRoleDetailsEntity.setModifiedDate(mockValues.nextDate());
		tblUserRoleDetailsEntity.setPrefixcolumn(mockValues.nextString(500));
		tblUserRoleDetailsEntity.setCountpk(mockValues.nextLong());
		tblUserRoleDetailsEntity.setTblUserRoleMaster(new TblUserRoleMasterEntity());
		tblUserRoleDetailsEntity.getTblUserRoleMaster().setUserRoleId(mockValues.nextString(100));
		tblUserRoleDetailsEntity.setTblUserMaster(new TblUserMasterEntity());
		tblUserRoleDetailsEntity.getTblUserMaster().setUserIdPk(mockValues.nextString(100));
		
		// When
		TblUserRoleDetails tblUserRoleDetails = tblUserRoleDetailsServiceMapper.mapTblUserRoleDetailsEntityToTblUserRoleDetails(tblUserRoleDetailsEntity);
		
		// Then
		assertEquals(tblUserRoleDetailsEntity.getUserRoleDetailName(), tblUserRoleDetails.getUserRoleDetailName());
		assertEquals(tblUserRoleDetailsEntity.getStatus(), tblUserRoleDetails.getStatus());
		assertEquals(tblUserRoleDetailsEntity.getUserId(), tblUserRoleDetails.getUserId());
		assertEquals(tblUserRoleDetailsEntity.getCreateDate(), tblUserRoleDetails.getCreateDate());
		assertEquals(tblUserRoleDetailsEntity.getModifiedDate(), tblUserRoleDetails.getModifiedDate());
		assertEquals(tblUserRoleDetailsEntity.getPrefixcolumn(), tblUserRoleDetails.getPrefixcolumn());
		assertEquals(tblUserRoleDetailsEntity.getCountpk(), tblUserRoleDetails.getCountpk());
		assertEquals(tblUserRoleDetailsEntity.getTblUserRoleMaster().getUserRoleId(), tblUserRoleDetails.getUserRoleId());
		assertEquals(tblUserRoleDetailsEntity.getTblUserMaster().getUserIdPk(), tblUserRoleDetails.getUserRoleUserId());
	}
	
	/**
	 * Test : Mapping from 'TblUserRoleDetails' to 'TblUserRoleDetailsEntity'
	 */
	@Test
	public void testMapTblUserRoleDetailsToTblUserRoleDetailsEntity() {
		// Given
		TblUserRoleDetails tblUserRoleDetails = new TblUserRoleDetails();
		tblUserRoleDetails.setUserRoleDetailName(mockValues.nextString(100));
		tblUserRoleDetails.setStatus(mockValues.nextString(100));
		tblUserRoleDetails.setUserId(mockValues.nextString(100));
		tblUserRoleDetails.setCreateDate(mockValues.nextDate());
		tblUserRoleDetails.setModifiedDate(mockValues.nextDate());
		tblUserRoleDetails.setPrefixcolumn(mockValues.nextString(500));
		tblUserRoleDetails.setCountpk(mockValues.nextLong());
		tblUserRoleDetails.setUserRoleId(mockValues.nextString(100));
		tblUserRoleDetails.setUserRoleUserId(mockValues.nextString(100));

		TblUserRoleDetailsEntity tblUserRoleDetailsEntity = new TblUserRoleDetailsEntity();
		
		// When
		tblUserRoleDetailsServiceMapper.mapTblUserRoleDetailsToTblUserRoleDetailsEntity(tblUserRoleDetails, tblUserRoleDetailsEntity);
		
		// Then
		assertEquals(tblUserRoleDetails.getUserRoleDetailName(), tblUserRoleDetailsEntity.getUserRoleDetailName());
		assertEquals(tblUserRoleDetails.getStatus(), tblUserRoleDetailsEntity.getStatus());
		assertEquals(tblUserRoleDetails.getUserId(), tblUserRoleDetailsEntity.getUserId());
		assertEquals(tblUserRoleDetails.getCreateDate(), tblUserRoleDetailsEntity.getCreateDate());
		assertEquals(tblUserRoleDetails.getModifiedDate(), tblUserRoleDetailsEntity.getModifiedDate());
		assertEquals(tblUserRoleDetails.getPrefixcolumn(), tblUserRoleDetailsEntity.getPrefixcolumn());
		assertEquals(tblUserRoleDetails.getCountpk(), tblUserRoleDetailsEntity.getCountpk());
		assertEquals(tblUserRoleDetails.getUserRoleId(), tblUserRoleDetailsEntity.getTblUserRoleMaster().getUserRoleId());
		assertEquals(tblUserRoleDetails.getUserRoleUserId(), tblUserRoleDetailsEntity.getTblUserMaster().getUserIdPk());
	}

}