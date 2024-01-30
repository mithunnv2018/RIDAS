/*
 * Created on 29 Feb 2016 ( Time 17:45:55 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.web.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//done as on 2 feb 2016
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import java.io.File;

//--- Common classes
import com.ridas.web.common.AbstractController;
import com.ridas.web.common.FormMode;
import com.ridas.web.common.Message;
import com.ridas.web.common.MessageType;

//--- Entities
import com.ridas.bean.TblBusiAssoMaster;
import com.ridas.bean.TblMembershipForm;
import com.ridas.bean.TblReceiptMaster;

//--- Services 
import com.ridas.business.service.TblBusiAssoMasterService;
import com.ridas.business.service.TblMembershipFormService;
import com.ridas.business.service.TblReceiptMasterService;

//--- List Items 
import com.ridas.web.listitem.TblMembershipFormListItem;
import com.ridas.web.listitem.TblReceiptMasterListItem;

/**
 * Spring MVC controller for 'TblBusiAssoMaster' management.
 */
@Controller
@RequestMapping("/tblBusiAssoMaster")
public class TblBusiAssoMasterController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "tblBusiAssoMaster";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "tblBusiAssoMaster/form";
	private static final String JSP_LIST   = "tblBusiAssoMaster/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/tblBusiAssoMaster/create";
	private static final String SAVE_ACTION_UPDATE   = "/tblBusiAssoMaster/update";

	//--done as on 19 feb 2016 to import CSV FILE Logic
	private static final String XLS_ACTION_UPLOAD = "fileImportAction";
	private static final String XLS_ACTION_UPLOAD_URL = "/tblBusiAssoMaster/csvimport";
	private static final String JSP_SHOW_IMPORT_PAGE = "tblBusiAssoMaster/fileimport";
	
	//--- Main entity service
	@Resource
    private TblBusiAssoMasterService tblBusiAssoMasterService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private TblMembershipFormService tblMembershipFormService; // Injected by Spring
	@Resource
    private TblReceiptMasterService tblReceiptMasterService; // Injected by Spring

	//done as on 2 feb 2016
	static final String folderName="TblBusiAssoMaster";
	private MultipartFile file2=null;
