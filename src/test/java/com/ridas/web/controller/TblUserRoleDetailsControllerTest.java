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
import com.ridas.bean.TblUserRoleDetails;
import com.ridas.bean.TblUserRoleMaster;
import com.ridas.bean.TblUserMaster;
import com.ridas.test.TblUserRoleDetailsFactoryForTest;
import com.ridas.test.TblUserRoleMasterFactoryForTest;
import com.ridas.test.TblUserMasterFactoryForTest;

//--- Services 
import com.ridas.business.service.TblUserRoleDetailsService;
import com.ridas.business.service.TblUserRoleMasterService;
import com.ridas.business.service.TblUserMasterService;

//--- List Items 
import com.ridas.web.listitem.TblUserRoleMasterListItem;
import com.ridas.web.listitem.TblUserMasterListItem;

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
public class TblUserRoleDetailsControllerTest {
	
	@InjectMocks
	private TblUserRoleDetailsController tblUserRoleDetailsController;
	@Mock
	private TblUserRoleDetailsService tblUserRoleDetailsService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private TblUserRoleMasterService tblUserRoleMasterService; // Injected by Spring
	@Mock
	private TblUserMasterService tblUserMasterService; // Injected by Spring

	private TblUserRoleDetailsFactoryForTest tblUserRoleDetailsFactoryForTest = new TblUserRoleDetailsFactoryForTest();
	private TblUserRoleMasterFactoryForTest tblUserRoleMasterFactoryForTest = new TblUserRoleMasterFactoryForTest();
	private TblUserMasterFactoryForTest tblUserMasterFactoryForTest = new TblUserMasterFactoryForTest();

	List<TblUserRoleMaster> tblUserRoleMasters = new ArrayList<TblUserRoleMaster>();
	List<TblUserMaster> tblUserMasters = new ArrayList<TblUserMaster>();

