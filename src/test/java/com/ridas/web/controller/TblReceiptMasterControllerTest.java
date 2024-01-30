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
import com.ridas.bean.TblReceiptMaster;
import com.ridas.bean.TblMembershipForm;
import com.ridas.test.TblReceiptMasterFactoryForTest;
import com.ridas.test.TblMembershipFormFactoryForTest;

//--- Services 
import com.ridas.business.service.TblReceiptMasterService;
import com.ridas.business.service.TblMembershipFormService;

//--- List Items 
import com.ridas.web.listitem.TblMembershipFormListItem;

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
public class TblReceiptMasterControllerTest {
	
	@InjectMocks
	private TblReceiptMasterController tblReceiptMasterController;
	@Mock
	private TblReceiptMasterService tblReceiptMasterService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private TblMembershipFormService tblMembershipFormService; // Injected by Spring

	private TblReceiptMasterFactoryForTest tblReceiptMasterFactoryForTest = new TblReceiptMasterFactoryForTest();
	private TblMembershipFormFactoryForTest tblMembershipFormFactoryForTest = new TblMembershipFormFactoryForTest();

	List<TblMembershipForm> tblMembershipForms = new ArrayList<TblMembershipForm>();

	private void givenPopulateModel() {
		TblMembershipForm tblMembershipForm1 = tblMembershipFormFactoryForTest.newTblMembershipForm();
		TblMembershipForm tblMembershipForm2 = tblMembershipFormFactoryForTest.newTblMembershipForm();
		List<TblMembershipForm> tblMembershipForms = new ArrayList<TblMembershipForm>();
		tblMembershipForms.add(tblMembershipForm1);
		tblMembershipForms.add(tblMembershipForm2);
		when(tblMembershipFormService.findAll()).thenReturn(tblMembershipForms);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<TblReceiptMaster> list = new ArrayList<TblReceiptMaster>();
		when(tblReceiptMasterService.findAll()).thenReturn(list);
		
		// When
		String viewName = tblReceiptMasterController.list(model);
		
		// Then
		assertEquals("tblReceiptMaster/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tblReceiptMasterController.formForCreate(model);
		
		// Then
		assertEquals("tblReceiptMaster/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((TblReceiptMaster)modelMap.get("tblReceiptMaster")).getReceiptPk());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblReceiptMaster/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblReceiptMaster tblReceiptMaster = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		String receiptPk = tblReceiptMaster.getReceiptPk();
		when(tblReceiptMasterService.findById(receiptPk)).thenReturn(tblReceiptMaster);
		
		// When
		String viewName = tblReceiptMasterController.formForUpdate(model, receiptPk);
		
		// Then
		assertEquals("tblReceiptMaster/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblReceiptMaster, (TblReceiptMaster) modelMap.get("tblReceiptMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblReceiptMaster/update", modelMap.get("saveAction"));
		
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblReceiptMaster tblReceiptMaster = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblReceiptMaster tblReceiptMasterCreated = new TblReceiptMaster();
		when(tblReceiptMasterService.create(tblReceiptMaster)).thenReturn(tblReceiptMasterCreated); 
		
		// When
		String viewName = tblReceiptMasterController.create(tblReceiptMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblReceiptMaster/form/"+tblReceiptMaster.getReceiptPk(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblReceiptMasterCreated, (TblReceiptMaster) modelMap.get("tblReceiptMaster"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblReceiptMaster tblReceiptMaster = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblReceiptMasterController.create(tblReceiptMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblReceiptMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblReceiptMaster, (TblReceiptMaster) modelMap.get("tblReceiptMaster"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblReceiptMaster/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
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

		TblReceiptMaster tblReceiptMaster = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		
		Exception exception = new RuntimeException("test exception");
		when(tblReceiptMasterService.create(tblReceiptMaster)).thenThrow(exception);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblReceiptMasterController.create(tblReceiptMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblReceiptMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblReceiptMaster, (TblReceiptMaster) modelMap.get("tblReceiptMaster"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblReceiptMaster/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblReceiptMaster.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblReceiptMaster tblReceiptMaster = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		String receiptPk = tblReceiptMaster.getReceiptPk();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblReceiptMaster tblReceiptMasterSaved = new TblReceiptMaster();
		tblReceiptMasterSaved.setReceiptPk(receiptPk);
		when(tblReceiptMasterService.update(tblReceiptMaster)).thenReturn(tblReceiptMasterSaved); 

		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblReceiptMasterController.update(tblReceiptMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblReceiptMaster/form/"+tblReceiptMaster.getReceiptPk(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblReceiptMasterSaved, (TblReceiptMaster) modelMap.get("tblReceiptMaster"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblReceiptMaster tblReceiptMaster = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblReceiptMasterController.update(tblReceiptMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblReceiptMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblReceiptMaster, (TblReceiptMaster) modelMap.get("tblReceiptMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblReceiptMaster/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
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

		TblReceiptMaster tblReceiptMaster = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		
		Exception exception = new RuntimeException("test exception");
		when(tblReceiptMasterService.update(tblReceiptMaster)).thenThrow(exception);
	
		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblReceiptMasterController.update(tblReceiptMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblReceiptMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblReceiptMaster, (TblReceiptMaster) modelMap.get("tblReceiptMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblReceiptMaster/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblReceiptMaster.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblReceiptMaster tblReceiptMaster = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		String receiptPk = tblReceiptMaster.getReceiptPk();
		
		// When
		String viewName = tblReceiptMasterController.delete(redirectAttributes, receiptPk);
		
		// Then
		verify(tblReceiptMasterService).delete(receiptPk);
		assertEquals("redirect:/tblReceiptMaster", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblReceiptMaster tblReceiptMaster = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		String receiptPk = tblReceiptMaster.getReceiptPk();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tblReceiptMasterService).delete(receiptPk);
		
		// When
		String viewName = tblReceiptMasterController.delete(redirectAttributes, receiptPk);
		
		// Then
		verify(tblReceiptMasterService).delete(receiptPk);
		assertEquals("redirect:/tblReceiptMaster", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tblReceiptMaster.error.delete", exception);
	}
	
	
}
