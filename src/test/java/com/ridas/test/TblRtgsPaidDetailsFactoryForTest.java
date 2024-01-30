package com.ridas.test;

import com.ridas.bean.TblRtgsPaidDetails;

public class TblRtgsPaidDetailsFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblRtgsPaidDetails newTblRtgsPaidDetails() {

		Integer rtgsDetailsId = mockValues.nextInteger();

		TblRtgsPaidDetails tblRtgsPaidDetails = new TblRtgsPaidDetails();
		tblRtgsPaidDetails.setRtgsDetailsId(rtgsDetailsId);
		return tblRtgsPaidDetails;
	}
	
}
