/*
 * Created on 18 Mar 2016 ( Time 11:54:41 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.web.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

//done as on 2 feb 2016
//ADD THIS CODE.
import javax.servlet.http.HttpServletResponse;
import java.io.File;
//--- Common classes
import com.ridas.web.common.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Spring MVC controller for Security management.
 */
@Controller
@RequestMapping("/user")

public class UserLoginController extends AbstractController {

	static String MAIN_ENTITY_NAME="";
/**
	 * Default constructor
	 */
	public UserLoginController() {
		super(UserLoginController.class, MAIN_ENTITY_NAME );
		log("UserLoginController created.");
	}
	
  	@RequestMapping(value="/login")
	 public String doLogin(){
   		return "loginpage";
 	 }
}