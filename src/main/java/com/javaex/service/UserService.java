package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
	//로그인 유저 정보 호출(Login)
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserService.getUser()]");
		
		UserVo authUser = userDao.getUser(userVo);
		
		return authUser;
	}
	
	//Join
	public int insert(UserVo userVo) {
		System.out.println("[UserService.insert()]");
		
		int count = userDao.insert(userVo);
		
		return count;
	}
	
	//ModifyUserInfo
	public UserVo modify(UserVo userVo) {
		System.out.println("[UserService.mordify()]");

		userDao.modify(userVo);
		
		return userDao.getUser(userVo); 
	}

	//Select ModifyUser(유저정보 조회)
	public UserVo getModifyUser(int idNum) {
		System.out.println("[UserService.getModifyUser()]");
		
		
		return userDao.getModifyUser(idNum);
	}
}
