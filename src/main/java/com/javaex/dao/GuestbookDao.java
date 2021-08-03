package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	//list
	public List<GuestbookVo> getList() {
		System.out.println("[GuestbookDao.getList()]");

		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.getList");
		
		return guestbookList;
	}
	
	//insert
	public int insert(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookDao.insert()]");
		
		int count = sqlSession.insert("guestbook.insert",guestbookVo);
		
		return count; 
	}
	
		
	//delete
	public int delete(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookDao.delete()]");

		int count =  sqlSession.delete("guestbook.delete",guestbookVo);
		
		return count;
	}
	
	//방명록 글 저장
	public int insertGuestbookKey(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookDao.insertGuestbookKey]");
		
		int count = sqlSession.insert("guestbook.insertGuestbookKey", guestbookVo);
		
		return count;
	}

}
