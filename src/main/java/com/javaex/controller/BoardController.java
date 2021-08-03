package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/board", method= {RequestMethod.GET, RequestMethod.POST})
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	//read
	@RequestMapping(value="/read", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("[BoardController.read()]");
		
		BoardVo boardVo = boardService.getBoard(no);
		System.out.println(boardVo);
		
		model.addAttribute("boardVo", boardVo);
		
		return "/read";
	}
	
	//writeform
	@RequestMapping(value="/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("[BoardController.writeForm()]");

		return "/writeForm";
	}
		
	
	//write
	//userNo session이용
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(HttpSession session, @ModelAttribute BoardVo boardVo ) {
		System.out.println("[BoardController.write()]");

		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		boardVo.setUserNo(authUser.getNo());
		
		boardService.write(boardVo);
			
		return "redirect:/board/list";
	}
	
	//modifyform
	@RequestMapping(value="/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model, @RequestParam("no") int no) {
		System.out.println("[BoardController.modifyForm()]");
		
		//로그인 유저와 작성자 일치시 수정폼
		BoardVo boardVo = boardService.getBoard(no);
		
		model.addAttribute("boardVo", boardVo);

		return "/board/modifyForm";
	}
	
	//modify
	@RequestMapping(value="/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("[BoardController.modify()]");
		
		boardService.modify(boardVo);
		
		return "redirect:/board/list";
	}
	
	//list
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model, @RequestParam(required = false, value="searchword", defaultValue = "") String searchWord) {
		System.out.println("[BoardController.list()]");
		
		
		List<BoardVo> boardList = boardService.getBoardList(searchWord);
		
		model.addAttribute("getBoardList", boardList);
		
		return "/board/list";
	}
	
	//delete
	@RequestMapping(value="/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("[BoardController.delete()]");

		boardService.delete(no);
		
		return "redirect:/board/list";
	}
	
}
