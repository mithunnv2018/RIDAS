/*
 * Created on 29 Feb 2016 ( Time 17:45:56 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.rest.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ridas.bean.TblPlotMaster;
import com.ridas.bean.TblPlotRecMaster;
import com.ridas.business.service.TblPlotMasterService;
import com.ridas.business.service.TblPlotRecMasterService;
import com.ridas.web.listitem.TblPlotRecMasterListItem;

/**
 * Spring MVC controller for 'TblPlotRecMaster' management.
 */
@Controller
public class TblPlotRecMasterRestController {

	@Resource
	private TblPlotRecMasterService tblPlotRecMasterService;
	@Resource
	private TblPlotMasterService tblPlotMasterService;
	
	@RequestMapping( value="/items/tblPlotRecMaster",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TblPlotRecMasterListItem> findAllAsListItems() {
		List<TblPlotRecMaster> list = tblPlotRecMasterService.findAll();
		List<TblPlotRecMasterListItem> items = new LinkedList<TblPlotRecMasterListItem>();
		for ( TblPlotRecMaster tblPlotRecMaster : list ) {
			items.add(new TblPlotRecMasterListItem( tblPlotRecMaster ) );
		}
		return items;
	}
	
	@RequestMapping( value="/tblPlotRecMaster",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TblPlotRecMaster> findAll() {
		return tblPlotRecMasterService.findAll();
	}

	@RequestMapping( value="/tblPlotRecMaster/{plotReceiptId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TblPlotRecMaster findOne(@PathVariable("plotReceiptId") Integer plotReceiptId) {
		return tblPlotRecMasterService.findById(plotReceiptId);
	}
	
	@RequestMapping( value="/tblPlotRecMaster",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TblPlotRecMaster create(@RequestBody TblPlotRecMaster tblPlotRecMaster) {
		return tblPlotRecMasterService.create(tblPlotRecMaster);
	}

	@RequestMapping( value="/tblPlotRecMaster/{plotReceiptId}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TblPlotRecMaster update(@PathVariable("plotReceiptId") Integer plotReceiptId, @RequestBody TblPlotRecMaster tblPlotRecMaster) {
		return tblPlotRecMasterService.update(tblPlotRecMaster);
	}

	@RequestMapping( value="/tblPlotRecMaster/{plotReceiptId}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("plotReceiptId") Integer plotReceiptId) {
		tblPlotRecMasterService.delete(plotReceiptId);
	}
	
	@RequestMapping(value = "/tblPlotRecMaster/populatePlot/{memberId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TblPlotMaster> populatePlot(Model model, @PathVariable("memberId") String memberId ) {
		System.out.println("******************populatePlot**************");
				//--- Search the entity by its primary key and stores it in the model 
		List<TblPlotMaster> populatePlotNoList = tblPlotMasterService.populatePlotNo(memberId);
	System.out.println("populatePlotNoList ::::::  "+populatePlotNoList.toString());
		return populatePlotNoList;
		
	}

	
	@RequestMapping( value="/tblPlotRecMaster/printForm",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String printForm(@RequestParam Map<String, String>  reqPar, HttpServletRequest request,HttpServletResponse response) {
		
		try {
			         	Integer plotPk = Integer.parseInt(reqPar.get("plotPk"));
						String receiptPk = reqPar.get("receiptPk");
						
						String PDFReceiptReport = tblPlotMasterService.printPlotPDFForm(plotPk, receiptPk, request, response);
					    System.out.println("..PDFReceiptReport : "+PDFReceiptReport);
					    ObjectMapper objectMapper = new ObjectMapper();
					    HashMap hashMap = new HashMap();
					    hashMap.put("result", PDFReceiptReport);
					    String writeValueAsString = objectMapper.writeValueAsString(hashMap);
					 
				return writeValueAsString;
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return null;
	}
	
	
	@RequestMapping( value="/tblPlotRecMaster/printReceipt",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String printReceipt(@RequestParam("receiptPk") String  receipt_pk, HttpServletRequest request,HttpServletResponse response) {
		
		try {
		
					
					String PDFReceiptReport = tblPlotMasterService.printPlotPDFReceipt(receipt_pk,request, response);
				    System.out.println("..PDFReceiptReport : "+PDFReceiptReport);
				    ObjectMapper objectMapper = new ObjectMapper();
				    
				    HashMap hashMap = new HashMap();
				    hashMap.put("result", PDFReceiptReport);
				    String writeValueAsString = objectMapper.writeValueAsString(hashMap);
				 
			return writeValueAsString;
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return null;
	}
}