package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value = "/api/gallery")
public class ApiGalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	
	//get one
	@ResponseBody
	@RequestMapping(value="/view", method = { RequestMethod.GET, RequestMethod.POST } )
	public GalleryVo getOneList(@RequestParam("no") int no) {
	    System.out.println("[ApiGalleryController.getOneList()]");

		GalleryVo galleryVo = galleryService.getOneList(no);
		
		return galleryVo;
	}
	
	//delete
	@ResponseBody
	@RequestMapping(value="/delete", method = { RequestMethod.GET, RequestMethod.POST } )
	public int galleryDelete(@RequestParam("no") int no) {
	    System.out.println("[ApiGalleryController.delete]");

		int count = galleryService.delete(no);
		
		return count;
	}
}
