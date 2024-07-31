package com.microservice.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.account.enums.RoleType;
import com.microservice.account.model.UserInfo;
import com.microservice.account.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService{

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	public UserInfo insertUserInfo(UserInfo userInfo) {
		//userInfo.setRole(RoleType.EMPLOYEE);
		return userInfoRepository.save(userInfo);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoRepository.findByUsername(username);
		return userInfo;
	}

}
/*
 * Is this class a UserDetailsService ? NO 
 * How can I make my class a UserDetailsService 
 * 
 * UserInfoService extends UserDetailsService
 * UserInfoService implements UserDetailsService
 * 
 * 
 * How can I make UserInfo class into UserDetails interface 
 * */