//done as on 19 feb 2016 added logic for csv file import
	private MultipartFile fileimport2 = null;
	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public TblBusiAssoMasterController() {
		super(TblBusiAssoMasterController.class, MAIN_ENTITY_NAME );
		log("TblBusiAssoMasterController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------
	/**
	 * Populates the combo-box "items" for the referenced entity "TblMembershipForm"
	 * @param model
	 */
	private void populateListOfTblMembershipFormItems(Model model) {
		List<TblMembershipForm> list = tblMembershipFormService.findAll();
		List<TblMembershipFormListItem> items = new LinkedList<TblMembershipFormListItem>();
		for ( TblMembershipForm tblMembershipForm : list ) {
			items.add(new TblMembershipFormListItem( tblMembershipForm ) );
		}
		model.addAttribute("listOfTblMembershipFormItems", items ) ;
	}

	/**
	 * Populates the combo-box "items" for the referenced entity "TblReceiptMaster"
	 * @param model
	 */
	private void populateListOfTblReceiptMasterItems(Model model) {
		List<TblReceiptMaster> list = tblReceiptMasterService.findAll();
		List<TblReceiptMasterListItem> items = new LinkedList<TblReceiptMasterListItem>();
		for ( TblReceiptMaster tblReceiptMaster : list ) {
			items.add(new TblReceiptMasterListItem( tblReceiptMaster ) );
		}
		model.addAttribute("listOfTblReceiptMasterItems", items ) ;
	}


	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param tblBusiAssoMaster
	 */
	private void populateModel(Model model, TblBusiAssoMaster tblBusiAssoMaster, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, tblBusiAssoMaster);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
			populateListOfTblMembershipFormItems(model);
			populateListOfTblReceiptMasterItems(model);
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
			populateListOfTblMembershipFormItems(model);
			populateListOfTblReceiptMasterItems(model);
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of TblBusiAssoMaster found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<TblBusiAssoMaster> list = tblBusiAssoMasterService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new TblBusiAssoMaster
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		TblBusiAssoMaster tblBusiAssoMaster = new TblBusiAssoMaster();	

// done as on 10 feb 2016  save primary key default to temp
				String DUMMY="temp";
		tblBusiAssoMaster.setBuAsId(DUMMY);

		populateModel( model, tblBusiAssoMaster, FormMode.CREATE);


		//done as on 2 feb 2016
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing TblBusiAssoMaster
	 * @param model Spring MVC model
	 * @param buAsId  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{buAsId}")
	public String formForUpdate(Model model, @PathVariable("buAsId") String buAsId ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		TblBusiAssoMaster tblBusiAssoMaster = tblBusiAssoMasterService.findById(buAsId);
		populateModel( model, tblBusiAssoMaster, FormMode.UPDATE);	
		model.addAttribute("isreport", "true");
		model.addAttribute("disableAuto", "true");
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param tblBusiAssoMaster  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid TblBusiAssoMaster tblBusiAssoMaster, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		//done as on 2 feb 2016
		//ADD PARAMS HttpServletResponse httpServletResponse

		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {

				

// done as on 06 feb 2016  save primary key default to temp
				String DUMMY="temp";

				tblBusiAssoMaster.setBuAsId(DUMMY);

				TblBusiAssoMaster tblBusiAssoMasterCreated = tblBusiAssoMasterService.create(tblBusiAssoMaster); 
				model.addAttribute(MAIN_ENTITY_NAME, tblBusiAssoMasterCreated);
				model.addAttribute("isreport", "true");
				
				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				// done as on 06 feb 2016  introduced a new getter for getting the primary key of the newly created Bean coming after saved.
				return redirectToForm(httpServletRequest, tblBusiAssoMasterCreated.getBuAsId() );
				//return redirectToForm(httpServletRequest, tblBusiAssoMaster.getBuAsId() );
			} else {
				populateModel( model, tblBusiAssoMaster, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "tblBusiAssoMaster.error.create", e);
			populateModel( model, tblBusiAssoMaster, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param tblBusiAssoMaster  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid TblBusiAssoMaster tblBusiAssoMaster, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
	//done as on 2 feb 2016
	//ADD PARAMS HttpServletResponse httpServletResponse
		
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {

				
				//--- Perform database operations
				TblBusiAssoMaster tblBusiAssoMasterSaved = tblBusiAssoMasterService.update(tblBusiAssoMaster);
				model.addAttribute(MAIN_ENTITY_NAME, tblBusiAssoMasterSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, tblBusiAssoMaster.getBuAsId());
			
				
				
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, tblBusiAssoMaster, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "tblBusiAssoMaster.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, tblBusiAssoMaster, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'DELETE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param redirectAttributes
	 * @param buAsId  primary key element
	 * @return
	 */
	@RequestMapping(value = "/delete/{buAsId}") // GET or POST
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("buAsId") String buAsId) {
		log("Action 'delete'" );
		try {
			tblBusiAssoMasterService.delete( buAsId );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "tblBusiAssoMaster.error.delete", e);
		}
		return redirectToList();
	}

	@RequestMapping("/formforcsvimport2")
	public String formForExcelImport(Model model) {
		log("Action 'formforexcelimport'");
		// --- Populates the model with a new instance
		TblBusiAssoMaster tblBusiAssoMaster = new TblBusiAssoMaster();

		// done as on 19 feb 2016  save primary key default to temp
				String DUMMY="temp";

		tblBusiAssoMaster.setBuAsId(DUMMY);


		model.addAttribute("fileimport2", fileimport2);
		 
		model.addAttribute(XLS_ACTION_UPLOAD, XLS_ACTION_UPLOAD_URL);

		// done as on 2 feb 2016
		return JSP_SHOW_IMPORT_PAGE;
	}

	@RequestMapping("/csvimport")
	public String excelimport(TblBusiAssoMaster tblBusiAssoMaster, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 
		log("Action 'csvuploading'");
		try {
			if (!bindingResult.hasErrors()) {

				 
				file2 = (MultipartFile) ((DefaultMultipartHttpServletRequest) httpServletRequest).getFile("fileimport2");
				if (file2.isEmpty() == false) {
 					String productPdfFileName = file2.getOriginalFilename();
					 
 					String contenttype = file2.getContentType();
 					
 					tblBusiAssoMasterService.doImportExcelSpreadSheet(file2);	
 
				}
			}
		} catch (Exception e) {
			log("Action 'CSVImport' : Exception - " + e.getMessage());
			messageHelper.addException(model, "tblBusiAssoMaster.error.create", e);	
		}
		return "redirect:/"+MAIN_ENTITY_NAME;
	}
	
	 @RequestMapping("/formforpdfreport")
	 public String formForPDReport(Model model) {
	  /*
	   * done as on 11 mar 2016 by Mandar <<commented>>
	   */

	  
	  log("Action 'list'");
	  List<TblBusiAssoMaster> list = tblBusiAssoMasterService.findAll();
	  model.addAttribute(MAIN_LIST_NAME, list);  
	  return "tblBusiAssoMaster/report";
	  

	 }
}