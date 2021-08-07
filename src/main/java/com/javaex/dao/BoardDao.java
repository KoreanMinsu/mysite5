package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	//field
	@Autowired
	private SqlSession sqlSession;
	
	//paging 연습용 boardlist2
	public List<BoardVo> getBoardList2(){
		
		List<BoardVo> boardList2 = sqlSession.selectList("board.getBoardList2");

		return boardList2;
	}
	
	
	//
	public List<BoardVo> getBoardList(String searchword) {
		System.out.println("[BaordDao.getBoardList()]");
		
		List<BoardVo> boardList = sqlSession.selectList("board.getBoardList", searchword);
		
		return boardList;
	}
	
	//조회수 올리기
	public int updateHit(int no) {
		System.out.println("[Boardao.updateHit()]");
		//System.out.println(no);
		
		int count = sqlSession.update("board.updateHit", no);
		
		return count;
	}
	
	//게시판1개 정보 가져오기
	public BoardVo selectBoard(int no) {
		System.out.println("[BoardDao.selectBoard()]");
		//System.out.println(no);

		BoardVo boardVo = sqlSession.selectOne("board.selectBoard",no);
		//System.out.println(boardVo);
		
		return boardVo;
	}
	
	//write
	public int writeBoard(BoardVo boardVo) {
		System.out.println("[BoardDao.writeBoard()]");
		
		int count = sqlSession.insert("board.writeBoard", boardVo);
		
		return count;
	}
	
	//delete
	public int deleteBoard(int no) {
		System.out.println("[BoardDao.deleteBoard()]");
		
		int count = sqlSession.delete("board.deleteBoard", no);
		
		return count;
	}
	
	//modify
	public int modifyBoard(BoardVo boardVo) {
		System.out.println("[BoardDao.modifyBoard()]");
		
		int count = sqlSession.update("board.modifyBoard", boardVo);
		
		return count;
	}
}
