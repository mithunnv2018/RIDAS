/*
 * Created on 18 Mar 2016 ( Time 11:54:41 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.rest.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception handler for Rest controllers.
 */
@ControllerAdvice
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GlobalRestControllerExceptionHandler {
	
	/** Logger */
	private final Logger logger = LoggerFactory.getLogger(GlobalRestControllerExceptionHandler.class);
	
	/**
	 * Handle error.
	 * @param exception Exception
	 * @return Errors map
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Map handleException(Exception exception) {
		logger.error("error", exception);
		return getExceptionAsMap(exception, new ArrayList<Throwable>());
	}
	
	/**
	 * Get exceptions as errors map with error message, stacktrace and exception cause.
	 * @param exception Exception 
	 * @param exceptions Exceptions listed in this errors map
	 * @return Errors map
	 */
	private Map getExceptionAsMap(Throwable exception, List<Throwable> exceptions) {
		Map exceptionAsMap = new HashMap();
		exceptionAsMap.put("message", exception.getMessage());
		// exceptionAsMap.put("trace", exception.getStackTrace());
		
		if(exception.getCause() != null && !exceptions.contains(exception)) {
			exceptions.add(exception);
			Map causeAsMap = getExceptionAsMap(exception.getCause(), exceptions);
			exceptionAsMap.put("cause", causeAsMap);
		}
		
		return exceptionAsMap;
	}
	
}