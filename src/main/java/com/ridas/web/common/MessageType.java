/*
 * Created on 18 Mar 2016 ( Time 11:54:41 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.web.common;

public enum MessageType {
	
	SUCCESS,
	INFO,
	WARNING,
	DANGER;
	
	public String getCss() {
		return name().toLowerCase();
	}
	
}
