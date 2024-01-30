package com.ridas.test;

import com.ridas.bean.jpa.TblUserMasterEntity;

public class TblUserMasterEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblUserMasterEntity newTblUserMasterEntity() {

		String userIdPk = mockValues.nextString(100);

		TblUserMasterEntity tblUserMasterEntity = new TblUserMasterEntity();
		tblUserMasterEntity.setUserIdPk(userIdPk);
		return tblUserMasterEntity;
	}
	
}
