package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guest")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	//addlist
	@RequestMapping(value = "/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("[GuestbookController.addList()]");
		
		List<GuestbookVo> guestbookList = guestbookService.getList();
		
		model.addAttribute("guestbookList", guestbookList);
		
		return "/guestbook/addList";
	}
	
	//add
	@RequestMapping(value = "/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[GuestbookController.add()]");

		guestbookService.insert(guestbookVo);
		
		return "redirect:/guest/addList";
	}
	
	//deleteform
	@RequestMapping(value = "/deleteform", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(Model model, @RequestParam("no") int no) {
		System.out.println("[GuestbookController.deleteForm()]");
		
		model.addAttribute("no", no);
		
		return "/guestbook/deleteFrom";
	}
	
	//delete
	@RequestMapping(value="/delete", method = { RequestMethod.GET, RequestMethod.POST } )
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[GuestbookController.delete()]");

		guestbookService.delete(guestbookVo);
		
		return "redirct:/guest/addlist";
	}
	
}
