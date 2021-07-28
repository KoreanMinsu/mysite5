package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {

	@Autowired
	private UserService userService;
	
	//로그인폼
	@RequestMapping(value="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
	System.out.println("[UserController.loginForm]");
	
		return "/user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login]");
		
		UserVo authUser = userService.getUser(userVo);
		
		if(authUser!=null) {
			System.out.println("[로그인성공]");
			
			session.setAttribute("authUser", authUser);
			return "redirct:/main";
			
		}else {
			System.out.println("[로그인실패]");
			
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	//로그아웃
	@RequestMapping(value="/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}

	//JoinForm
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserController.joinForm]");

		return "/user/joinForm";
	}
	
	//Join
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {

		userService.insert(userVo);

		return "/user/joinOk";
	}
	
	//ModifyForm
	@RequestMapping(value="/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model,HttpSession session, @RequestParam("no") int idNum) {
		
		//로그인상태만
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			model.addAttribute("userVo", userService.getModifyUser(idNum));
			
			return "/user/modifyForm";
		} else {
			
			return "/user/loginFrom";
		}
	}
	
	//Modify
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {

		UserVo authUser  = userService.modify(userVo);
	
		session.setAttribute("authUser", authUser);

		return "redirect:/main";
	}
	
	
}	
