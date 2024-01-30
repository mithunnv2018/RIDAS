
/*
 * Created on 18 Mar 2016 ( Time 11:54:34 )
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
import com.ridas.bean.TblRtgsPaidDetails;
import com.ridas.bean.TblReceiptMaster;

//--- Services 
import com.ridas.business.service.TblRtgsPaidDetailsService;
import com.ridas.business.service.TblReceiptMasterService;

//--- List Items 
import com.ridas.web.listitem.TblReceiptMasterListItem;

/**
 * Spring MVC controller for 'TblRtgsPaidDetails' management.
 */
@Controller
@RequestMapping("/tblRtgsPaidDetails")
public class TblRtgsPaidDetailsController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "tblRtgsPaidDetails";
	private static final String MAIN_LIST_NAME   = "list";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_FORM   = "tblRtgsPaidDetails/form";
	private static final String JSP_LIST   = "tblRtgsPaidDetails/list";

	//--- SAVE ACTION ( in the HTML form )
	private static final String SAVE_ACTION_CREATE   = "/tblRtgsPaidDetails/create";
	private static final String SAVE_ACTION_UPDATE   = "/tblRtgsPaidDetails/update";

	//--done as on 19 feb 2016 to import CSV FILE Logic
	private static final String XLS_ACTION_UPLOAD = "fileImportAction";
	private static final String XLS_ACTION_UPLOAD_URL = "/tblRtgsPaidDetails/csvimport";
	private static final String JSP_SHOW_IMPORT_PAGE = "tblRtgsPaidDetails/fileimport";
	
	//--- Main entity service
	@Resource
    private TblRtgsPaidDetailsService tblRtgsPaidDetailsService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private TblReceiptMasterService tblReceiptMasterService; // Injected by Spring

	//done as on 2 feb 2016
	static final String folderName="TblRtgsPaidDetails";
	private MultipartFile file2=null;
