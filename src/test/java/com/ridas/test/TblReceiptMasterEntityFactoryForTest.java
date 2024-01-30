package com.ridas.test;

import com.ridas.bean.jpa.TblReceiptMasterEntity;

public class TblReceiptMasterEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblReceiptMasterEntity newTblReceiptMasterEntity() {

		String receiptPk = mockValues.nextString(50);

		TblReceiptMasterEntity tblReceiptMasterEntity = new TblReceiptMasterEntity();
		tblReceiptMasterEntity.setReceiptPk(receiptPk);
		return tblReceiptMasterEntity;
	}
	
}
