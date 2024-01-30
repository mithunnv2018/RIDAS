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
import com.ridas.bean.TblUserMaster;
import com.ridas.test.TblUserMasterFactoryForTest;

//--- Services 
import com.ridas.business.service.TblUserMasterService;


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
public class TblUserMasterControllerTest {
	
	@InjectMocks
	private TblUserMasterController tblUserMasterController;
	@Mock
	private TblUserMasterService tblUserMasterService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private TblUserMasterFactoryForTest tblUserMasterFactoryForTest = new TblUserMasterFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<TblUserMaster> list = new ArrayList<TblUserMaster>();
		when(tblUserMasterService.findAll()).thenReturn(list);
		
		// When
		String viewName = tblUserMasterController.list(model);
		
		// Then
		assertEquals("tblUserMaster/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tblUserMasterController.formForCreate(model);
		
		// Then
		assertEquals("tblUserMaster/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((TblUserMaster)modelMap.get("tblUserMaster")).getUserIdPk());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblUserMaster/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblUserMaster tblUserMaster = tblUserMasterFactoryForTest.newTblUserMaster();
		String userIdPk = tblUserMaster.getUserIdPk();
		when(tblUserMasterService.findById(userIdPk)).thenReturn(tblUserMaster);
		
		// When
		String viewName = tblUserMasterController.formForUpdate(model, userIdPk);
		
		// Then
		assertEquals("tblUserMaster/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserMaster, (TblUserMaster) modelMap.get("tblUserMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblUserMaster/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblUserMaster tblUserMaster = tblUserMasterFactoryForTest.newTblUserMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblUserMaster tblUserMasterCreated = new TblUserMaster();
		when(tblUserMasterService.create(tblUserMaster)).thenReturn(tblUserMasterCreated); 
		
		// When
		String viewName = tblUserMasterController.create(tblUserMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblUserMaster/form/"+tblUserMaster.getUserIdPk(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserMasterCreated, (TblUserMaster) modelMap.get("tblUserMaster"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblUserMaster tblUserMaster = tblUserMasterFactoryForTest.newTblUserMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblUserMasterController.create(tblUserMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblUserMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserMaster, (TblUserMaster) modelMap.get("tblUserMaster"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblUserMaster/create", modelMap.get("saveAction"));
		
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

		TblUserMaster tblUserMaster = tblUserMasterFactoryForTest.newTblUserMaster();
		
		Exception exception = new RuntimeException("test exception");
		when(tblUserMasterService.create(tblUserMaster)).thenThrow(exception);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblUserMasterController.create(tblUserMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblUserMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserMaster, (TblUserMaster) modelMap.get("tblUserMaster"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblUserMaster/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblUserMaster.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblUserMaster tblUserMaster = tblUserMasterFactoryForTest.newTblUserMaster();
		String userIdPk = tblUserMaster.getUserIdPk();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblUserMaster tblUserMasterSaved = new TblUserMaster();
		tblUserMasterSaved.setUserIdPk(userIdPk);
		when(tblUserMasterService.update(tblUserMaster)).thenReturn(tblUserMasterSaved); 

		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblUserMasterController.update(tblUserMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblUserMaster/form/"+tblUserMaster.getUserIdPk(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserMasterSaved, (TblUserMaster) modelMap.get("tblUserMaster"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblUserMaster tblUserMaster = tblUserMasterFactoryForTest.newTblUserMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblUserMasterController.update(tblUserMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblUserMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserMaster, (TblUserMaster) modelMap.get("tblUserMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblUserMaster/update", modelMap.get("saveAction"));
		
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

		TblUserMaster tblUserMaster = tblUserMasterFactoryForTest.newTblUserMaster();
		
		Exception exception = new RuntimeException("test exception");
		when(tblUserMasterService.update(tblUserMaster)).thenThrow(exception);
	
		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblUserMasterController.update(tblUserMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblUserMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserMaster, (TblUserMaster) modelMap.get("tblUserMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblUserMaster/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblUserMaster.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblUserMaster tblUserMaster = tblUserMasterFactoryForTest.newTblUserMaster();
		String userIdPk = tblUserMaster.getUserIdPk();
		
		// When
		String viewName = tblUserMasterController.delete(redirectAttributes, userIdPk);
		
		// Then
		verify(tblUserMasterService).delete(userIdPk);
		assertEquals("redirect:/tblUserMaster", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblUserMaster tblUserMaster = tblUserMasterFactoryForTest.newTblUserMaster();
		String userIdPk = tblUserMaster.getUserIdPk();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tblUserMasterService).delete(userIdPk);
		
		// When
		String viewName = tblUserMasterController.delete(redirectAttributes, userIdPk);
		
		// Then
		verify(tblUserMasterService).delete(userIdPk);
		assertEquals("redirect:/tblUserMaster", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tblUserMaster.error.delete", exception);
	}
	
	
}