//done as on 19 feb 2016 added logic for csv file import
	private MultipartFile fileimport2 = null;
	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public TblRtgsPaidDetailsController() {
		super(TblRtgsPaidDetailsController.class, MAIN_ENTITY_NAME );
		log("TblRtgsPaidDetailsController created.");
	}

	//--------------------------------------------------------------------------------------
	// Spring MVC model management
	//--------------------------------------------------------------------------------------
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
	 * @param tblRtgsPaidDetails
	 */
	private void populateModel(Model model, TblRtgsPaidDetails tblRtgsPaidDetails, FormMode formMode) {
		//--- Main entity
		model.addAttribute(MAIN_ENTITY_NAME, tblRtgsPaidDetails);
		if ( formMode == FormMode.CREATE ) {
			model.addAttribute(MODE, MODE_CREATE); // The form is in "create" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_CREATE); 			
			//--- Other data useful in this screen in "create" mode (all fields)
			populateListOfTblReceiptMasterItems(model);
		}
		else if ( formMode == FormMode.UPDATE ) {
			model.addAttribute(MODE, MODE_UPDATE); // The form is in "update" mode
			model.addAttribute(SAVE_ACTION, SAVE_ACTION_UPDATE); 			
			//--- Other data useful in this screen in "update" mode (only non-pk fields)
			populateListOfTblReceiptMasterItems(model);
		}
	}

	//--------------------------------------------------------------------------------------
	// Request mapping
	//--------------------------------------------------------------------------------------
	/**
	 * Shows a list with all the occurrences of TblRtgsPaidDetails found in the database
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping()
	public String list(Model model) {
		log("Action 'list'");
		List<TblRtgsPaidDetails> list = tblRtgsPaidDetailsService.findAll();
		model.addAttribute(MAIN_LIST_NAME, list);		
		return JSP_LIST;
	}

	/**
	 * Shows a form page in order to create a new TblRtgsPaidDetails
	 * @param model Spring MVC model
	 * @return
	 */
	@RequestMapping("/form")
	public String formForCreate(Model model) {
		log("Action 'formForCreate'");
		//--- Populates the model with a new instance
		TblRtgsPaidDetails tblRtgsPaidDetails = new TblRtgsPaidDetails();	

// done as on 10 feb 2016  save primary key default to temp
				java.lang.Integer DUMMY=new java.lang.Integer(0+"");
		tblRtgsPaidDetails.setRtgsDetailsId(DUMMY);

		populateModel( model, tblRtgsPaidDetails, FormMode.CREATE);


		//done as on 2 feb 2016
		return JSP_FORM;
	}

	/**
	 * Shows a form page in order to update an existing TblRtgsPaidDetails
	 * @param model Spring MVC model
	 * @param rtgsDetailsId  primary key element
	 * @return
	 */
	@RequestMapping(value = "/form/{rtgsDetailsId}")
	public String formForUpdate(Model model, @PathVariable("rtgsDetailsId") Integer rtgsDetailsId ) {
		log("Action 'formForUpdate'");
		//--- Search the entity by its primary key and stores it in the model 
		TblRtgsPaidDetails tblRtgsPaidDetails = tblRtgsPaidDetailsService.findById(rtgsDetailsId);
		populateModel( model, tblRtgsPaidDetails, FormMode.UPDATE);		
		return JSP_FORM;
	}

	/**
	 * 'CREATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param tblRtgsPaidDetails  entity to be created
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/create" ) // GET or POST
	public String create(@Valid TblRtgsPaidDetails tblRtgsPaidDetails, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		//done as on 2 feb 2016
		//ADD PARAMS HttpServletResponse httpServletResponse

		log("Action 'create'");
		try {
			if (!bindingResult.hasErrors()) {

				

// done as on 06 feb 2016  save primary key default to temp
				java.lang.Integer DUMMY=new java.lang.Integer(0+"");

				tblRtgsPaidDetails.setRtgsDetailsId(DUMMY);

				TblRtgsPaidDetails tblRtgsPaidDetailsCreated = tblRtgsPaidDetailsService.create(tblRtgsPaidDetails); 
				model.addAttribute(MAIN_ENTITY_NAME, tblRtgsPaidDetailsCreated);

				
				//---
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				// done as on 06 feb 2016  introduced a new getter for getting the primary key of the newly created Bean coming after saved.
				return redirectToForm(httpServletRequest, tblRtgsPaidDetailsCreated.getRtgsDetailsId() );
				//return redirectToForm(httpServletRequest, tblRtgsPaidDetails.getRtgsDetailsId() );
			} else {
				populateModel( model, tblRtgsPaidDetails, FormMode.CREATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			log("Action 'create' : Exception - " + e.getMessage() );
			messageHelper.addException(model, "tblRtgsPaidDetails.error.create", e);
			populateModel( model, tblRtgsPaidDetails, FormMode.CREATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'UPDATE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param tblRtgsPaidDetails  entity to be updated
	 * @param bindingResult Spring MVC binding result
	 * @param model Spring MVC model
	 * @param redirectAttributes Spring MVC redirect attributes
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/update" ) // GET or POST
	public String update(@Valid TblRtgsPaidDetails tblRtgsPaidDetails, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
	//done as on 2 feb 2016
	//ADD PARAMS HttpServletResponse httpServletResponse
		
		log("Action 'update'");
		try {
			if (!bindingResult.hasErrors()) {

				
				//--- Perform database operations
				TblRtgsPaidDetails tblRtgsPaidDetailsSaved = tblRtgsPaidDetailsService.update(tblRtgsPaidDetails);
				model.addAttribute(MAIN_ENTITY_NAME, tblRtgsPaidDetailsSaved);
				//--- Set the result message
				messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
				log("Action 'update' : update done - redirect");
				return redirectToForm(httpServletRequest, tblRtgsPaidDetails.getRtgsDetailsId());
			
				
				
			} else {
				log("Action 'update' : binding errors");
				populateModel( model, tblRtgsPaidDetails, FormMode.UPDATE);
				return JSP_FORM;
			}
		} catch(Exception e) {
			messageHelper.addException(model, "tblRtgsPaidDetails.error.update", e);
			log("Action 'update' : Exception - " + e.getMessage() );
			populateModel( model, tblRtgsPaidDetails, FormMode.UPDATE);
			return JSP_FORM;
		}
	}

	/**
	 * 'DELETE' action processing. <br>
	 * This action is based on the 'Post/Redirect/Get (PRG)' pattern, so it ends by 'http redirect'<br>
	 * @param redirectAttributes
	 * @param rtgsDetailsId  primary key element
	 * @return
	 */
	@RequestMapping(value = "/delete/{rtgsDetailsId}") // GET or POST
	public String delete(RedirectAttributes redirectAttributes, @PathVariable("rtgsDetailsId") Integer rtgsDetailsId) {
		log("Action 'delete'" );
		try {
			tblRtgsPaidDetailsService.delete( rtgsDetailsId );
			//--- Set the result message
			messageHelper.addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));	
		} catch(Exception e) {
			messageHelper.addException(redirectAttributes, "tblRtgsPaidDetails.error.delete", e);
		}
		return redirectToList();
	}

	@RequestMapping("/formforcsvimport2")
	public String formForExcelImport(Model model) {
		log("Action 'formforexcelimport'");
		// --- Populates the model with a new instance
		TblRtgsPaidDetails tblRtgsPaidDetails = new TblRtgsPaidDetails();

		// done as on 19 feb 2016  save primary key default to temp
				java.lang.Integer DUMMY=new java.lang.Integer(0+"");

		tblRtgsPaidDetails.setRtgsDetailsId(DUMMY);


		model.addAttribute("fileimport2", fileimport2);
		 
		model.addAttribute(XLS_ACTION_UPLOAD, XLS_ACTION_UPLOAD_URL);

		// done as on 2 feb 2016
		return JSP_SHOW_IMPORT_PAGE;
	}

	
	@RequestMapping("/csvimport")
	public String excelimport(TblRtgsPaidDetails tblRtgsPaidDetails, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		 
		log("Action 'csvuploading'");
		try {
			if (!bindingResult.hasErrors()) {

				 
				file2 = (MultipartFile) ((DefaultMultipartHttpServletRequest) httpServletRequest).getFile("fileimport2");
				if (file2.isEmpty() == false) {
 					String productPdfFileName = file2.getOriginalFilename();
					 
 					String contenttype = file2.getContentType();
 					
 					tblRtgsPaidDetailsService.doImportExcelSpreadSheet(file2);	
 
				}
			}
		} catch (Exception e) {
			log("Action 'CSVImport' : Exception - " + e.getMessage());
			messageHelper.addException(model, "tblRtgsPaidDetails.error.create", e);	
		}
		return "redirect:/"+MAIN_ENTITY_NAME;
	}
	
	 @RequestMapping("/rtgsPayment")
	 public String rtgsPayment(Model model) 
	 {
	  log("Action 'rtgsPayment'");
	  TblRtgsPaidDetails tblRtgsPaidDetails = new TblRtgsPaidDetails();	

	// done as on 10 feb 2016  save primary key default to temp
					java.lang.Integer DUMMY=new java.lang.Integer(0+"");
			tblRtgsPaidDetails.setRtgsDetailsId(DUMMY);

			populateModel( model, tblRtgsPaidDetails, FormMode.CREATE);
			
	  return "rtgsPayment";
	 }
	
	 
	 @RequestMapping("/rtgsPaymentYearly")
	 public String rtgsPaymentYearly(Model model) 
	 {
	  log("Action 'rtgsPayment'");
	  TblRtgsPaidDetails tblRtgsPaidDetails = new TblRtgsPaidDetails();	

	// done as on 10 feb 2016  save primary key default to temp
			java.lang.Integer DUMMY=new java.lang.Integer(0+"");
			tblRtgsPaidDetails.setRtgsDetailsId(DUMMY);

			populateModel( model, tblRtgsPaidDetails, FormMode.CREATE);
			
	  return "rtgsPaymentYearly";
	 }
}
