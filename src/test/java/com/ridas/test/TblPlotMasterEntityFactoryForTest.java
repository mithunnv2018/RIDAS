package com.ridas.test;

import com.ridas.bean.jpa.TblPlotMasterEntity;

public class TblPlotMasterEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblPlotMasterEntity newTblPlotMasterEntity() {

		Integer plotPk = mockValues.nextInteger();

		TblPlotMasterEntity tblPlotMasterEntity = new TblPlotMasterEntity();
		tblPlotMasterEntity.setPlotPk(plotPk);
		return tblPlotMasterEntity;
	}
	
}
