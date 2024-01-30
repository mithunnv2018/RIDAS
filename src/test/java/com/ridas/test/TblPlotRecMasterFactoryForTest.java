package com.ridas.test;

import com.ridas.bean.TblPlotRecMaster;

public class TblPlotRecMasterFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblPlotRecMaster newTblPlotRecMaster() {

		Integer plotReceiptId = mockValues.nextInteger();

		TblPlotRecMaster tblPlotRecMaster = new TblPlotRecMaster();
		tblPlotRecMaster.setPlotReceiptId(plotReceiptId);
		return tblPlotRecMaster;
	}
	
}
