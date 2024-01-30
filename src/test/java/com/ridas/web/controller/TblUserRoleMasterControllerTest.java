package com.ridas.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//--- Entities
import com.ridas.bean.TblUserRoleMaster;
import com.ridas.test.TblUserRoleMasterFactoryForTest;

//--- Services 
import com.ridas.business.service.TblUserRoleMasterService;


import com.ridas.web.common.Message;
import com.ridas.web.common.MessageHelper;
import com.ridas.web.common.MessageType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//done as on 2 feb 2016
import javax.servlet.http.HttpServletResponse;


@RunWith(MockitoJUnitRunner.class)
public class TblUserRoleMasterControllerTest {
	
	@InjectMocks
	private TblUserRoleMasterController tblUserRoleMasterController;
	@Mock
	private TblUserRoleMasterService tblUserRoleMasterService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private TblUserRoleMasterFactoryForTest tblUserRoleMasterFactoryForTest = new TblUserRoleMasterFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<TblUserRoleMaster> list = new ArrayList<TblUserRoleMaster>();
		when(tblUserRoleMasterService.findAll()).thenReturn(list);
		
		// When
		String viewName = tblUserRoleMasterController.list(model);
		
		// Then
		assertEquals("tblUserRoleMaster/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tblUserRoleMasterController.formForCreate(model);
		
		// Then
		assertEquals("tblUserRoleMaster/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((TblUserRoleMaster)modelMap.get("tblUserRoleMaster")).getUserRoleId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblUserRoleMaster/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblUserRoleMaster tblUserRoleMaster = tblUserRoleMasterFactoryForTest.newTblUserRoleMaster();
		String userRoleId = tblUserRoleMaster.getUserRoleId();
		when(tblUserRoleMasterService.findById(userRoleId)).thenReturn(tblUserRoleMaster);
		
		// When
		String viewName = tblUserRoleMasterController.formForUpdate(model, userRoleId);
		
		// Then
		assertEquals("tblUserRoleMaster/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleMaster, (TblUserRoleMaster) modelMap.get("tblUserRoleMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblUserRoleMaster/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblUserRoleMaster tblUserRoleMaster = tblUserRoleMasterFactoryForTest.newTblUserRoleMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblUserRoleMaster tblUserRoleMasterCreated = new TblUserRoleMaster();
		when(tblUserRoleMasterService.create(tblUserRoleMaster)).thenReturn(tblUserRoleMasterCreated); 
		
		// When
		String viewName = tblUserRoleMasterController.create(tblUserRoleMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblUserRoleMaster/form/"+tblUserRoleMaster.getUserRoleId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleMasterCreated, (TblUserRoleMaster) modelMap.get("tblUserRoleMaster"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblUserRoleMaster tblUserRoleMaster = tblUserRoleMasterFactoryForTest.newTblUserRoleMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblUserRoleMasterController.create(tblUserRoleMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblUserRoleMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleMaster, (TblUserRoleMaster) modelMap.get("tblUserRoleMaster"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblUserRoleMaster/create", modelMap.get("saveAction"));
		
	}

	@Test
	public void createException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		TblUserRoleMaster tblUserRoleMaster = tblUserRoleMasterFactoryForTest.newTblUserRoleMaster();
		
		Exception exception = new RuntimeException("test exception");
		when(tblUserRoleMasterService.create(tblUserRoleMaster)).thenThrow(exception);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblUserRoleMasterController.create(tblUserRoleMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblUserRoleMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleMaster, (TblUserRoleMaster) modelMap.get("tblUserRoleMaster"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblUserRoleMaster/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblUserRoleMaster.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblUserRoleMaster tblUserRoleMaster = tblUserRoleMasterFactoryForTest.newTblUserRoleMaster();
		String userRoleId = tblUserRoleMaster.getUserRoleId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblUserRoleMaster tblUserRoleMasterSaved = new TblUserRoleMaster();
		tblUserRoleMasterSaved.setUserRoleId(userRoleId);
		when(tblUserRoleMasterService.update(tblUserRoleMaster)).thenReturn(tblUserRoleMasterSaved); 

		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblUserRoleMasterController.update(tblUserRoleMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblUserRoleMaster/form/"+tblUserRoleMaster.getUserRoleId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleMasterSaved, (TblUserRoleMaster) modelMap.get("tblUserRoleMaster"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblUserRoleMaster tblUserRoleMaster = tblUserRoleMasterFactoryForTest.newTblUserRoleMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblUserRoleMasterController.update(tblUserRoleMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblUserRoleMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleMaster, (TblUserRoleMaster) modelMap.get("tblUserRoleMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblUserRoleMaster/update", modelMap.get("saveAction"));
		
	}

	@Test
	public void updateException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		TblUserRoleMaster tblUserRoleMaster = tblUserRoleMasterFactoryForTest.newTblUserRoleMaster();
		
		Exception exception = new RuntimeException("test exception");
		when(tblUserRoleMasterService.update(tblUserRoleMaster)).thenThrow(exception);
	
		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblUserRoleMasterController.update(tblUserRoleMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblUserRoleMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleMaster, (TblUserRoleMaster) modelMap.get("tblUserRoleMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblUserRoleMaster/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblUserRoleMaster.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblUserRoleMaster tblUserRoleMaster = tblUserRoleMasterFactoryForTest.newTblUserRoleMaster();
		String userRoleId = tblUserRoleMaster.getUserRoleId();
		
		// When
		String viewName = tblUserRoleMasterController.delete(redirectAttributes, userRoleId);
		
		// Then
		verify(tblUserRoleMasterService).delete(userRoleId);
		assertEquals("redirect:/tblUserRoleMaster", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblUserRoleMaster tblUserRoleMaster = tblUserRoleMasterFactoryForTest.newTblUserRoleMaster();
		String userRoleId = tblUserRoleMaster.getUserRoleId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tblUserRoleMasterService).delete(userRoleId);
		
		// When
		String viewName = tblUserRoleMasterController.delete(redirectAttributes, userRoleId);
		
		// Then
		verify(tblUserRoleMasterService).delete(userRoleId);
		assertEquals("redirect:/tblUserRoleMaster", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tblUserRoleMaster.error.delete", exception);
	}
	
	
}
