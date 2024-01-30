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
import com.ridas.bean.TblMembershipForm;
import com.ridas.test.TblMembershipFormFactoryForTest;

//--- Services 
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
public class TblMembershipFormControllerTest {
	
	@InjectMocks
	private TblMembershipFormController tblMembershipFormController;
	@Mock
	private TblMembershipFormService tblMembershipFormService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private TblMembershipFormFactoryForTest tblMembershipFormFactoryForTest = new TblMembershipFormFactoryForTest();
	//private TblMembershipFormFactoryForTest tblMembershipFormFactoryForTest = new TblMembershipFormFactoryForTest();

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
		
		List<TblMembershipForm> list = new ArrayList<TblMembershipForm>();
		when(tblMembershipFormService.findAll()).thenReturn(list);
		
		// When
		String viewName = tblMembershipFormController.list(model);
		
		// Then
		assertEquals("tblMembershipForm/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = tblMembershipFormController.formForCreate(model);
		
		// Then
		assertEquals("tblMembershipForm/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((TblMembershipForm)modelMap.get("tblMembershipForm")).getMemberId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblMembershipForm/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();
		String memberId = tblMembershipForm.getMemberId();
		when(tblMembershipFormService.findById(memberId)).thenReturn(tblMembershipForm);
		
		// When
		String viewName = tblMembershipFormController.formForUpdate(model, memberId);
		
		// Then
		assertEquals("tblMembershipForm/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblMembershipForm, (TblMembershipForm) modelMap.get("tblMembershipForm"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblMembershipForm/update", modelMap.get("saveAction"));
		
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblMembershipForm tblMembershipFormCreated = new TblMembershipForm();
		when(tblMembershipFormService.create(tblMembershipForm)).thenReturn(tblMembershipFormCreated); 
		
		// When
		String viewName = tblMembershipFormController.create(tblMembershipForm, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblMembershipForm/form/"+tblMembershipForm.getMemberId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblMembershipFormCreated, (TblMembershipForm) modelMap.get("tblMembershipForm"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblMembershipFormController.create(tblMembershipForm, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblMembershipForm/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblMembershipForm, (TblMembershipForm) modelMap.get("tblMembershipForm"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblMembershipForm/create", modelMap.get("saveAction"));
		
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

		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();
		
		Exception exception = new RuntimeException("test exception");
		when(tblMembershipFormService.create(tblMembershipForm)).thenThrow(exception);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblMembershipFormController.create(tblMembershipForm, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblMembershipForm/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblMembershipForm, (TblMembershipForm) modelMap.get("tblMembershipForm"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/tblMembershipForm/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblMembershipForm.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();
		String memberId = tblMembershipForm.getMemberId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		TblMembershipForm tblMembershipFormSaved = new TblMembershipForm();
		tblMembershipFormSaved.setMemberId(memberId);
		when(tblMembershipFormService.update(tblMembershipForm)).thenReturn(tblMembershipFormSaved); 

		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblMembershipFormController.update(tblMembershipForm, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("redirect:/tblMembershipForm/form/"+tblMembershipForm.getMemberId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblMembershipFormSaved, (TblMembershipForm) modelMap.get("tblMembershipForm"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		//done as on 2 feb 2016
		HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
		
		//done as on 2 feb 2016 added httpServletResponse param to method
		// When
		String viewName = tblMembershipFormController.update(tblMembershipForm, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblMembershipForm/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblMembershipForm, (TblMembershipForm) modelMap.get("tblMembershipForm"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblMembershipForm/update", modelMap.get("saveAction"));
		
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

		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();
		
		Exception exception = new RuntimeException("test exception");
		when(tblMembershipFormService.update(tblMembershipForm)).thenThrow(exception);
	
		//done as on 2 feb 2016 added httpServletResponse param to method		
		// When
		String viewName = tblMembershipFormController.update(tblMembershipForm, bindingResult, model, redirectAttributes, httpServletRequest,httpServletResponse);
		
		// Then
		assertEquals("tblMembershipForm/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(tblMembershipForm, (TblMembershipForm) modelMap.get("tblMembershipForm"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/tblMembershipForm/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "tblMembershipForm.error.update", exception);
		
		@SuppressWarnings("unchecked")
		List<TblMembershipFormListItem> tblMembershipFormListItems = (List<TblMembershipFormListItem>) modelMap.get("listOfTblMembershipFormItems");
		assertEquals(2, tblMembershipFormListItems.size());
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();
		String memberId = tblMembershipForm.getMemberId();
		
		// When
		String viewName = tblMembershipFormController.delete(redirectAttributes, memberId);
		
		// Then
		verify(tblMembershipFormService).delete(memberId);
		assertEquals("redirect:/tblMembershipForm", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		TblMembershipForm tblMembershipForm = tblMembershipFormFactoryForTest.newTblMembershipForm();
		String memberId = tblMembershipForm.getMemberId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(tblMembershipFormService).delete(memberId);
		
		// When
		String viewName = tblMembershipFormController.delete(redirectAttributes, memberId);
		
		// Then
		verify(tblMembershipFormService).delete(memberId);
		assertEquals("redirect:/tblMembershipForm", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "tblMembershipForm.error.delete", exception);
	}
	
	
}
