package com.ridas.test;

import com.ridas.bean.TblPlotMaster;

public class TblPlotMasterFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblPlotMaster newTblPlotMaster() {

		Integer plotPk = mockValues.nextInteger();

		TblPlotMaster tblPlotMaster = new TblPlotMaster();
		tblPlotMaster.setPlotPk(plotPk);
		return tblPlotMaster;
	}
	
}
