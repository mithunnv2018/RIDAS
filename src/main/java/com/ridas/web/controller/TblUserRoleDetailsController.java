










				

/*
 * Created on 29 Feb 2016 ( Time 17:45:57 )
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
import com.ridas.bean.TblUserRoleDetails;
import com.ridas.bean.TblUserRoleMaster;
import com.ridas.bean.TblUserMaster;

//--- Services 
import com.ridas.business.service.TblUserRoleDetailsService;
import com.ridas.business.service.TblUserRoleMasterService;
import com.ridas.business.service.TblUserMasterService;

//--- List Items 
import com.ridas.web.listitem.TblUserRoleMasterListItem;
import com.ridas.web.listitem.TblUserMasterListItem;

/**
 * Spring MVC controller for 'TblUserRoleDetails' management.
 */
@Controller
@RequestMapping("/tblUserRoleDetails")
public class TblUserRoleDetailsController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "tblUserRoleDetails";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "tblUserRoleDetails/form";
	private static final String JSP_LIST   = "tblUserRoleDetails/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/tblUserRoleDetails/create";
	private static final String SAVE_ACTION_UPDATE   = "/tblUserRoleDetails/update";

	//--done as on 19 feb 2016 to import CSV FILE Logic
	private static final String XLS_ACTION_UPLOAD = "fileImportAction";
	private static final String XLS_ACTION_UPLOAD_URL = "/tblUserRoleDetails/csvimport";
	private static final String JSP_SHOW_IMPORT_PAGE = "tblUserRoleDetails/fileimport";
	
	//--- Main entity service
	@Resource
    private TblUserRoleDetailsService tblUserRoleDetailsService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private TblUserRoleMasterService tblUserRoleMasterService; // Injected by Spring
	@Resource
    private TblUserMasterService tblUserMasterService; // Injected by Spring

	//done as on 2 feb 2016
	static final String folderName="TblUserRoleDetails";
	private MultipartFile file2=null;
