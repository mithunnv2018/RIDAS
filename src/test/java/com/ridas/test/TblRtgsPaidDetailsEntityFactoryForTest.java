package com.ridas.test;

import com.ridas.bean.jpa.TblRtgsPaidDetailsEntity;

public class TblRtgsPaidDetailsEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblRtgsPaidDetailsEntity newTblRtgsPaidDetailsEntity() {

		Integer rtgsDetailsId = mockValues.nextInteger();

		TblRtgsPaidDetailsEntity tblRtgsPaidDetailsEntity = new TblRtgsPaidDetailsEntity();
		tblRtgsPaidDetailsEntity.setRtgsDetailsId(rtgsDetailsId);
		return tblRtgsPaidDetailsEntity;
	}
	
}
