/*
 * Created on 29 Feb 2016 ( Time 17:46:20 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.business.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.ridas.bean.TblMembershipForm;
import com.ridas.bean.jpa.TblMembershipFormEntity;
import com.ridas.business.service.TblMembershipFormService;
import com.ridas.business.service.mapping.TblMembershipFormServiceMapper;
import com.ridas.persistence.PersistenceServiceProvider;
import com.ridas.persistence.services.TblMembershipFormPersistence;
				
/**
 * Implementation of TblMembershipFormService
 */
@Component
public class TblMembershipFormServiceImpl implements TblMembershipFormService {

	private TblMembershipFormPersistence tblMembershipFormPersistence;

	@Resource
	private TblMembershipFormServiceMapper tblMembershipFormServiceMapper;
	
	public TblMembershipFormServiceImpl() {
		tblMembershipFormPersistence = PersistenceServiceProvider.getService(TblMembershipFormPersistence.class);
	}
		
	@Override
	public TblMembershipForm findById(String memberId) {
		TblMembershipFormEntity entity = tblMembershipFormPersistence.load(memberId);
		TblMembershipFormEntity memberFk = entity.getTblMembershipForm();
		String memberFkName = "";
		String memberFkId = "";
		if(memberFk!=null)
		{
		 memberFkName = memberFk.getMemberName();//.getMemberId();
		 memberFkId = memberFk.getMemberId();
		}
		TblMembershipForm tblMembershipForm = tblMembershipFormServiceMapper.mapTblMembershipFormEntityToTblMembershipForm(entity);
		tblMembershipForm.setMemberFkId(memberFkId);
		tblMembershipForm.setMemberFkName(memberFkName);
		return tblMembershipForm;
	}

	@Override
	public List<TblMembershipForm> findAll() {
		List<TblMembershipFormEntity> entities = tblMembershipFormPersistence.loadAll();
		List<TblMembershipForm> beans = new ArrayList<TblMembershipForm>();
		for(TblMembershipFormEntity entity : entities) {
			/* TblMembershipFormEntity tblMembershipFormTest2 = entity.getTblMembershipForm();
			 System.out.println("tblMembershipFormTest2 ::"+tblMembershipFormTest2.toString());*/
			 
			 TblMembershipFormEntity memberFk = entity.getTblMembershipForm();
				String memberFkName = "";
				String memberFkId = "";
				if(memberFk!=null)
				{
				 memberFkName = memberFk.getMemberName();//.getMemberId();
				 memberFkId = memberFk.getMemberId();
				}
			TblMembershipForm tblMembershipForm = tblMembershipFormServiceMapper.mapTblMembershipFormEntityToTblMembershipForm(entity);
			tblMembershipForm.setMemberFkId(memberFkId);
			tblMembershipForm.setMemberFkName(memberFkName);
			beans.add(tblMembershipForm);
		}
		return beans;
	}

	@Override
	public TblMembershipForm save(TblMembershipForm tblMembershipForm) {
		return update(tblMembershipForm) ;
	}

	@Override
	public TblMembershipForm create(TblMembershipForm tblMembershipForm) {
		if(tblMembershipFormPersistence.load(tblMembershipForm.getMemberId()) != null) {
			throw new IllegalStateException("already.exists");
		}
		String memberFk = tblMembershipForm.getMemberFkId();
		String memberMembershipThrough = tblMembershipForm.getMemberMembershipThrough();
		if(memberMembershipThrough.equals("Agent")==true)
		{
			tblMembershipForm.setMemberFk(memberFk);
		}
		else {
			
		
			tblMembershipForm.setMemberFk("1");
		}
		tblMembershipForm.setMemberCurrentDate(new Date());
		TblMembershipFormEntity tblMembershipFormEntity = new TblMembershipFormEntity();

	 	
		
		tblMembershipFormServiceMapper.mapTblMembershipFormToTblMembershipFormEntity(tblMembershipForm, tblMembershipFormEntity);
	 	
		TblMembershipFormEntity tblMembershipFormEntitySaved = tblMembershipFormPersistence.save(tblMembershipFormEntity);
		return tblMembershipFormServiceMapper.mapTblMembershipFormEntityToTblMembershipForm(tblMembershipFormEntitySaved);
	}

