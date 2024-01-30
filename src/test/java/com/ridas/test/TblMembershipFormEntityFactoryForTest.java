package com.ridas.test;

import com.ridas.bean.jpa.TblMembershipFormEntity;

public class TblMembershipFormEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblMembershipFormEntity newTblMembershipFormEntity() {

		String memberId = mockValues.nextString(50);

		TblMembershipFormEntity tblMembershipFormEntity = new TblMembershipFormEntity();
		tblMembershipFormEntity.setMemberId(memberId);
		return tblMembershipFormEntity;
	}
	
}
