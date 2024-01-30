/*
 * Created on 18 Mar 2016 ( Time 11:54:32 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.web.listitem;

import com.ridas.bean.TblRtgsPaidDetails;
import com.ridas.web.common.ListItem;

public class TblRtgsPaidDetailsListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public TblRtgsPaidDetailsListItem(TblRtgsPaidDetails tblRtgsPaidDetails) {
		super();

		this.value = ""
			 + tblRtgsPaidDetails.getRtgsDetailsId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = tblRtgsPaidDetails.toString();
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getLabel() {
		return label;
	}

}