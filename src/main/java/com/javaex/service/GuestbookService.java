package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	//list
	public List<GuestbookVo> getList(){
		System.out.println("[GuestbookService.getList()]");
		
		List<GuestbookVo> guestbookList = guestbookDao.getList();
		
		return guestbookList;
	}
	
	//insert
	public int insert(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookService.insert()]");
		
				
		return guestbookDao.insert(guestbookVo);
	}
	
	//delete
	public int delete(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookService.delete()]");

		return guestbookDao.delete(guestbookVo);
	}
	
	//방명록 글 저장-게시 방명록 가져오기
	public GuestbookVo writeResultVo(GuestbookVo guestbookVo) {
		
		//글저장
		int count = guestbookDao.insertGuestbookKey(guestbookVo);
		System.out.println(guestbookVo);
		
		int no = guestbookVo.getNo(); //방금 가져온 글번호
		
		//글가져오기(방금등록한 번호)
		
		
		return null;
	}
}
