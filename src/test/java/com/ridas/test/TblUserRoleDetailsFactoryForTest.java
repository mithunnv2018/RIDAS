package com.ridas.test;

import com.ridas.bean.TblUserRoleDetails;

public class TblUserRoleDetailsFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblUserRoleDetails newTblUserRoleDetails() {

		String userRoleDetailId = mockValues.nextString(100);

		TblUserRoleDetails tblUserRoleDetails = new TblUserRoleDetails();
		tblUserRoleDetails.setUserRoleDetailId(userRoleDetailId);
		return tblUserRoleDetails;
	}
	
}
