package com.ridas.test;

import com.ridas.bean.jpa.TblUserRoleMasterEntity;

public class TblUserRoleMasterEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblUserRoleMasterEntity newTblUserRoleMasterEntity() {

		String userRoleId = mockValues.nextString(100);

		TblUserRoleMasterEntity tblUserRoleMasterEntity = new TblUserRoleMasterEntity();
		tblUserRoleMasterEntity.setUserRoleId(userRoleId);
		return tblUserRoleMasterEntity;
	}
	
}
