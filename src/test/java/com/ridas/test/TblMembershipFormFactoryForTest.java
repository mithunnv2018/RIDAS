package com.ridas.test;

import com.ridas.bean.TblMembershipForm;

public class TblMembershipFormFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblMembershipForm newTblMembershipForm() {

		String memberId = mockValues.nextString(50);

		TblMembershipForm tblMembershipForm = new TblMembershipForm();
		tblMembershipForm.setMemberId(memberId);
		return tblMembershipForm;
	}
	
}
