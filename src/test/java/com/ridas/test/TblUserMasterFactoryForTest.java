package com.ridas.test;

import com.ridas.bean.TblUserMaster;

public class TblUserMasterFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblUserMaster newTblUserMaster() {

		String userIdPk = mockValues.nextString(100);

		TblUserMaster tblUserMaster = new TblUserMaster();
		tblUserMaster.setUserIdPk(userIdPk);
		return tblUserMaster;
	}
	
}
