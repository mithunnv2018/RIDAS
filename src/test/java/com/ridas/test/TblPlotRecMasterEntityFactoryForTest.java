package com.ridas.test;

import com.ridas.bean.jpa.TblPlotRecMasterEntity;

public class TblPlotRecMasterEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblPlotRecMasterEntity newTblPlotRecMasterEntity() {

		Integer plotReceiptId = mockValues.nextInteger();

		TblPlotRecMasterEntity tblPlotRecMasterEntity = new TblPlotRecMasterEntity();
		tblPlotRecMasterEntity.setPlotReceiptId(plotReceiptId);
		return tblPlotRecMasterEntity;
	}
	
}
