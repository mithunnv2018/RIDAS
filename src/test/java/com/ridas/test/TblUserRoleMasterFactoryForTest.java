package com.ridas.test;

import com.ridas.bean.TblUserRoleMaster;

public class TblUserRoleMasterFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblUserRoleMaster newTblUserRoleMaster() {

		String userRoleId = mockValues.nextString(100);

		TblUserRoleMaster tblUserRoleMaster = new TblUserRoleMaster();
		tblUserRoleMaster.setUserRoleId(userRoleId);
		return tblUserRoleMaster;
	}
	
}
