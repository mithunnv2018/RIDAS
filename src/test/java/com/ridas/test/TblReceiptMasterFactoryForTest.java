package com.ridas.test;

import com.ridas.bean.TblReceiptMaster;

public class TblReceiptMasterFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TblReceiptMaster newTblReceiptMaster() {

		String receiptPk = mockValues.nextString(50);

		TblReceiptMaster tblReceiptMaster = new TblReceiptMaster();
		tblReceiptMaster.setReceiptPk(receiptPk);
		return tblReceiptMaster;
	}
	
}
