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
import com.ridas.bean.TblRtgsPaidDetails;
import com.ridas.bean.TblReceiptMaster;
import com.ridas.test.TblRtgsPaidDetailsFactoryForTest;
import com.ridas.test.TblReceiptMasterFactoryForTest;

//--- Services 
import com.ridas.business.service.TblRtgsPaidDetailsService;
import com.ridas.business.service.TblReceiptMasterService;

//--- List Items 
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
public class TblRtgsPaidDetailsControllerTest {
	
	@InjectMocks
	private TblRtgsPaidDetailsController tblRtgsPaidDetailsController;
	@Mock
	private TblRtgsPaidDetailsService tblRtgsPaidDetailsService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private TblReceiptMasterService tblReceiptMasterService; // Injected by Spring

	private TblRtgsPaidDetailsFactoryForTest tblRtgsPaidDetailsFactoryForTest = new TblRtgsPaidDetailsFactoryForTest();
	private TblReceiptMasterFactoryForTest tblReceiptMasterFactoryForTest = new TblReceiptMasterFactoryForTest();

	List<TblReceiptMaster> tblReceiptMasters = new ArrayList<TblReceiptMaster>();

	private void givenPopulateModel() {
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
		
		List<TblRtgsPaidDetails> list = new ArrayList<TblRtgsPaidDetails>();
		when(tblRtgsPaidDetailsService.findAll()).thenReturn(list);
		
		// When
		String viewName = tblRtgsPaidDetailsController.list(model);
		
		// Then
		assertEquals("tblRtgsPaidDetails/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tblRtgsPaidDetailsController.formForCreate(model);
		
		// Then
		assertEquals("tblRtgsPaidDetails/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((TblRtgsPaidDetails)modelMap.get("tblRtgsPaidDetails")).getRtgsDetailsId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblRtgsPaidDetails/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblRtgsPaidDetails tblRtgsPaidDetails = tblRtgsPaidDetailsFactoryForTest.newTblRtgsPaidDetails();
		Integer rtgsDetailsId = tblRtgsPaidDetails.getRtgsDetailsId();
		when(tblRtgsPaidDetailsService.findById(rtgsDetailsId)).thenReturn(tblRtgsPaidDetails);
		
		// When
		String viewName = tblRtgsPaidDetailsController.formForUpdate(model, rtgsDetailsId);
		
		// Then
		assertEquals("tblRtgsPaidDetails/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblRtgsPaidDetails, (TblRtgsPaidDetails) modelMap.get("tblRtgsPaidDetails"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblRtgsPaidDetails/update", modelMap.get("saveAction"));
		
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblRtgsPaidDetails tblRtgsPaidDetails = tblRtgsPaidDetailsFactoryForTest.newTblRtgsPaidDetails();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblRtgsPaidDetails tblRtgsPaidDetailsCreated = new TblRtgsPaidDetails();
		when(tblRtgsPaidDetailsService.create(tblRtgsPaidDetails)).thenReturn(tblRtgsPaidDetailsCreated); 
		
		// When
		String viewName = tblRtgsPaidDetailsController.create(tblRtgsPaidDetails, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblRtgsPaidDetails/form/"+tblRtgsPaidDetails.getRtgsDetailsId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblRtgsPaidDetailsCreated, (TblRtgsPaidDetails) modelMap.get("tblRtgsPaidDetails"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblRtgsPaidDetails tblRtgsPaidDetails = tblRtgsPaidDetailsFactoryForTest.newTblRtgsPaidDetails();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblRtgsPaidDetailsController.create(tblRtgsPaidDetails, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblRtgsPaidDetails/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblRtgsPaidDetails, (TblRtgsPaidDetails) modelMap.get("tblRtgsPaidDetails"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblRtgsPaidDetails/create", modelMap.get("saveAction"));
		
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

		TblRtgsPaidDetails tblRtgsPaidDetails = tblRtgsPaidDetailsFactoryForTest.newTblRtgsPaidDetails();
		
		Exception exception = new RuntimeException("test exception");
		when(tblRtgsPaidDetailsService.create(tblRtgsPaidDetails)).thenThrow(exception);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblRtgsPaidDetailsController.create(tblRtgsPaidDetails, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblRtgsPaidDetails/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblRtgsPaidDetails, (TblRtgsPaidDetails) modelMap.get("tblRtgsPaidDetails"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblRtgsPaidDetails/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblRtgsPaidDetails.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblRtgsPaidDetails tblRtgsPaidDetails = tblRtgsPaidDetailsFactoryForTest.newTblRtgsPaidDetails();
		Integer rtgsDetailsId = tblRtgsPaidDetails.getRtgsDetailsId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblRtgsPaidDetails tblRtgsPaidDetailsSaved = new TblRtgsPaidDetails();
		tblRtgsPaidDetailsSaved.setRtgsDetailsId(rtgsDetailsId);
		when(tblRtgsPaidDetailsService.update(tblRtgsPaidDetails)).thenReturn(tblRtgsPaidDetailsSaved); 

		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblRtgsPaidDetailsController.update(tblRtgsPaidDetails, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblRtgsPaidDetails/form/"+tblRtgsPaidDetails.getRtgsDetailsId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblRtgsPaidDetailsSaved, (TblRtgsPaidDetails) modelMap.get("tblRtgsPaidDetails"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblRtgsPaidDetails tblRtgsPaidDetails = tblRtgsPaidDetailsFactoryForTest.newTblRtgsPaidDetails();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblRtgsPaidDetailsController.update(tblRtgsPaidDetails, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblRtgsPaidDetails/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblRtgsPaidDetails, (TblRtgsPaidDetails) modelMap.get("tblRtgsPaidDetails"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblRtgsPaidDetails/update", modelMap.get("saveAction"));
		
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

		TblRtgsPaidDetails tblRtgsPaidDetails = tblRtgsPaidDetailsFactoryForTest.newTblRtgsPaidDetails();
		
		Exception exception = new RuntimeException("test exception");
		when(tblRtgsPaidDetailsService.update(tblRtgsPaidDetails)).thenThrow(exception);
	
		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblRtgsPaidDetailsController.update(tblRtgsPaidDetails, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblRtgsPaidDetails/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblRtgsPaidDetails, (TblRtgsPaidDetails) modelMap.get("tblRtgsPaidDetails"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblRtgsPaidDetails/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblRtgsPaidDetails.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<TblReceiptMasterListItem> tblReceiptMasterListItems = (List<TblReceiptMasterListItem>) modelMap.get("listOfTblReceiptMasterItems");
		assertEquals(2, tblReceiptMasterListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblRtgsPaidDetails tblRtgsPaidDetails = tblRtgsPaidDetailsFactoryForTest.newTblRtgsPaidDetails();
		Integer rtgsDetailsId = tblRtgsPaidDetails.getRtgsDetailsId();
		
		// When
		String viewName = tblRtgsPaidDetailsController.delete(redirectAttributes, rtgsDetailsId);
		
		// Then
		verify(tblRtgsPaidDetailsService).delete(rtgsDetailsId);
		assertEquals("redirect:/tblRtgsPaidDetails", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblRtgsPaidDetails tblRtgsPaidDetails = tblRtgsPaidDetailsFactoryForTest.newTblRtgsPaidDetails();
		Integer rtgsDetailsId = tblRtgsPaidDetails.getRtgsDetailsId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tblRtgsPaidDetailsService).delete(rtgsDetailsId);
		
		// When
		String viewName = tblRtgsPaidDetailsController.delete(redirectAttributes, rtgsDetailsId);
		
		// Then
		verify(tblRtgsPaidDetailsService).delete(rtgsDetailsId);
		assertEquals("redirect:/tblRtgsPaidDetails", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tblRtgsPaidDetails.error.delete", exception);
	}
	
	
}
