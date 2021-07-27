package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	//field
	@Autowired
	private SqlSession sqlSession;
	
	//constructor
	//method-g/s
	//method-generic
	
	//로그인 유저 정보 호출(Login)
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserDao.getUser()]");
		
		return sqlSession.selectOne("user.getUser", userVo);
	}

	//Join
	public int insert(UserVo userVo) {
		System.out.println("[UserDao.insert()]");
		
		int count = sqlSession.insert("user.insert", userVo);
		
		return count;
	}
	
	//ModifyUserInfo(수정)
	public int modify(UserVo userVo) {
		System.out.println("[UserDao.modify()]");
		
		int count = sqlSession.update("user.modifyUser", userVo);
		
		return count;
	}
	
	
	//Select ModifyUser(유저정보 수정용 조회)
	public UserVo getModifyUser(int idNum) {
		System.out.println("[userDao.getmodifyUser()]");

		UserVo userVo =  sqlSession.selectOne("user.getModifyUser", idNum);
		
		return userVo;
	}
	
}
