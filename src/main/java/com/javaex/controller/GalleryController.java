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
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	//list
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[GalleryController.list]");
		
		List<GalleryVo> galleryList = galleryService.getList();

		model.addAttribute("galleryList", galleryList);
		
	    return "/gallery/list";
	}
	
	//insert(upload)
	@RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file, HttpSession session, @RequestParam(value = "content", required = false, defaultValue =  "") String content) {
	    System.out.println("[GalleryController.upload]");

	    int userNo = ((UserVo)session.getAttribute("authUser")).getNo();

	    galleryService.insert(file, content, userNo);

	    return "redirect:/gallery/list";
	  }
}
