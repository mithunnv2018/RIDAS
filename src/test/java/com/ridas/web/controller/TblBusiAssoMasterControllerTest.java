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
import com.ridas.bean.TblBusiAssoMaster;
import com.ridas.bean.TblMembershipForm;
import com.ridas.bean.TblReceiptMaster;
import com.ridas.test.TblBusiAssoMasterFactoryForTest;
import com.ridas.test.TblMembershipFormFactoryForTest;
import com.ridas.test.TblReceiptMasterFactoryForTest;

//--- Services 
import com.ridas.business.service.TblBusiAssoMasterService;
import com.ridas.business.service.TblMembershipFormService;
import com.ridas.business.service.TblReceiptMasterService;

//--- List Items 
import com.ridas.web.listitem.TblMembershipFormListItem;
import com.ridas.web.listitem.TblReceiptMasterListItem;

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
public class TblBusiAssoMasterControllerTest {
	
	@InjectMocks
	private TblBusiAssoMasterController tblBusiAssoMasterController;
	@Mock
	private TblBusiAssoMasterService tblBusiAssoMasterService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private TblMembershipFormService tblMembershipFormService; // Injected by Spring
	@Mock
	private TblReceiptMasterService tblReceiptMasterService; // Injected by Spring

	private TblBusiAssoMasterFactoryForTest tblBusiAssoMasterFactoryForTest = new TblBusiAssoMasterFactoryForTest();
	private TblMembershipFormFactoryForTest tblMembershipFormFactoryForTest = new TblMembershipFormFactoryForTest();
	private TblReceiptMasterFactoryForTest tblReceiptMasterFactoryForTest = new TblReceiptMasterFactoryForTest();

	List<TblMembershipForm> tblMembershipForms = new ArrayList<TblMembershipForm>();
	List<TblReceiptMaster> tblReceiptMasters = new ArrayList<TblReceiptMaster>();

	private void givenPopulateModel() {
		TblMembershipForm tblMembershipForm1 = tblMembershipFormFactoryForTest.newTblMembershipForm();
		TblMembershipForm tblMembershipForm2 = tblMembershipFormFactoryForTest.newTblMembershipForm();
		List<TblMembershipForm> tblMembershipForms = new ArrayList<TblMembershipForm>();
		tblMembershipForms.add(tblMembershipForm1);
		tblMembershipForms.add(tblMembershipForm2);
		when(tblMembershipFormService.findAll()).thenReturn(tblMembershipForms);

		TblReceiptMaster tblReceiptMaster1 = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		TblReceiptMaster tblReceiptMaster2 = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		List<TblReceiptMaster> tblReceiptMasters = new ArrayList<TblReceiptMaster>();
		tblReceiptMasters.add(tblReceiptMaster1);
		tblReceiptMasters.add(tblReceiptMaster2);
		when(tblReceiptMasterService.findAll()).thenReturn(tblReceiptMasters);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<TblBusiAssoMaster> list = new ArrayList<TblBusiAssoMaster>();
		when(tblBusiAssoMasterService.findAll()).thenReturn(list);
		
		// When
		String viewName = tblBusiAssoMasterController.list(model);
		
		// Then
		assertEquals("tblBusiAssoMaster/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tblBusiAssoMasterController.formForCreate(model);
		
		// Then
		assertEquals("tblBusiAssoMaster/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((TblBusiAssoMaster)modelMap.get("tblBusiAssoMaster")).getBuAsId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblBusiAssoMaster/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		String buAsId = tblBusiAssoMaster.getBuAsId();
		when(tblBusiAssoMasterService.findById(buAsId)).thenReturn(tblBusiAssoMaster);
		
		// When
		String viewName = tblBusiAssoMasterController.formForUpdate(model, buAsId);
		
		// Then
		assertEquals("tblBusiAssoMaster/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblBusiAssoMaster, (TblBusiAssoMaster) modelMap.get("tblBusiAssoMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblBusiAssoMaster/update", modelMap.get("saveAction"));
		
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblBusiAssoMaster tblBusiAssoMasterCreated = new TblBusiAssoMaster();
		when(tblBusiAssoMasterService.create(tblBusiAssoMaster)).thenReturn(tblBusiAssoMasterCreated); 
		
		// When
		String viewName = tblBusiAssoMasterController.create(tblBusiAssoMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblBusiAssoMaster/form/"+tblBusiAssoMaster.getBuAsId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblBusiAssoMasterCreated, (TblBusiAssoMaster) modelMap.get("tblBusiAssoMaster"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblBusiAssoMasterController.create(tblBusiAssoMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblBusiAssoMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblBusiAssoMaster, (TblBusiAssoMaster) modelMap.get("tblBusiAssoMaster"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblBusiAssoMaster/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
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

		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		
		Exception exception = new RuntimeException("test exception");
		when(tblBusiAssoMasterService.create(tblBusiAssoMaster)).thenThrow(exception);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblBusiAssoMasterController.create(tblBusiAssoMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblBusiAssoMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblBusiAssoMaster, (TblBusiAssoMaster) modelMap.get("tblBusiAssoMaster"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblBusiAssoMaster/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblBusiAssoMaster.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		String buAsId = tblBusiAssoMaster.getBuAsId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblBusiAssoMaster tblBusiAssoMasterSaved = new TblBusiAssoMaster();
		tblBusiAssoMasterSaved.setBuAsId(buAsId);
		when(tblBusiAssoMasterService.update(tblBusiAssoMaster)).thenReturn(tblBusiAssoMasterSaved); 

		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblBusiAssoMasterController.update(tblBusiAssoMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblBusiAssoMaster/form/"+tblBusiAssoMaster.getBuAsId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblBusiAssoMasterSaved, (TblBusiAssoMaster) modelMap.get("tblBusiAssoMaster"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblBusiAssoMasterController.update(tblBusiAssoMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblBusiAssoMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblBusiAssoMaster, (TblBusiAssoMaster) modelMap.get("tblBusiAssoMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblBusiAssoMaster/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
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

		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		
		Exception exception = new RuntimeException("test exception");
		when(tblBusiAssoMasterService.update(tblBusiAssoMaster)).thenThrow(exception);
	
		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblBusiAssoMasterController.update(tblBusiAssoMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblBusiAssoMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblBusiAssoMaster, (TblBusiAssoMaster) modelMap.get("tblBusiAssoMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblBusiAssoMaster/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblBusiAssoMaster.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		String buAsId = tblBusiAssoMaster.getBuAsId();
		
		// When
		String viewName = tblBusiAssoMasterController.delete(redirectAttributes, buAsId);
		
		// Then
		verify(tblBusiAssoMasterService).delete(buAsId);
		assertEquals("redirect:/tblBusiAssoMaster", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterFactoryForTest.newTblBusiAssoMaster();
		String buAsId = tblBusiAssoMaster.getBuAsId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tblBusiAssoMasterService).delete(buAsId);
		
		// When
		String viewName = tblBusiAssoMasterController.delete(redirectAttributes, buAsId);
		
		// Then
		verify(tblBusiAssoMasterService).delete(buAsId);
		assertEquals("redirect:/tblBusiAssoMaster", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tblBusiAssoMaster.error.delete", exception);
	}
	
	
}
