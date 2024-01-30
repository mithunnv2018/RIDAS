package com.ridas.test;

import com.ridas.bean.jpa.TblBusiAssoMasterEntity;

public class TblBusiAssoMasterEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblBusiAssoMasterEntity newTblBusiAssoMasterEntity() {

		String buAsId = mockValues.nextString(50);

		TblBusiAssoMasterEntity tblBusiAssoMasterEntity = new TblBusiAssoMasterEntity();
		tblBusiAssoMasterEntity.setBuAsId(buAsId);
		return tblBusiAssoMasterEntity;
	}
	
}