	private void givenPopulateModel() {
		TblUserRoleMaster tblUserRoleMaster1 = tblUserRoleMasterFactoryForTest.newTblUserRoleMaster();
		TblUserRoleMaster tblUserRoleMaster2 = tblUserRoleMasterFactoryForTest.newTblUserRoleMaster();
		List<TblUserRoleMaster> tblUserRoleMasters = new ArrayList<TblUserRoleMaster>();
		tblUserRoleMasters.add(tblUserRoleMaster1);
		tblUserRoleMasters.add(tblUserRoleMaster2);
		when(tblUserRoleMasterService.findAll()).thenReturn(tblUserRoleMasters);

		TblUserMaster tblUserMaster1 = tblUserMasterFactoryForTest.newTblUserMaster();
		TblUserMaster tblUserMaster2 = tblUserMasterFactoryForTest.newTblUserMaster();
		List<TblUserMaster> tblUserMasters = new ArrayList<TblUserMaster>();
		tblUserMasters.add(tblUserMaster1);
		tblUserMasters.add(tblUserMaster2);
		when(tblUserMasterService.findAll()).thenReturn(tblUserMasters);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<TblUserRoleDetails> list = new ArrayList<TblUserRoleDetails>();
		when(tblUserRoleDetailsService.findAll()).thenReturn(list);
		
		// When
		String viewName = tblUserRoleDetailsController.list(model);
		
		// Then
		assertEquals("tblUserRoleDetails/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tblUserRoleDetailsController.formForCreate(model);
		
		// Then
		assertEquals("tblUserRoleDetails/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((TblUserRoleDetails)modelMap.get("tblUserRoleDetails")).getUserRoleDetailId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblUserRoleDetails/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblUserRoleMasterListItem> tblUserRoleMasterListItems = (List<TblUserRoleMasterListItem>) modelMap.get("listOfTblUserRoleMasterItems");
		assertEquals(2, tblUserRoleMasterListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblUserMasterListItem> tblUserMasterListItems = (List<TblUserMasterListItem>) modelMap.get("listOfTblUserMasterItems");
		assertEquals(2, tblUserMasterListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblUserRoleDetails tblUserRoleDetails = tblUserRoleDetailsFactoryForTest.newTblUserRoleDetails();
		String userRoleDetailId = tblUserRoleDetails.getUserRoleDetailId();
		when(tblUserRoleDetailsService.findById(userRoleDetailId)).thenReturn(tblUserRoleDetails);
		
		// When
		String viewName = tblUserRoleDetailsController.formForUpdate(model, userRoleDetailId);
		
		// Then
		assertEquals("tblUserRoleDetails/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleDetails, (TblUserRoleDetails) modelMap.get("tblUserRoleDetails"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblUserRoleDetails/update", modelMap.get("saveAction"));
		
		List<TblUserRoleMasterListItem> tblUserRoleMasterListItems = (List<TblUserRoleMasterListItem>) modelMap.get("listOfTblUserRoleMasterItems");
		assertEquals(2, tblUserRoleMasterListItems.size());
		
		List<TblUserMasterListItem> tblUserMasterListItems = (List<TblUserMasterListItem>) modelMap.get("listOfTblUserMasterItems");
		assertEquals(2, tblUserMasterListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblUserRoleDetails tblUserRoleDetails = tblUserRoleDetailsFactoryForTest.newTblUserRoleDetails();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblUserRoleDetails tblUserRoleDetailsCreated = new TblUserRoleDetails();
		when(tblUserRoleDetailsService.create(tblUserRoleDetails)).thenReturn(tblUserRoleDetailsCreated); 
		
		// When
		String viewName = tblUserRoleDetailsController.create(tblUserRoleDetails, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblUserRoleDetails/form/"+tblUserRoleDetails.getUserRoleDetailId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleDetailsCreated, (TblUserRoleDetails) modelMap.get("tblUserRoleDetails"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblUserRoleDetails tblUserRoleDetails = tblUserRoleDetailsFactoryForTest.newTblUserRoleDetails();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblUserRoleDetailsController.create(tblUserRoleDetails, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblUserRoleDetails/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleDetails, (TblUserRoleDetails) modelMap.get("tblUserRoleDetails"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblUserRoleDetails/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblUserRoleMasterListItem> tblUserRoleMasterListItems = (List<TblUserRoleMasterListItem>) modelMap.get("listOfTblUserRoleMasterItems");
		assertEquals(2, tblUserRoleMasterListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblUserMasterListItem> tblUserMasterListItems = (List<TblUserMasterListItem>) modelMap.get("listOfTblUserMasterItems");
		assertEquals(2, tblUserMasterListItems.size());
		
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

		TblUserRoleDetails tblUserRoleDetails = tblUserRoleDetailsFactoryForTest.newTblUserRoleDetails();
		
		Exception exception = new RuntimeException("test exception");
		when(tblUserRoleDetailsService.create(tblUserRoleDetails)).thenThrow(exception);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblUserRoleDetailsController.create(tblUserRoleDetails, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblUserRoleDetails/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleDetails, (TblUserRoleDetails) modelMap.get("tblUserRoleDetails"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblUserRoleDetails/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblUserRoleDetails.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<TblUserRoleMasterListItem> tblUserRoleMasterListItems = (List<TblUserRoleMasterListItem>) modelMap.get("listOfTblUserRoleMasterItems");
		assertEquals(2, tblUserRoleMasterListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblUserMasterListItem> tblUserMasterListItems = (List<TblUserMasterListItem>) modelMap.get("listOfTblUserMasterItems");
		assertEquals(2, tblUserMasterListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblUserRoleDetails tblUserRoleDetails = tblUserRoleDetailsFactoryForTest.newTblUserRoleDetails();
		String userRoleDetailId = tblUserRoleDetails.getUserRoleDetailId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblUserRoleDetails tblUserRoleDetailsSaved = new TblUserRoleDetails();
		tblUserRoleDetailsSaved.setUserRoleDetailId(userRoleDetailId);
		when(tblUserRoleDetailsService.update(tblUserRoleDetails)).thenReturn(tblUserRoleDetailsSaved); 

		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblUserRoleDetailsController.update(tblUserRoleDetails, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblUserRoleDetails/form/"+tblUserRoleDetails.getUserRoleDetailId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleDetailsSaved, (TblUserRoleDetails) modelMap.get("tblUserRoleDetails"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblUserRoleDetails tblUserRoleDetails = tblUserRoleDetailsFactoryForTest.newTblUserRoleDetails();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblUserRoleDetailsController.update(tblUserRoleDetails, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblUserRoleDetails/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleDetails, (TblUserRoleDetails) modelMap.get("tblUserRoleDetails"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblUserRoleDetails/update", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblUserRoleMasterListItem> tblUserRoleMasterListItems = (List<TblUserRoleMasterListItem>) modelMap.get("listOfTblUserRoleMasterItems");
		assertEquals(2, tblUserRoleMasterListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblUserMasterListItem> tblUserMasterListItems = (List<TblUserMasterListItem>) modelMap.get("listOfTblUserMasterItems");
		assertEquals(2, tblUserMasterListItems.size());
		
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

		TblUserRoleDetails tblUserRoleDetails = tblUserRoleDetailsFactoryForTest.newTblUserRoleDetails();
		
		Exception exception = new RuntimeException("test exception");
		when(tblUserRoleDetailsService.update(tblUserRoleDetails)).thenThrow(exception);
	
		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblUserRoleDetailsController.update(tblUserRoleDetails, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblUserRoleDetails/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblUserRoleDetails, (TblUserRoleDetails) modelMap.get("tblUserRoleDetails"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblUserRoleDetails/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblUserRoleDetails.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<TblUserRoleMasterListItem> tblUserRoleMasterListItems = (List<TblUserRoleMasterListItem>) modelMap.get("listOfTblUserRoleMasterItems");
		assertEquals(2, tblUserRoleMasterListItems.size());
		
		@SuppressWarnings("unchecked")
		List<TblUserMasterListItem> tblUserMasterListItems = (List<TblUserMasterListItem>) modelMap.get("listOfTblUserMasterItems");
		assertEquals(2, tblUserMasterListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblUserRoleDetails tblUserRoleDetails = tblUserRoleDetailsFactoryForTest.newTblUserRoleDetails();
		String userRoleDetailId = tblUserRoleDetails.getUserRoleDetailId();
		
		// When
		String viewName = tblUserRoleDetailsController.delete(redirectAttributes, userRoleDetailId);
		
		// Then
		verify(tblUserRoleDetailsService).delete(userRoleDetailId);
		assertEquals("redirect:/tblUserRoleDetails", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblUserRoleDetails tblUserRoleDetails = tblUserRoleDetailsFactoryForTest.newTblUserRoleDetails();
		String userRoleDetailId = tblUserRoleDetails.getUserRoleDetailId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tblUserRoleDetailsService).delete(userRoleDetailId);
		
		// When
		String viewName = tblUserRoleDetailsController.delete(redirectAttributes, userRoleDetailId);
		
		// Then
		verify(tblUserRoleDetailsService).delete(userRoleDetailId);
		assertEquals("redirect:/tblUserRoleDetails", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tblUserRoleDetails.error.delete", exception);
	}
	
	
}
