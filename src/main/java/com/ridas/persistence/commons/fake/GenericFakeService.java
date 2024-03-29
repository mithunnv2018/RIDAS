/*
 * Created on 18 Mar 2016 ( Date ISO 2016-03-18 - Time 11:55:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.persistence.commons.fake;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Generic FAKE service operations (provided by Telosys Tools)
 * 
 * @param <T> entity class 
 * 
 */
public abstract class GenericFakeService<T> {

	private final Class<T> entityClass ;
	
	private char  currentChar      = 'z' ; // next is 'A'
	
	private byte  byteValue        = 0 ;
	private short shortValue       = 0 ;
	private int   intValue         = 0 ;
	private int   longValue        = 0 ;
	
	private float  floatValue      = 0.0f ;
	private double doubleValue     = 0.0 ;
	
	private long  bigDecimalValue  = 0 ;
	
	protected static final byte[] VOID_BYTES_ARRAY = new byte[0] ;
		
	protected GenericFakeService(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	protected void log(String msg) {
		System.out.println("[LOG-PersistenceFake-" + entityClass.getSimpleName() + " ] : " + msg );
	}
	
	protected String nextString() {
		
		if ( currentChar < 'Z' ) {
			currentChar++ ;
		}
		else {
			currentChar = 'A';
		}
		char[] chars = new char [1]  ;
		chars[0] = currentChar ;
		return new String ( chars );
	}
	
	protected byte nextByte() {
		if ( byteValue >= 99 ) {
			byteValue = 0 ;
		}
		return ++byteValue;
	}

	protected short nextShort() {
		if ( shortValue >= 999 ) {
			shortValue = 0 ;
		}
		return ++shortValue;
	}

	protected int nextInteger() {
		if ( intValue >= 999999 ) {
			intValue = 0 ;
		}
		return ++intValue;
	}
	
	protected long nextLong() {
		if ( longValue >= 999999 ) {
			longValue = 0 ;
		}
		return ++longValue;
	}
	
	protected float nextFloat() {
		if ( floatValue >= 999999.00f ) {
			floatValue = 0.0f ;
		}
		return ++floatValue;
	}
	
	protected double nextDouble() {
		if ( doubleValue >= 999999.00 ) {
			doubleValue = 0.0 ;
		}
		return ++doubleValue;
	}
	
	protected BigDecimal nextBigDecimal() {
		if ( bigDecimalValue == Long.MAX_VALUE ) {
			bigDecimalValue = 0 ;
		}
		return new BigDecimal ( ++bigDecimalValue ) ;
	}
	
	protected abstract T buildEntity(int index) ;
	
	protected List<T> buildList(int count) {
		List<T> list = new LinkedList<T>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( buildEntity(i) );
		}
		return list;
	}

	protected java.util.Date nextDate() {
		return new java.util.Date();
	}
	
	protected java.sql.Date nextSqlDate() {
		return new java.sql.Date( (new java.util.Date()).getTime() );
	}
	
	protected java.sql.Time nextSqlTime() {
		return new java.sql.Time( (new java.util.Date()).getTime() );
	}
	
	protected java.sql.Timestamp nextSqlTimestamp() {
		return new java.sql.Timestamp( (new java.util.Date()).getTime() );
	}
	

}
