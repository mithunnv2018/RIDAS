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
import com.ridas.bean.TblPlotRecMaster;
import com.ridas.bean.TblReceiptMaster;
import com.ridas.bean.TblPlotMaster;
import com.ridas.test.TblPlotRecMasterFactoryForTest;
import com.ridas.test.TblReceiptMasterFactoryForTest;
import com.ridas.test.TblPlotMasterFactoryForTest;

//--- Services 
import com.ridas.business.service.TblPlotRecMasterService;
import com.ridas.business.service.TblReceiptMasterService;
import com.ridas.business.service.TblPlotMasterService;

//--- List Items 
import com.ridas.web.listitem.TblReceiptMasterListItem;
import com.ridas.web.listitem.TblPlotMasterListItem;

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
public class TblPlotRecMasterControllerTest {
	
	@InjectMocks
	private TblPlotRecMasterController tblPlotRecMasterController;
	@Mock
	private TblPlotRecMasterService tblPlotRecMasterService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private TblReceiptMasterService tblReceiptMasterService; // Injected by Spring
	@Mock
	private TblPlotMasterService tblPlotMasterService; // Injected by Spring

	private TblPlotRecMasterFactoryForTest tblPlotRecMasterFactoryForTest = new TblPlotRecMasterFactoryForTest();
	private TblReceiptMasterFactoryForTest tblReceiptMasterFactoryForTest = new TblReceiptMasterFactoryForTest();
	private TblPlotMasterFactoryForTest tblPlotMasterFactoryForTest = new TblPlotMasterFactoryForTest();

	List<TblReceiptMaster> tblReceiptMasters = new ArrayList<TblReceiptMaster>();
	List<TblPlotMaster> tblPlotMasters = new ArrayList<TblPlotMaster>();

