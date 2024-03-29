/*
 * Created on 18 Mar 2016 ( Time 11:55:13 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.ridas.business.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ridas.bean.TblUserMaster;
import com.ridas.bean.jpa.TblUserMasterEntity;
import com.ridas.bean.jpa.TblUserRoleDetailsEntity;
import com.ridas.business.service.TblUserMasterService;
import com.ridas.business.service.mapping.TblUserMasterServiceMapper;

import org.modelmapper.ModelMapper;
import com.ridas.web.common.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Method to Login Service for Spring Security.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService  {


 	@Resource
 	private TblUserMasterService tblUserMasterService;
  
 	//private  TblUserMasterPersistence tblUserMasterPersistence;
 
	@Resource
 	private TblUserMasterServiceMapper tblUserMasterServiceMapper;
 
	@Override
 	public UserDetails loadUserByUsername(String username2) throws UsernameNotFoundException {
  
   		List<TblUserMasterEntity> user = tblUserMasterService.getUser(username2);
   		TblUserMasterEntity tblUserMasterEntity = user.get(0);
		TblUserMaster tblUserMaster =new TblUserMaster();

		//done as on 16 feb 2016 retireved the UserId of logging in user.
		String userid=tblUserMasterEntity.getUserIdPk();
   
   		tblUserMaster=tblUserMasterServiceMapper.mapTblUserMasterEntityToTblUserMaster(tblUserMasterEntity);
   
   		boolean enabled=true;
   
  		String username=tblUserMasterEntity.getUserName();
  		String password=tblUserMasterEntity.getUserPassword();
  		boolean accountNonExpired=true;
  		boolean credentialsNonExpired=true;
  		boolean accountNonLocked=true;
  		Collection<? extends GrantedAuthority> authorities=getAuthorities(tblUserMasterEntity.getListOfTblUserRoleDetails());

  		//return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		//done as on 16 feb 2016 saving the UserId of logging in user.
		return new CustomUserDetails(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities,userid);
   
 	}
 
 	public Collection<? extends GrantedAuthority> getAuthorities(List<TblUserRoleDetailsEntity> list)
 	{
  		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
  
  		for (TblUserRoleDetailsEntity tblUserRoleDetailsEntity : list) {
   
   			String userRoleName = tblUserRoleDetailsEntity.getTblUserRoleMaster().getUserRoleName();
   			authorities.add(new SimpleGrantedAuthority(userRoleName));
  		}
  
  		return authorities;
 	}	
}