	@Override
	public TblMembershipForm update(TblMembershipForm tblMembershipForm) {
		String memberFk = tblMembershipForm.getMemberFkId();
		String memberMembershipThrough = tblMembershipForm.getMemberMembershipThrough();
		if(memberMembershipThrough.equals("Agent")==true)
		{
			tblMembershipForm.setMemberFk(memberFk);
		}
		else {
			
			tblMembershipForm.setMemberFk("1");
		}
		tblMembershipForm.setMemberCurrentDate(new Date());
		TblMembershipFormEntity tblMembershipFormEntity = tblMembershipFormPersistence.load(tblMembershipForm.getMemberId());
		tblMembershipFormServiceMapper.mapTblMembershipFormToTblMembershipFormEntity(tblMembershipForm, tblMembershipFormEntity);
		TblMembershipFormEntity tblMembershipFormEntitySaved = tblMembershipFormPersistence.save(tblMembershipFormEntity);
		return tblMembershipFormServiceMapper.mapTblMembershipFormEntityToTblMembershipForm(tblMembershipFormEntitySaved);
	}

	@Override
	public void delete(String memberId) {
		tblMembershipFormPersistence.delete(memberId);
	}

	public TblMembershipFormPersistence getTblMembershipFormPersistence() {
		return tblMembershipFormPersistence;
	}

	public void setTblMembershipFormPersistence(TblMembershipFormPersistence tblMembershipFormPersistence) {
		this.tblMembershipFormPersistence = tblMembershipFormPersistence;
	}

	public TblMembershipFormServiceMapper getTblMembershipFormServiceMapper() {
		return tblMembershipFormServiceMapper;
	}

	public void setTblMembershipFormServiceMapper(TblMembershipFormServiceMapper tblMembershipFormServiceMapper) {
		this.tblMembershipFormServiceMapper = tblMembershipFormServiceMapper;
	}

/**
	* 	done as on 13 feb 2016 added for TblUserMaster for retrieving user details for login
    */

	@Override
	public Boolean doImportExcelSpreadSheet(MultipartFile file) throws IOException {
		
		ICsvBeanReader beanReader = null;
		try
		{
//done as on 22-feb-2016  code to remove unwanted fields from list

			beanReader = new CsvBeanReader(new InputStreamReader(file.getInputStream()),
					CsvPreference.STANDARD_PREFERENCE);
			final String[] header = beanReader.getHeader(true);
			final CellProcessor[] processors = new CellProcessor[] { 
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new ParseDate("MM/dd/yyyy")					

					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new ParseDate("MM/dd/yyyy")					

					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()
					,
					new NotNull()

			};

			TblMembershipForm tblMembershipForm=new TblMembershipForm();
			while((tblMembershipForm=beanReader.read(TblMembershipForm.class, header,processors))!=null){

		
		 
			
			// done as on 19 feb 2016  save primary key default to temp
				String DUMMY="temp";

				tblMembershipForm.setMemberId(DUMMY);

			 	create(tblMembershipForm);
			}
		}
		finally
		{
			if( beanReader != null ) {
                beanReader.close();
        	}
		}
		return true;
	}

@Override
public List<TblMembershipForm> findAllWhereClause() {
	List<TblMembershipFormEntity> findAllWhereClause = tblMembershipFormPersistence.findAllWhereClause();
	System.out.println("findAllWhereClause"+findAllWhereClause.toString());
	List<TblMembershipForm> beans = new ArrayList<TblMembershipForm>();
	for(TblMembershipFormEntity WhereClause : findAllWhereClause) {
		beans.add(tblMembershipFormServiceMapper.mapTblMembershipFormEntityToTblMembershipForm(WhereClause));
	}
	System.out.println("beans"+beans.toString());
	return beans;
	
}
}