//done as on 19 feb 2016 added logic for csv file import
	private MultipartFile fileimport2 = null;
	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public TblUserRoleDetailsController() {
		super(TblUserRoleDetailsController.class, MAIN_ENTITY_NAME );
		log("TblUserRoleDetailsController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------
	/**
	 * Populates the combo-box "items" for the referenced entity "TblUserRoleMaster"
	 * @param model
	 */
	private void populateListOfTblUserRoleMasterItems(Model model) {
		List<TblUserRoleMaster> list = tblUserRoleMasterService.findAll();
		List<TblUserRoleMasterListItem> items = new LinkedList<TblUserRoleMasterListItem>();
		for ( TblUserRoleMaster tblUserRoleMaster : list ) {
			items.add(new TblUserRoleMasterListItem( tblUserRoleMaster ) );
		}
		model.addAttribute("listOfTblUserRoleMasterItems", items ) ;
	}

	/**
	 * Populates the combo-box "items" for the referenced entity "TblUserMaster"
	 * @param model
	 */
	private void populateListOfTblUserMasterItems(Model model) {
		List<TblUserMaster> list = tblUserMasterService.findAll();
		List<TblUserMasterListItem> items = new LinkedList<TblUserMasterListItem>();
		for ( TblUserMaster tblUserMaster : list ) {
			items.add(new TblUserMasterListItem( tblUserMaster ) );
		}
		model.addAttribute("listOfTblUserMasterItems", items ) ;
	}


	/**
	 * Populates the Spring MVC model with the given entity and eventually other useful data
	 * @param model
	 * @param tblUserRoleDetails
	 */
	private void populateModel(Model model, TblUserRoleDetails tblUserRoleDetails, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, tblUserRoleDetails);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
			populateListOfTblUserRoleMasterItems(model);
			populateListOfTblUserMasterItems(model);
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
			populateListOfTblUserRoleMasterItems(model);
			populateListOfTblUserMasterItems(model);
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of TblUserRoleDetails found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<TblUserRoleDetails> list = tblUserRoleDetailsService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new TblUserRoleDetails
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		TblUserRoleDetails tblUserRoleDetails = new TblUserRoleDetails();	

// done as on 10 feb 2016  save primary key default to temp
				String DUMMY="temp";
		tblUserRoleDetails.setUserRoleDetailId(DUMMY);

		populateModel( model, tblUserRoleDetails, FormMode.CREATE);


		//done as on 2 feb 2016
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing TblUserRoleDetails
	 * @param model Spring MVC model
	 * @param userRoleDetailId  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{userRoleDetailId}")
	public String formForUpdate(Model model, @PathVariable("userRoleDetailId") String userRoleDetailId ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		TblUserRoleDetails tblUserRoleDetails = tblUserRoleDetailsService.findById(userRoleDetailId);
		populateModel( model, tblUserRoleDetails, FormMode.UPDATE);		
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param tblUserRoleDetails  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid TblUserRoleDetails tblUserRoleDetails, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		//done as on 2 feb 2016
		//ADD PARAMS HttpServletResponse httpServletResponse

		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {

				

// done as on 06 feb 2016  save primary key default to temp
				String DUMMY="temp";

				tblUserRoleDetails.setUserRoleDetailId(DUMMY);

				TblUserRoleDetails tblUserRoleDetailsCreated = tblUserRoleDetailsService.create(tblUserRoleDetails); 
				model.addAttribute(MAIN_ENTITY_NAME, tblUserRoleDetailsCreated);

				
				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				// done as on 06 feb 2016  introduced a new getter for getting the primary key of the newly created Bean coming after saved.
				return redirectToForm(httpServletRequest, tblUserRoleDetailsCreated.getUserRoleDetailId() );
				//return redirectToForm(httpServletRequest, tblUserRoleDetails.getUserRoleDetailId() );
			} else {
				populateModel( model, tblUserRoleDetails, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "tblUserRoleDetails.error.create", e);
			populateModel( model, tblUserRoleDetails, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param tblUserRoleDetails  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid TblUserRoleDetails tblUserRoleDetails, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
	//done as on 2 feb 2016
	//ADD PARAMS HttpServletResponse httpServletResponse
		
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {

				
				//--- Perform database operations
				TblUserRoleDetails tblUserRoleDetailsSaved = tblUserRoleDetailsService.update(tblUserRoleDetails);
				model.addAttribute(MAIN_ENTITY_NAME, tblUserRoleDetailsSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, tblUserRoleDetails.getUserRoleDetailId());
			
				
				
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, tblUserRoleDetails, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "tblUserRoleDetails.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, tblUserRoleDetails, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'DELETE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param redirectAttributes
	 * @param userRoleDetailId  primary key element
	 * @return
	 */
	@RequestMapping(value = "/delete/{userRoleDetailId}") // GET or POST
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("userRoleDetailId") String userRoleDetailId) {
		log("Action 'delete'" );
		try {
			tblUserRoleDetailsService.delete( userRoleDetailId );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "tblUserRoleDetails.error.delete", e);
		}
		return redirectToList();
	}

	@RequestMapping("/formforcsvimport2")
	public String formForExcelImport(Model model) {
		log("Action 'formforexcelimport'");
		// --- Populates the model with a new instance
		TblUserRoleDetails tblUserRoleDetails = new TblUserRoleDetails();

		// done as on 19 feb 2016  save primary key default to temp
				String DUMMY="temp";

		tblUserRoleDetails.setUserRoleDetailId(DUMMY);


		model.addAttribute("fileimport2", fileimport2);
		 
		model.addAttribute(XLS_ACTION_UPLOAD, XLS_ACTION_UPLOAD_URL);

		// done as on 2 feb 2016
		return JSP_SHOW_IMPORT_PAGE;
	}

	@RequestMapping("/csvimport")
	public String excelimport(TblUserRoleDetails tblUserRoleDetails, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 
		log("Action 'csvuploading'");
		try {
			if (!bindingResult.hasErrors()) {

				 
				file2 = (MultipartFile) ((DefaultMultipartHttpServletRequest) httpServletRequest).getFile("fileimport2");
				if (file2.isEmpty() == false) {
 					String productPdfFileName = file2.getOriginalFilename();
					 
 					String contenttype = file2.getContentType();
 					
 					tblUserRoleDetailsService.doImportExcelSpreadSheet(file2);	
 
				}
			}
		} catch (Exception e) {
			log("Action 'CSVImport' : Exception - " + e.getMessage());
			messageHelper.addException(model, "tblUserRoleDetails.error.create", e);	
		}
		return "redirect:/"+MAIN_ENTITY_NAME;
	}
}