	private void givenPopulateModel() {
		TblReceiptMaster tblReceiptMaster1 = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		TblReceiptMaster tblReceiptMaster2 = tblReceiptMasterFactoryForTest.newTblReceiptMaster();
		List<TblReceiptMaster> tblReceiptMasters = new ArrayList<TblReceiptMaster>();
		tblReceiptMasters.add(tblReceiptMaster1);
		tblReceiptMasters.add(tblReceiptMaster2);
		when(tblReceiptMasterService.findAll()).thenReturn(tblReceiptMasters);

		TblPlotMaster tblPlotMaster1 = tblPlotMasterFactoryForTest.newTblPlotMaster();
		TblPlotMaster tblPlotMaster2 = tblPlotMasterFactoryForTest.newTblPlotMaster();
		List<TblPlotMaster> tblPlotMasters = new ArrayList<TblPlotMaster>();
		tblPlotMasters.add(tblPlotMaster1);
		tblPlotMasters.add(tblPlotMaster2);
		when(tblPlotMasterService.findAll()).thenReturn(tblPlotMasters);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<TblPlotRecMaster> list = new ArrayList<TblPlotRecMaster>();
		when(tblPlotRecMasterService.findAll()).thenReturn(list);
		
		// When
		String viewName = tblPlotRecMasterController.list(model);
		
		// Then
		assertEquals("tblPlotRecMaster/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tblPlotRecMasterController.formForCreate(model);
		
		// Then
		assertEquals("tblPlotRecMaster/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((TblPlotRecMaster)modelMap.get("tblPlotRecMaster")).getPlotReceiptId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblPlotRecMaster/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblPlotMasterListItem> tblPlotMasterListItems = (List<TblPlotMasterListItem>) modelMap.get("listOfTblPlotMasterItems");
		assertEquals(2, tblPlotMasterListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblPlotRecMaster tblPlotRecMaster = tblPlotRecMasterFactoryForTest.newTblPlotRecMaster();
		Integer plotReceiptId = tblPlotRecMaster.getPlotReceiptId();
		when(tblPlotRecMasterService.findById(plotReceiptId)).thenReturn(tblPlotRecMaster);
		
		// When
		String viewName = tblPlotRecMasterController.formForUpdate(model, plotReceiptId);
		
		// Then
		assertEquals("tblPlotRecMaster/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotRecMaster, (TblPlotRecMaster) modelMap.get("tblPlotRecMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblPlotRecMaster/update", modelMap.get("saveAction"));
		
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
		List<TblPlotMasterListItem> tblPlotMasterListItems = (List<TblPlotMasterListItem>) modelMap.get("listOfTblPlotMasterItems");
		assertEquals(2, tblPlotMasterListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblPlotRecMaster tblPlotRecMaster = tblPlotRecMasterFactoryForTest.newTblPlotRecMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblPlotRecMaster tblPlotRecMasterCreated = new TblPlotRecMaster();
		when(tblPlotRecMasterService.create(tblPlotRecMaster)).thenReturn(tblPlotRecMasterCreated); 
		
		// When
		String viewName = tblPlotRecMasterController.create(tblPlotRecMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblPlotRecMaster/form/"+tblPlotRecMaster.getPlotReceiptId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotRecMasterCreated, (TblPlotRecMaster) modelMap.get("tblPlotRecMaster"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblPlotRecMaster tblPlotRecMaster = tblPlotRecMasterFactoryForTest.newTblPlotRecMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblPlotRecMasterController.create(tblPlotRecMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblPlotRecMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotRecMaster, (TblPlotRecMaster) modelMap.get("tblPlotRecMaster"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblPlotRecMaster/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblPlotMasterListItem> tblPlotMasterListItems = (List<TblPlotMasterListItem>) modelMap.get("listOfTblPlotMasterItems");
		assertEquals(2, tblPlotMasterListItems.size());
		
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

		TblPlotRecMaster tblPlotRecMaster = tblPlotRecMasterFactoryForTest.newTblPlotRecMaster();
		
		Exception exception = new RuntimeException("test exception");
		when(tblPlotRecMasterService.create(tblPlotRecMaster)).thenThrow(exception);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblPlotRecMasterController.create(tblPlotRecMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblPlotRecMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotRecMaster, (TblPlotRecMaster) modelMap.get("tblPlotRecMaster"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblPlotRecMaster/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblPlotRecMaster.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblPlotMasterListItem> tblPlotMasterListItems = (List<TblPlotMasterListItem>) modelMap.get("listOfTblPlotMasterItems");
		assertEquals(2, tblPlotMasterListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblPlotRecMaster tblPlotRecMaster = tblPlotRecMasterFactoryForTest.newTblPlotRecMaster();
		Integer plotReceiptId = tblPlotRecMaster.getPlotReceiptId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblPlotRecMaster tblPlotRecMasterSaved = new TblPlotRecMaster();
		tblPlotRecMasterSaved.setPlotReceiptId(plotReceiptId);
		when(tblPlotRecMasterService.update(tblPlotRecMaster)).thenReturn(tblPlotRecMasterSaved); 

		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblPlotRecMasterController.update(tblPlotRecMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblPlotRecMaster/form/"+tblPlotRecMaster.getPlotReceiptId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotRecMasterSaved, (TblPlotRecMaster) modelMap.get("tblPlotRecMaster"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblPlotRecMaster tblPlotRecMaster = tblPlotRecMasterFactoryForTest.newTblPlotRecMaster();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblPlotRecMasterController.update(tblPlotRecMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblPlotRecMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotRecMaster, (TblPlotRecMaster) modelMap.get("tblPlotRecMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblPlotRecMaster/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblPlotMasterListItem> tblPlotMasterListItems = (List<TblPlotMasterListItem>) modelMap.get("listOfTblPlotMasterItems");
		assertEquals(2, tblPlotMasterListItems.size());
		
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

		TblPlotRecMaster tblPlotRecMaster = tblPlotRecMasterFactoryForTest.newTblPlotRecMaster();
		
		Exception exception = new RuntimeException("test exception");
		when(tblPlotRecMasterService.update(tblPlotRecMaster)).thenThrow(exception);
	
		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblPlotRecMasterController.update(tblPlotRecMaster, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblPlotRecMaster/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblPlotRecMaster, (TblPlotRecMaster) modelMap.get("tblPlotRecMaster"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblPlotRecMaster/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblPlotRecMaster.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblPlotMasterListItem> tblPlotMasterListItems = (List<TblPlotMasterListItem>) modelMap.get("listOfTblPlotMasterItems");
		assertEquals(2, tblPlotMasterListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblPlotRecMaster tblPlotRecMaster = tblPlotRecMasterFactoryForTest.newTblPlotRecMaster();
		Integer plotReceiptId = tblPlotRecMaster.getPlotReceiptId();
		
		// When
		String viewName = tblPlotRecMasterController.delete(redirectAttributes, plotReceiptId);
		
		// Then
		verify(tblPlotRecMasterService).delete(plotReceiptId);
		assertEquals("redirect:/tblPlotRecMaster", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblPlotRecMaster tblPlotRecMaster = tblPlotRecMasterFactoryForTest.newTblPlotRecMaster();
		Integer plotReceiptId = tblPlotRecMaster.getPlotReceiptId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tblPlotRecMasterService).delete(plotReceiptId);
		
		// When
		String viewName = tblPlotRecMasterController.delete(redirectAttributes, plotReceiptId);
		
		// Then
		verify(tblPlotRecMasterService).delete(plotReceiptId);
		assertEquals("redirect:/tblPlotRecMaster", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tblPlotRecMaster.error.delete", exception);
	}
	
	
}
