package com.ridas.test;

import com.ridas.bean.TblBusiAssoMaster;

public class TblBusiAssoMasterFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblBusiAssoMaster newTblBusiAssoMaster() {

		String buAsId = mockValues.nextString(50);

		TblBusiAssoMaster tblBusiAssoMaster = new TblBusiAssoMaster();
		tblBusiAssoMaster.setBuAsId(buAsId);
		return tblBusiAssoMaster;
	}
	
}
