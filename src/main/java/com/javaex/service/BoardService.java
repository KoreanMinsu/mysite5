package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	
	//list2 페이징 연습용
	public List<BoardVo> getBoardList2(){
		System.out.println("[BoardService.getBoardList2()]");
		
		return boardDao.getBoardList2();
	}
	
	public BoardVo getBoard(int no) {
		System.out.println("[BoardService.getBoard()]");
		System.out.println(no);
		
		//조회수 올리기
		boardDao.updateHit(no);
		
		//게시판 정보 가져오기
		BoardVo boardVo = boardDao.selectBoard(no);
		
		return boardVo;
	}
	
	public List<BoardVo> getBoardList(String searchword){
		
		return boardDao.getBoardList(searchword);
	}
	
	public int write(BoardVo boardVo) {
		System.out.println("[BoardService.writeBoard()]");
		

		return boardDao.writeBoard(boardVo);
	}
	
	public int delete(int no) {
		System.out.println("[BoardService.deleteBoard()]");
		
		return boardDao.deleteBoard(no);
	}
	
	public int modify(BoardVo boardVo) {
		System.out.println("[BoardService.modify()]");

		return boardDao.modifyBoard(boardVo);
	}
}
