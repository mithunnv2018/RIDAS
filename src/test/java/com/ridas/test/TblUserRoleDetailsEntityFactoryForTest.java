package com.ridas.test;

import com.ridas.bean.jpa.TblUserRoleDetailsEntity;

public class TblUserRoleDetailsEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblUserRoleDetailsEntity newTblUserRoleDetailsEntity() {

		String userRoleDetailId = mockValues.nextString(100);

		TblUserRoleDetailsEntity tblUserRoleDetailsEntity = new TblUserRoleDetailsEntity();
		tblUserRoleDetailsEntity.setUserRoleDetailId(userRoleDetailId);
		return tblUserRoleDetailsEntity;
	}
	
}
