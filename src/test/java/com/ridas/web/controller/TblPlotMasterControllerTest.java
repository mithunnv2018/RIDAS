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
import com.ridas.bean.TblPlotMaster;
import com.ridas.bean.TblMembershipForm;
import com.ridas.test.TblPlotMasterFactoryForTest;
import com.ridas.test.TblMembershipFormFactoryForTest;

//--- Services 
import com.ridas.business.service.TblPlotMasterService;
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
public class TblPlotMasterControllerTest {
	
	@InjectMocks
	private TblPlotMasterController tblPlotMasterController;
	@Mock
	private TblPlotMasterService tblPlotMasterService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private TblMembershipFormService tblMembershipFormService; // Injected by Spring

	private TblPlotMasterFactoryForTest tblPlotMasterFactoryForTest = new TblPlotMasterFactoryForTest();
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
		
		List<TblPlotMaster> list = new ArrayList<TblPlotMaster>();
		when(tblPlotMasterService.findAll()).thenReturn(list);
		
		// When
		String viewName = tblPlotMasterController.list(model);
		
		// Then
		assertEquals("tblPlotMaster/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tblPlotMasterController.formForCreate(model);
		
		// Then
		assertEquals("tblPlotMaster/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((TblPlotMaster)modelMap.get("tblPlotMaster")).getPlotPk());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblPlotMaster/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblPlotMaster tblPlotMaster = tblPlotMasterFactoryForTest.newTblPlotMaster();
		Integer plotPk = tblPlotMaster.getPlotPk();
		when(tblPlotMasterService.findById(plotPk)).thenReturn(tblPlotMaster);
		
		// When
		String viewName = tblPlotMasterController.formForUpdate(model, plotPk);
		
		// Then
		assertEquals("tblPlotMaster/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotMaster, (TblPlotMaster) modelMap.get("tblPlotMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblPlotMaster/update", modelMap.get("saveAction"));
		
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblPlotMaster tblPlotMaster = tblPlotMasterFactoryForTest.newTblPlotMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblPlotMaster tblPlotMasterCreated = new TblPlotMaster();
		when(tblPlotMasterService.create(tblPlotMaster)).thenReturn(tblPlotMasterCreated); 
		
		// When
		String viewName = tblPlotMasterController.create(tblPlotMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblPlotMaster/form/"+tblPlotMaster.getPlotPk(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotMasterCreated, (TblPlotMaster) modelMap.get("tblPlotMaster"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblPlotMaster tblPlotMaster = tblPlotMasterFactoryForTest.newTblPlotMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblPlotMasterController.create(tblPlotMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblPlotMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotMaster, (TblPlotMaster) modelMap.get("tblPlotMaster"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblPlotMaster/create", modelMap.get("saveAction"));
		
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

		TblPlotMaster tblPlotMaster = tblPlotMasterFactoryForTest.newTblPlotMaster();
		
		Exception exception = new RuntimeException("test exception");
		when(tblPlotMasterService.create(tblPlotMaster)).thenThrow(exception);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblPlotMasterController.create(tblPlotMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblPlotMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotMaster, (TblPlotMaster) modelMap.get("tblPlotMaster"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblPlotMaster/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblPlotMaster.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblPlotMaster tblPlotMaster = tblPlotMasterFactoryForTest.newTblPlotMaster();
		Integer plotPk = tblPlotMaster.getPlotPk();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblPlotMaster tblPlotMasterSaved = new TblPlotMaster();
		tblPlotMasterSaved.setPlotPk(plotPk);
		when(tblPlotMasterService.update(tblPlotMaster)).thenReturn(tblPlotMasterSaved); 

		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblPlotMasterController.update(tblPlotMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblPlotMaster/form/"+tblPlotMaster.getPlotPk(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotMasterSaved, (TblPlotMaster) modelMap.get("tblPlotMaster"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblPlotMaster tblPlotMaster = tblPlotMasterFactoryForTest.newTblPlotMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblPlotMasterController.update(tblPlotMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblPlotMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotMaster, (TblPlotMaster) modelMap.get("tblPlotMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblPlotMaster/update", modelMap.get("saveAction"));
		
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

		TblPlotMaster tblPlotMaster = tblPlotMasterFactoryForTest.newTblPlotMaster();
		
		Exception exception = new RuntimeException("test exception");
		when(tblPlotMasterService.update(tblPlotMaster)).thenThrow(exception);
	
		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblPlotMasterController.update(tblPlotMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblPlotMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotMaster, (TblPlotMaster) modelMap.get("tblPlotMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblPlotMaster/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblPlotMaster.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblPlotMaster tblPlotMaster = tblPlotMasterFactoryForTest.newTblPlotMaster();
		Integer plotPk = tblPlotMaster.getPlotPk();
		
		// When
		String viewName = tblPlotMasterController.delete(redirectAttributes, plotPk);
		
		// Then
		verify(tblPlotMasterService).delete(plotPk);
		assertEquals("redirect:/tblPlotMaster", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblPlotMaster tblPlotMaster = tblPlotMasterFactoryForTest.newTblPlotMaster();
		Integer plotPk = tblPlotMaster.getPlotPk();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tblPlotMasterService).delete(plotPk);
		
		// When
		String viewName = tblPlotMasterController.delete(redirectAttributes, plotPk);
		
		// Then
		verify(tblPlotMasterService).delete(plotPk);
		assertEquals("redirect:/tblPlotMaster", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tblPlotMaster.error.delete", exception);
	}
	
	
}
