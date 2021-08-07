package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/api")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	//ajax 리스트 가져오기
	@ResponseBody
	@RequestMapping(value="/guestbook/list", method= {RequestMethod.GET, RequestMethod.POST})
	public List<GuestbookVo> list() {
		System.out.println("[apiGuestbookController.list");
		
		List<GuestbookVo> guestbookList = guestbookService.getList();
		System.out.println(guestbookList);
		
		return guestbookList;
	}
	
	//ajax 방명록 저장
	@ResponseBody
	@RequestMapping(value="/guestbook/write" , method= {RequestMethod.GET, RequestMethod.POST})
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[apiGuestbookController.write]");
		
		GuestbookVo resultVo = guestbookService.writeResultVo(guestbookVo);
		
		return resultVo;
	}
	
	//ajax 방명록 삭제
	@RequestMapping(value = "/guestbook/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public int remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[apiGuestbookController.delete]");
		
		int count = guestbookService.delete(guestbookVo);
		System.out.println(count);
		
		return count;
	}
}
